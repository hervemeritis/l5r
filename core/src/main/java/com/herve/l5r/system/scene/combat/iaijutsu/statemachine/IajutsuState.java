package com.herve.l5r.system.scene.combat.iaijutsu.statemachine;

import java.util.Optional;

public enum IajutsuState {
    BEGIN {
        @Override
        public Optional<IajutsuState> advance(IajutsuEvent event, IajutsuDuelAggregate duel) {
            if (IajutsuEvent.INITIATIVE == event) {
                duel.rollInitiative();
                return Optional.of(IajutsuState.INITIATED);
            }
            return Optional.empty();
        }
    },
    INITIATED {
        @Override
        public Optional<IajutsuState> advance(IajutsuEvent event, IajutsuDuelAggregate duel) {
            if (IajutsuEvent.ASSESS == event) {
                duel.assess();
                return Optional.of(ASSESSED);
            }
            return Optional.empty();
        }
    },
    ASSESSED {
        @Override
        public Optional<IajutsuState> advance(IajutsuEvent event, IajutsuDuelAggregate duel) {
            if (IajutsuEvent.FOCUS == event) {
                duel.concentrate();
                return Optional.of(FOCUSED);
            }
            return Optional.empty();
        }
    },
    FOCUSED {
        @Override
        public Optional<IajutsuState> advance(IajutsuEvent event, IajutsuDuelAggregate duel) {
            return Optional.empty();
        }
    };

    public abstract Optional<IajutsuState> advance(IajutsuEvent event, IajutsuDuelAggregate duel);
}
