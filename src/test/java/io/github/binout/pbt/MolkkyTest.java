/*
 * Copyright 2018 Benoît Prioux
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.binout.pbt;

import io.github.binout.pbt.Molkky.Game;
import io.github.binout.pbt.Molkky.Pin;
import io.github.binout.pbt.Molkky.Throw;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.binout.pbt.Molkky.Throw.WHITE;
import static java.util.Set.of;

class MolkkyTest implements WithAssertions {

    @Group
    class ThrowOfOnePin {

        @Example
        void given_one_pin_5_points_should_be_5() {
            assertThat(new Throw(of(Pin._5)).points()).isEqualTo(5);
        }

        @Example
        void given_one_pin_6_points_should_be_6() {
            assertThat(new Throw(of(Pin._6)).points()).isEqualTo(6);
        }

        @Example
        void given_one_pin_7_points_should_be_7() {
            assertThat(new Throw(of(Pin._7)).points()).isEqualTo(7);
        }

        @Property
        void given_one_pin_points_should_be_pin_value(@ForAll Pin pin) {
            assertThat(new Throw(of(pin)).points()).isEqualTo(pin.value());
        }
    }

    @Group
    class ThrowOfMoreThanOnePin {

        @Example
        void given_6_pins_points_should_be_6() {
            assertThat(new Throw(of(Pin._4, Pin._2, Pin._3, Pin._6, Pin._7, Pin._8)).points()).isEqualTo(6);
        }

        @Property
        void given_more_than_one_pins_points_should_be_equal_to_number_of_pin(@ForAll @Size(min = 2, max = 12) Set<Pin> pins) {
            assertThat(new Throw(pins).points()).isEqualTo(pins.size());
        }
    }

    @Test
    void score_should_start_from_zero() {
        assertThat(new Game().score()).isZero();
    }

    @Test
    void game_should_not_be_winning_at_start() {
        assertThat(new Game().isWinning()).isFalse();
    }

    @Group
    class GameScore {

        @Example
        void should_stay_at_zero_when_white_throw() {
            assertThat(new Game()._throw(WHITE).score()).isZero();
        }


        @Example
        void should_be_12_when_pin6_and_pin6() {
            var game = new Game()
                    ._throw(new Throw(of(Pin._6)))
                    ._throw(new Throw(of(Pin._6)));
            assertThat(game.score()).isEqualTo(12);
        }

        @Example
        void should_be_18_when_pin6_and_pin6_and_pin6() {
            var game = new Game()
                    ._throw(new Throw(of(Pin._6)))
                    ._throw(new Throw(of(Pin._6)))
                    ._throw(new Throw(of(Pin._6)));
            assertThat(game.score()).isEqualTo(18);
        }

        @Property
        void should_add_each_throw_points(@ForAll @IntRange(min = 1, max = 4) int nbThrows, @ForAll Throw aThrow) {
            var scoreOfAThrow = aThrow.points();
            var game = new Game();
            IntStream.range(1, nbThrows+1).forEach(i -> game._throw(aThrow));
            assertThat(game.score()).isEqualTo(scoreOfAThrow * nbThrows);
        }

    }
    @Test
    void score_should_reset_to_25_if_4_times_pin12_and_pin3() {
        var game = new Game()
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._3)));
        assertThat(game.score()).isEqualTo(25);
    }

    @Test
    void game_should_be_winning_at_50_example() {
        var game = new Game()
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._12)))
                ._throw(new Throw(of(Pin._2)));
        assertThat(game.score()).isEqualTo(50);
        assertThat(game.isWinning()).isTrue();
    }

    @Provide
    Arbitrary<Throw> aThrow() {
        return Arbitraries.integers().between(1, 12).set().ofMaxSize(12)
                .map(s -> s.stream().map(i -> Pin.valueOf("_" + i)).collect(Collectors.toSet()))
                .map(Throw::new);
    }

    @Property
    void score_is_always_between_0_and_50(@ForAll @IntRange(min = 1, max = 100) int nbThrows, @ForAll Throw aThrow) {
        var game = new Game();
        IntStream.range(1, nbThrows+1).forEach(i -> game._throw(aThrow));
        assertThat(game.score()).isBetween(0, 50);
    }

}
