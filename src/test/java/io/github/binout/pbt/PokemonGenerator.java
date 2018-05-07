package io.github.binout.pbt;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.UUID;

public class PokemonGenerator extends Generator<Pokemon> {

    public PokemonGenerator() {
        super(Pokemon.class);
    }

    @Override
    public Pokemon generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        var name = UUID.randomUUID().toString();
        var type = PokemonType.values()[sourceOfRandomness.nextInt(0, 3)];
        var pc = sourceOfRandomness.nextInt(0, 3000);
        return new Pokemon(name, type, pc);
    }
}