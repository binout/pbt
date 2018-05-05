package io.github.binout.pbt

import com.pholser.junit.quickcheck.From
import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import io.github.binout.pbt.PokemonType.ELECTRIC
import org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo
import org.junit.Assume.assumeThat
import org.junit.Assume.assumeTrue
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitQuickcheck::class)
class PokemonTest {

    @Test
    fun `fight with same type electric`() {
        val pikachu = Pokemon("Pikachu", ELECTRIC, 150)
        val raichu = Pokemon("Raichu", ELECTRIC, 2000)
        When(pikachu) fights raichu `, the winner is` raichu
    }

    @Test
    fun `fight with same type electric inverse`() {
        val pikachu = Pokemon("Pikachu", ELECTRIC, 150)
        val raichu = Pokemon("Raichu", ELECTRIC, 2000)
        When(raichu) fights pikachu `, the winner is` raichu
    }

    @Test
    fun `fight with same type electric and with same pc`() {
        val pikachu = Pokemon("Pikachu", ELECTRIC, 150)
        val raichu = Pokemon("Raichu", ELECTRIC, 150)
        When(pikachu) fights raichu `, the winner is` pikachu
    }

    @Property
    fun `fight with same type electric whatever pc`(
            name1: String, pc1: Int,
            name2: String, pc2: Int)
    {
        assumeThat(pc1, greaterThanOrEqualTo(0))
        assumeThat(pc2, greaterThanOrEqualTo(0))
        assumeThat(pc1, greaterThanOrEqualTo(pc2))
        val p1 = Pokemon(name1, ELECTRIC, pc1)
        val p2 = Pokemon(name2, ELECTRIC, pc2)
        When(p1) fights p2 `, the winner is` p1
    }

    @Property
    fun `fight with same type whatever pc`(
            name1: String, type1: PokemonType, pc1: Int,
            name2: String, type2: PokemonType, pc2: Int)
    {
        assumeThat(pc1, greaterThanOrEqualTo(0))
        assumeThat(pc2, greaterThanOrEqualTo(0))
        assumeThat(pc1, greaterThanOrEqualTo(pc2))
        assumeTrue(type1 == type2)
        val p1 = Pokemon(name1, type1, pc1)
        val p2 = Pokemon(name2, type2, pc2)
        When(p1) fights p2 `, the winner is` p1
    }

    @Property
    @Ignore
    fun `fight with same type whatever pokemon`(p1: @From(PokemonGenerator::class) Pokemon, p2: @From(PokemonGenerator::class) Pokemon)
    {
        assumeThat(p1.pc, greaterThanOrEqualTo(p2.pc))
        assumeTrue(p1.sameTypeAs(p2))
        When(p1) fights p2 `, the winner is` p1
    }

}