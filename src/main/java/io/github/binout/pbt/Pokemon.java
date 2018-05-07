package io.github.binout.pbt;

import java.util.Objects;
import java.util.UUID;

public class Pokemon {

    private final String id;
    private final String name;
    private final PokemonType type;
    private final int pc;

    public Pokemon(String name, PokemonType type, int pc) {
        this.id = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
        if (pc < 0 || pc > 3000) {
            throw new IllegalArgumentException("PC must be between 0 and 3000");
        }
        this.pc = pc;
    }

    public String name() {
        return name;
    }

    public PokemonType type() {
        return type;
    }

    public int pc() {
        return pc;
    }

    public boolean sameTypeAs(Pokemon another) {
        return this.type == another.type;
    }

    public Pokemon fights(Pokemon another) {
        if (another.pc > this.pc) {
            return another;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}