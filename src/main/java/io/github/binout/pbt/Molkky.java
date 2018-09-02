package io.github.binout.pbt;

import java.util.Set;

public interface Molkky {

    enum Pin {
        _1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12;

        public int value() {
            return Integer.parseInt(name().substring(1));
        }
    }

    class Score {

        public int _throw(Set<Pin> pins) {
            return 0;
        }

        public int value() {
            return 0;
        }
    }
}
