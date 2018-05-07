package io.github.binout.pbt;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.github.binout.pbt.PokemonType.ELECTRIC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class PokemonTest {

    @Test
    public void fightWithSameTypeElectric() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var raichu = new Pokemon("Raichu", ELECTRIC, 2000);
        assertThat(pikachu.fights(raichu)).isEqualTo(raichu);
    }

    @Test
    public void fightWithSameTypeElectricInverse() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var raichu = new Pokemon("Raichu", ELECTRIC, 2000);
        assertThat(raichu.fights(pikachu)).isEqualTo(raichu);
    }

    @Test
    public void fightWithSameTypeElectricAndWithSamePC() {
        var pikachu = new Pokemon("Pikachu", ELECTRIC, 150);
        var raichu = new Pokemon("Raichu", ELECTRIC, 150);
        assertThat(pikachu.fights(raichu)).isEqualTo(pikachu);
    }

    @Property
    public void fightWithSameTypeElectricAndWatheverPC(String name1, @InRange(min = "0", max = "3000") int pc1,
                                                       String name2 , @InRange(min = "0", max = "3000") int pc2) {
        assumeThat(pc1, greaterThanOrEqualTo(0));
        assumeThat(pc2, greaterThanOrEqualTo(0));
        assumeThat(pc1, greaterThanOrEqualTo(pc2));
        var p1 = new Pokemon(name1, ELECTRIC, pc1);
        var p2 = new Pokemon(name2, ELECTRIC, pc2);
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

    @Property
    public void fightWithSameTypeAndWatheverPC(PokemonType type,
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
    public void fightWithSameTypeAndWatheverPokemon(@From(PokemonGenerator.class) Pokemon p1,
                                                    @From(PokemonGenerator.class) Pokemon p2) {
        assumeThat(p1.pc(), greaterThanOrEqualTo(p2.pc()));
        assumeTrue(p1.sameTypeAs(p2));
        assertThat(p1.fights(p2)).isEqualTo(p1);
    }

}