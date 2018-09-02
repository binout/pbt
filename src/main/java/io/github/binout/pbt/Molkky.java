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

    class Score {

        public int _throw(Set<Pin> pins) {
            Iterator<Pin> iterator = pins.iterator();
            if (iterator.hasNext()) {
                if (pins.size() > 1) {
                    return pins.size();
                }
                return iterator.next().value();
            } else {
                return 0;
            }
        }

        public int value() {
            return 0;
        }
    }
}
