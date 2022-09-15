package com.herve.l5r.system.scene.iaijutsu;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.scene.Protagonist;

public class IaijutsuDuel {
    private static final RollAndKeep bonusRollOnConcentration = RollAndKeep.of(0,1,0);
    private final Protagonist protagonist;

    private IaijutsuDuel(Protagonist protagonist) {
        this.protagonist = protagonist;
    }

    public static Initiative between(Samurai duelist1, Samurai duelist2) {
        Protagonist opponent = Protagonist.of(duelist2);
        Protagonist protagonist = Protagonist.of(duelist1).withOpponent(opponent);
        opponent.withOpponent(protagonist);
        return new IaijutsuDuel(protagonist).initiative();
    }

    private Initiative initiative() {
        return new InitiativeStep();
    }

    private class InitiativeStep implements Initiative {

        @Override
        public Evaluation rollInitiative() {
            protagonist.initiative();
            protagonist.opponent().initiative();
            return new EvaluationStep();
        }
    }

    private class EvaluationStep implements Evaluation {

        @Override
        public Concentration evaluation() {
            protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_EVALUATION);
            protagonist.opponent().evaluate(CompetenceTypeRoll.IAJUTSU_EVALUATION);
            return new ConcentrationStep();
        }
    }

    private class ConcentrationStep implements Concentration {

        @Override
        public Frappe concentration() {
            int differenceWithOpponent = protagonist.resultDifferenceWithOpponent();
            if(differenceWithOpponent >= 10) {
                protagonist.addTemporaryBonus(bonusRollOnConcentration);
            } else if (differenceWithOpponent <= -10) {
                protagonist.opponent().addTemporaryBonus(bonusRollOnConcentration);
            }
            protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_CONCENTRATION);
            protagonist.opponent().evaluate(CompetenceTypeRoll.IAJUTSU_CONCENTRATION);
            return new FrappeStep();
        }
    }

    private class FrappeStep implements Frappe {

        @Override
        public DuelResult frappe() {
            int oppositionResult = protagonist.resultDifferenceWithOpponent();
            if (oppositionResult == 0) {
                return tie();
            } else {
                Protagonist winnerConcentration = protagonist.resultDifferenceWithOpponent() > 0 ? protagonist : protagonist.opponent();
                return resolveDuel(winnerConcentration);
            }
        }

        private DuelResult resolveDuel(Protagonist protagonist) {
            if (protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_FRAPPE) >= protagonist.opponent().TNToBeHit()) {
                return DuelResult.winner(protagonist.samurai);
            } else if (protagonist.opponent().evaluate(CompetenceTypeRoll.IAJUTSU_FRAPPE) >= protagonist.TNToBeHit()) {
                return DuelResult.winner(protagonist.opponent().samurai);
            } else {
                return DuelResult.tie();
            }
        }

        private DuelResult tie() {
            protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_FRAPPE);
            protagonist.opponent().evaluate(CompetenceTypeRoll.IAJUTSU_FRAPPE);
            return DuelResult.tie();
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
