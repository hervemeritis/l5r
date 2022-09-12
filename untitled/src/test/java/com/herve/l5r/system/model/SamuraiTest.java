package com.herve.l5r.system.model;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SamuraiTest {

    @Test
    void should_have_rank_1() {
        //Given
        Samurai samurai = SamuraiFactory.kakitaRank1();

        //When
        int rank = samurai.rank();

        //Then
        Assertions.assertThat(rank).isEqualTo(1);
    }

    @Test
    void should_have_rank_2() {
        //Given
        Samurai samurai = SamuraiFactory.kakitaRank2();

        //When
        int rank = samurai.rank();

        //Then
        Assertions.assertThat(rank).isEqualTo(2);
    }

    @Test
    void should_have_rank_3() {
        //Given
        Samurai samurai = SamuraiFactory.kakitaRank3();

        //When
        int rank = samurai.rank();

        //Then
        Assertions.assertThat(rank).isEqualTo(3);
    }
}