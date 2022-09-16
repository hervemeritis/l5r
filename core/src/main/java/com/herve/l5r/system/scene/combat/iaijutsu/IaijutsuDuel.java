package com.herve.l5r.system.scene.combat.iaijutsu;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.model.weapon.WeaponType;
import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.scene.Protagonist;
import com.herve.l5r.system.scene.combat.model.CombatEvent;
import com.herve.l5r.system.scene.combat.model.FrappeResult;
import com.herve.l5r.system.scene.common.CompetenceRollEvent;
import com.herve.l5r.system.scene.common.InitiativeEvent;
import com.herve.l5r.system.scene.logger.EventLogger;

public class IaijutsuDuel {
    private static final RollAndKeep bonusRollOnConcentration = RollAndKeep.of(0, 1, 0);
    private final Protagonist protagonist;

    private final EventLogger eventLogger = new EventLogger();

    private IaijutsuDuel(Protagonist protagonist) {
        this.protagonist = protagonist;
    }

    public static Initiative between(Samurai duelist1, Samurai duelist2) {
        Protagonist opponent = Protagonist.of(duelist2).draw(WeaponType.KATANA);
        Protagonist protagonist = Protagonist.of(duelist1).withOpponent(opponent).draw(WeaponType.KATANA);
        opponent.withOpponent(protagonist);
        return new IaijutsuDuel(protagonist).initiative();
    }

    private Initiative initiative() {
        return new InitiativeStep();
    }

    private class InitiativeStep implements Initiative {

        @Override
        public Evaluation rollInitiative() {
            ComputedResult initiative = protagonist.computeInitiative();
            eventLogger.log(new InitiativeEvent(protagonist.samurai, initiative));
            ComputedResult opponentInitiative = protagonist.opponent().computeInitiative();
            eventLogger.log(new InitiativeEvent(protagonist.opponent().samurai, opponentInitiative));
            return new EvaluationStep();
        }
    }

    private class EvaluationStep implements Evaluation {

        @Override
        public Concentration evaluation() {
            ComputedResult protagonistEvaluation = protagonist.iaijutsuAssesment();
            eventLogger.log(new CompetenceRollEvent(protagonist.samurai, protagonistEvaluation, CompetenceTypeRoll.IAJUTSU_EVALUATION));
            ComputedResult opponentEvaluation = protagonist.opponent().iaijutsuAssesment();
            eventLogger.log(new CompetenceRollEvent(protagonist.opponent().samurai, opponentEvaluation, CompetenceTypeRoll.IAJUTSU_EVALUATION));
            return new ConcentrationStep();
        }
    }

    private class ConcentrationStep implements Concentration {

        @Override
        public Frappe concentration() {
            int differenceWithOpponent = protagonist.resultDifferenceWithOpponent();
            if (differenceWithOpponent >= 10) {
                eventLogger.log(() -> protagonist.name() + " has gained concentration bonus");
                protagonist.addTemporaryBonus(bonusRollOnConcentration);
            } else if (differenceWithOpponent <= -10) {
                eventLogger.log(() -> protagonist.opponent().name() + " has gained concentration bonus");
                protagonist.opponent().addTemporaryBonus(bonusRollOnConcentration);
            }
            ComputedResult protagonistConcentration = protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_CONCENTRATION);
            eventLogger.log(new CompetenceRollEvent(protagonist.samurai, protagonistConcentration, CompetenceTypeRoll.IAJUTSU_CONCENTRATION));
            ComputedResult opponentEvaluation = protagonist.opponent().evaluate(CompetenceTypeRoll.IAJUTSU_CONCENTRATION);
            eventLogger.log(new CompetenceRollEvent(protagonist.opponent().samurai, opponentEvaluation, CompetenceTypeRoll.IAJUTSU_CONCENTRATION));
            return new FrappeStep();
        }
    }

    private class FrappeStep implements Frappe {

        @Override
        public DuelResult frappe() {
            int oppositionResult = protagonist.resultDifferenceWithOpponent();
            if (Math.abs(oppositionResult) < 5) {
                eventLogger.log(() -> "Kharmic Strike");
                return kharmicStrike();
            } else {
                Protagonist winnerConcentration = protagonist.resultDifferenceWithOpponent() > 0 ? protagonist : protagonist.opponent();
                return resolveDuel(winnerConcentration);
            }
        }

        private DuelResult resolveDuel(Protagonist protagonist) {
            int ndGratuit = (protagonist.resultDifferenceWithOpponent() - 5) / 5;
            if(ndGratuit > 0) {
                eventLogger.log(() -> protagonist.name() + " has gained " + ndGratuit + " free raise");
            }
            FrappeResult frappeResult = protagonist.iaijutsuFrappe(ndGratuit);
            eventLogger.log(new CombatEvent(protagonist.samurai, frappeResult, CompetenceName.IAIJUSTSU));
            if (frappeResult.hasSucceed()) {
                return DuelResult.winner(protagonist.samurai).with(eventLogger);
            }
            frappeResult = protagonist.opponent().iaijutsuFrappe(0);
            eventLogger.log(new CombatEvent(protagonist.opponent().samurai, frappeResult, CompetenceName.IAIJUSTSU));
            if (frappeResult.hasSucceed()) {
                return DuelResult.winner(protagonist.opponent().samurai).with(eventLogger);
            }
            return DuelResult.tie().with(eventLogger);
        }

        private DuelResult kharmicStrike() {
            FrappeResult protagonistHasHit = protagonist.iaijutsuFrappe( 0);
            eventLogger.log(new CombatEvent(protagonist.samurai, protagonistHasHit, CompetenceName.IAIJUSTSU));
            FrappeResult opponentHasHit = protagonist.opponent().iaijutsuFrappe(0);
            eventLogger.log(new CombatEvent(protagonist.opponent().samurai, opponentHasHit, CompetenceName.IAIJUSTSU));
            if (protagonistHasHit.hasSucceed() == opponentHasHit.hasSucceed()) {
                return DuelResult.tie().with(eventLogger);
            }
            DuelResult duelResult = protagonistHasHit.hasSucceed() ? DuelResult.winner(protagonist.samurai) : DuelResult.winner(protagonist.opponent().samurai);
            return duelResult.with(eventLogger);
        }
    }

    @FunctionalInterface
    public interface Initiative {
        Evaluation rollInitiative();
    }

    @FunctionalInterface
    public interface Evaluation {
        Concentration evaluation();
    }

    @FunctionalInterface
    public interface Concentration {
        Frappe concentration();
    }

    @FunctionalInterface
    public interface Frappe {
        DuelResult frappe();
    }
}
