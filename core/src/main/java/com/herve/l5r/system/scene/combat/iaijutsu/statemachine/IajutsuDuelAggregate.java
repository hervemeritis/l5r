package com.herve.l5r.system.scene.combat.iaijutsu.statemachine;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.roll.model.AgainstTNResult;
import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.scene.Protagonist;
import com.herve.l5r.system.scene.combat.iaijutsu.DuelResult;

import java.util.Objects;

class IajutsuDuelAggregate {
    private static final RollAndKeep bonusRollOnConcentration = RollAndKeep.of(0, 1, 0);
    private final Protagonist challenger;
    private final Protagonist opponent;

    private AssesmentResult assesmentResult;

    private ConcentrationResult concentrationResult;

    public IajutsuDuelAggregate(Protagonist challenger, Protagonist opponent) {
        this.opponent = opponent;
        this.challenger = challenger;
    }

    void rollInitiative() {
        challenger.computeInitiative();
        opponent.computeInitiative();
    }

    void assess() {
        if (Objects.nonNull(assesmentResult)) {
            throw new IllegalStateException("Assessment already compute");
        }
        assesmentResult = new AssesmentResult(
                iajutsuAssesment(challenger, opponent.samurai),
                iajutsuAssesment(opponent, challenger.samurai));
    }

    void concentrate() {
        if (Objects.nonNull(concentrationResult)) {
            throw new IllegalStateException("Concentration already compute");
        }
        RollAndKeep challengerBonus = RollAndKeep.zero();
        RollAndKeep opponentBonus = RollAndKeep.zero();

        if(assesmentResult.computeGap() >= 10) {
            challengerBonus = bonusRollOnConcentration;
        } else if (assesmentResult.computeGap() <= -10) {
            opponentBonus = bonusRollOnConcentration;
        }

        concentrationResult = new ConcentrationResult(
                challenger.evaluate(CompetenceTypeRoll.IAJUTSU_CONCENTRATION, challengerBonus),
                opponent.evaluate(CompetenceTypeRoll.IAJUTSU_CONCENTRATION, opponentBonus));
    }


    private AgainstTNResult iajutsuAssesment(Protagonist protagonist, Samurai opponent) {
        ComputedResult assesmentResult = protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_EVALUATION);
        int assesmentDifficulty = opponent.rank() * 5 + 10;
        return new AgainstTNResult(assesmentResult, assesmentDifficulty);
    }

    void strike() {

    }

    private DuelResult kharmicStrike() {

    }

    record AssesmentResult(AgainstTNResult challengerResult, AgainstTNResult opponentResult) {
        public int computeGap() {
            return challengerResult.computedResult().result() - opponentResult.computedResult().result();
        }
    }

    record ConcentrationResult(ComputedResult challengerResult, ComputedResult opponentResult) {

    }
}
