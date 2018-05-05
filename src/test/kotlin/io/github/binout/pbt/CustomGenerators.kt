package io.github.binout.pbt

import com.pholser.junit.quickcheck.generator.GenerationStatus
import com.pholser.junit.quickcheck.generator.Generator
import com.pholser.junit.quickcheck.random.SourceOfRandomness
import java.util.*

class PokemonGenerator : Generator<Pokemon>(Pokemon::class.java) {

    override fun generate(r: SourceOfRandomness?, s: GenerationStatus?): Pokemon {
        val name = UUID.randomUUID().toString()
        val type = PokemonType.values()[r?.nextInt(0, 4)!!]
        val pc = r.nextInt(0)
        return Pokemon(name, type, pc)
    }
}