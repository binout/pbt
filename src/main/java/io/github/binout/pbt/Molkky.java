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

import java.util.Iterator;
import java.util.Set;

public interface Molkky {

    enum Pin {
        _1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12;

        public int value() {
            return Integer.parseInt(name().substring(1));
        }
    }

    Set<Pin> WHITE = Set.of();

    class Game {

        private int score = 0;
        private boolean isWinning = false;

        public Game _throw(Set<Pin> pins) {
            score += computeThrow(pins);
            if (score == 50) {
                isWinning = true;
            }
            if (score > 50) {
                score = 25;
            }
            return this;
        }

        private int computeThrow(Set<Pin> pins) {
            if (pins.size() == 1) {
                return pins.iterator().next().value();
            } else {
                return pins.size();
            }
        }

        public int score() {
            return score;
        }

        public boolean isWinning() {
            return isWinning;
        }
    }
}
