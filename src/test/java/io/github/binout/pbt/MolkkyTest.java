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

import io.github.binout.pbt.Molkky.Pin;
import io.github.binout.pbt.Molkky.Game;
import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.IntStream;

import static io.github.binout.pbt.Molkky.WHITE;
import static java.util.Set.of;

class MolkkyTest implements WithAssertions {

    @Test
    void should_start_from_zero() {
        assertThat(new Game().score()).isZero();
    }

    @Test
    void should_stay_at_zero_when_white_throw() {
        assertThat(new Game()._throw(WHITE).score()).isZero();
    }

    @Example
    void should_add_5_when_one_pin_5() {
        assertThat(new Game()._throw(of(Pin._5)).score()).isEqualTo(5);
    }

    @Example
    void should_add_6_when_one_pin_6() {
        assertThat(new Game()._throw(of(Pin._6)).score()).isEqualTo(6);
    }

    @Example
    void should_add_7_when_one_pin_7() {
        assertThat(new Game()._throw(of(Pin._7)).score()).isEqualTo(7);
    }

    @Property
    void should_add_pin_value_when_one_pin(@ForAll Pin pin) {
        assertThat(new Game()._throw(of(pin)).score()).isEqualTo(pin.value());
    }

    @Example
    void should_add_6_when_6_pins() {
        assertThat(new Game()._throw(of(Pin._4, Pin._2, Pin._3, Pin._6, Pin._7, Pin._8)).score()).isEqualTo(6);
    }

    @Property
    void should_add_pin_count_when_more_than_one_pin(@ForAll @Size(min = 2, max= 12) Set<Pin> pins) {
        assertThat(new Game()._throw(pins).score()).isEqualTo(pins.size());
    }

    @Example
    void should_set_score_to_12_when_pin6_and_pin6() {
        var game = new Game()
                ._throw(of(Pin._6))
                ._throw(of(Pin._6));
        assertThat(game.score()).isEqualTo(12);
    }

    @Example
    void should_set_score_to_18_when_pin6_and_pin6_and_pin6() {
        var game = new Game()
                ._throw(of(Pin._6))
                ._throw(of(Pin._6))
                ._throw(of(Pin._6));
        assertThat(game.score()).isEqualTo(18);
    }

    @Property
    void should_set_add_throw_to_score(@ForAll @IntRange(min = 1, max = 4) int nbThrows, @ForAll @Size(max= 12) Set<Pin> pins) {
        var scoreOfAThrow = new Game()._throw(pins).score();
        var game = new Game();
        IntStream.range(1, nbThrows+1).forEach(i -> game._throw(pins));
        assertThat(game.score()).isEqualTo(scoreOfAThrow * nbThrows);
    }

}
