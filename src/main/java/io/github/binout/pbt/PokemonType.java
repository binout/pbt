package io.github.binout.pbt;

public enum PokemonType {
    FIRE("PLANT"), WATER("FIRE"), PLANT("ELECTRIC"), ELECTRIC("WATER");

    private final String effectiveType;

    PokemonType(String effectiveType) {
        this.effectiveType = effectiveType;
    }

    public PokemonType effectiveType() {
        return PokemonType.valueOf(effectiveType);
    }
}
