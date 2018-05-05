package io.github.binout.pbt

enum class PokemonType {
    FIRE, WATER, PLANT, ELECTRIC
}

data class Pokemon(val name: String, val type: PokemonType, val pc: Int = 0) {

    init {
        require(pc >= 0)
    }

    fun sameTypeAs(another: Pokemon) = this.type == another.type
}


fun Pokemon.fights(another: Pokemon) : Pokemon {
    if (another.pc > this.pc) {
        return another
    }
    return this
}