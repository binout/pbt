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

    public boolean isEffectiveAgainst(Pokemon another) {
        return another.type.equals(this.type.effectiveType());
    }

    public Pokemon fights(Pokemon another) {
        var pc = isEffectiveAgainst(another) ? this.pc * 2 : this.pc;
        var anotherPc = another.isEffectiveAgainst(this) ? another.pc * 2 : another.pc;
        return anotherPc > pc ? another : this;
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

    @Override
    public String toString() {
        return "Pokemon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", pc=" + pc +
                '}';
    }
}