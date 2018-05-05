package io.github.binout.pbt

import kotlin.test.assertEquals


class When(private val pokemon: Pokemon) {

    infix fun fights(another: Pokemon): Fight {
        return Fight(pokemon, another)
    }
}

class Fight(private val pokemon: Pokemon, private val another: Pokemon) {

    infix fun `, the winner is`(winner: Pokemon) {
        assertEquals(pokemon.fights(another), winner)
    }
}