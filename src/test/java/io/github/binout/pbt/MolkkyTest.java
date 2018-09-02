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
import io.github.binout.pbt.Molkky.Score;
import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Size;
import net.jqwik.properties.Assumptions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.github.binout.pbt.Molkky.WHITE;

class MolkkyTest implements WithAssertions {

    @Test
    void should_start_from_zero() {
        assertThat(new Score().value()).isZero();
    }

    @Test
    void should_stay_at_zero_when_white_throw() {
        assertThat(new Score()._throw(WHITE)).isZero();
    }

    @Example
    void should_add_5_when_one_pin_5() {
        assertThat(new Score()._throw(Set.of(Pin._5))).isEqualTo(5);
    }

    @Example
    void should_add_6_when_one_pin_6() {
        assertThat(new Score()._throw(Set.of(Pin._6))).isEqualTo(6);
    }

    @Example
    void should_add_7_when_one_pin_7() {
        assertThat(new Score()._throw(Set.of(Pin._7))).isEqualTo(7);
    }

    @Property
    void should_add_pin_value_when_one_pin(@ForAll Pin pin) {
        assertThat(new Score()._throw(Set.of(pin))).isEqualTo(pin.value());
    }

    @Example
    void should_add_6_when_6_pins() {
        assertThat(new Score()._throw(Set.of(Pin._4, Pin._2, Pin._3, Pin._6, Pin._7, Pin._8))).isEqualTo(6);
    }

    @Property
    void should_add_pin_count_when_more_than_one_pin(@ForAll @Size(min = 2, max= 12) Set<Pin> pins) {
        assertThat(new Score()._throw(pins)).isEqualTo(pins.size());
    }

}
