package io.github.binout.pbt;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.github.binout.pbt.Generators.PokemonGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.github.binout.pbt.PokemonType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class PokemonTest {

    @Test
    public void fight_with_same_type_Electric() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var raichu = new Pokemon("Raichu", ELECTRIC, 2000);
        assertThat(pikachu.fights(raichu)).isEqualTo(raichu);
    }

    @Test
    public void fight_with_same_type_Electric_inverse() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var raichu = new Pokemon("Raichu", ELECTRIC, 2000);
        assertThat(raichu.fights(pikachu)).isEqualTo(raichu);
    }

    @Test
    public void fight_with_same_type_Electric_and_with_same_pc() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var raichu = new Pokemon("Raichu", ELECTRIC, 150);
        assertThat(pikachu.fights(raichu)).isEqualTo(pikachu);
    }

    @Property
    public void fight_with_same_type_Electric_and_whatever_pc(
            String name1, @InRange(min = "0", max = "3000") int pc1,
            String name2 , @InRange(min = "0", max = "3000") int pc2) {
        assumeThat(pc1, greaterThanOrEqualTo(0));
        assumeThat(pc2, greaterThanOrEqualTo(0));
        assumeThat(pc1, greaterThanOrEqualTo(pc2));
        var p1 = new Pokemon(name1, ELECTRIC, pc1);
        var p2 = new Pokemon(name2, ELECTRIC, pc2);
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

    @Property
    public void fight_with_same_type_and_whatever_pc(
            PokemonType type,
            String name1, @InRange(min = "0", max = "3000") int pc1,
            String name2, @InRange(min = "0", max = "3000") int pc2) {
        assumeThat(pc1, greaterThanOrEqualTo(0));
        assumeThat(pc2, greaterThanOrEqualTo(0));
        assumeThat(pc1, greaterThanOrEqualTo(pc2));
        var p1 = new Pokemon(name1, type, pc1);
        var p2 = new Pokemon(name2, type, pc2);
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

    @Property
    public void fight_with_same_type_and_whatever_pokemon(
            @From(PokemonGenerator.class) Pokemon p1,
            @From(PokemonGenerator.class) Pokemon p2) {
        assumeThat(p1.pc(), greaterThanOrEqualTo(p2.pc()));
        assumeTrue(p1.sameTypeAs(p2));
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

    @Test
    public void fight_with_electric_against_water() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var carapuce = new Pokemon("Carapuce", WATER, 100);
        assertThat(pikachu.fights(carapuce)).isEqualTo(pikachu);
    }

    @Test
    public void fight_with_electric_against_fire() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var salameche = new Pokemon("Salameche", FIRE, 100);
        assertThat(pikachu.fights(salameche)).isEqualTo(pikachu);
    }

    @Test
    public void fight_with_electric_against_plant() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var bulbizar = new Pokemon("Bulbizar", PLANT, 100);
        assertThat(pikachu.fights(bulbizar)).isEqualTo(bulbizar);
    }

    @Test
    public void fight_with_electric_against_weak_plant() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var bulbizar = new Pokemon("Bulbizar", PLANT, 10);
        assertThat(pikachu.fights(bulbizar)).isEqualTo(pikachu);
    }

    @Property
    public void fight_with_not_effective_type_and_whatever_pokemon(
            @From(PokemonGenerator.class) Pokemon p1,
            @From(PokemonGenerator.class) Pokemon p2) {
        assumeFalse(p1.isEffectiveAgainst(p2));
        assumeFalse(p2.isEffectiveAgainst(p1));
        assumeThat(p1.pc(), greaterThanOrEqualTo(p2.pc()));
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

    @Property
    public void fight_with_weak_effective_type_and_whatever_pokemon(
            @From(PokemonGenerator.class) Pokemon p1,
            @From(PokemonGenerator.class) Pokemon p2) {
        assumeFalse(p1.isEffectiveAgainst(p2));
        assumeTrue(p2.isEffectiveAgainst(p1));
        assumeTrue(p1.pc() > p2.pc() * 2);
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

    @Property
    public void fight_with_effective_type_and_whatever_pokemon(
            @From(PokemonGenerator.class) Pokemon p1,
            @From(PokemonGenerator.class) Pokemon p2) {
        assumeFalse(p1.isEffectiveAgainst(p2));
        assumeTrue(p2.isEffectiveAgainst(p1));
        assumeTrue(p1.pc() > p2.pc());
        assumeTrue(p1.pc() < p2.pc() * 2);
        assertThat(p1.fights(p2)).isEqualTo(p2);
    }

    @Property
    public void fight_with_effective_type_and_whatever_pokemon_inverse(
            @From(PokemonGenerator.class) Pokemon p1,
            @From(PokemonGenerator.class) Pokemon p2) {
        assumeTrue(p1.isEffectiveAgainst(p2));
        assumeFalse(p2.isEffectiveAgainst(p1));
        assumeTrue(p2.pc() > p1.pc());
        assumeTrue(p2.pc() < p1.pc() * 2);
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

}