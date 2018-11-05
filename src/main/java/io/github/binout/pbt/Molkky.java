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

import java.util.Set;

public interface Molkky {

    enum Pin {
        _1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12;

        public int value() {
            return Integer.parseInt(name().substring(1));
        }
    }


    class Throw {

        public static Throw WHITE = new Throw(Set.of());

        private final Set<Pin> pins;

        public Throw(Set<Pin> pins) {
            this.pins = pins;
        }

        public int points() {
           return 0;
        }
    }

    class Game {

        private int score = 0;
        private boolean isWinning = false;

        public Game _throw(Throw aThrow) {
            return this;
        }


        public int score() {
            return score;
        }

        public boolean isWinning() {
            return isWinning;
        }
    }
}
