import com.apc.evolvingbeings.Gene
import kotlin.math.abs
import kotlin.random.Random

data class StringGene(private var value: String = randomString(Random.nextInt(5, 20))) :
    Gene<StringGene> {
    override fun breed(other: StringGene, mutationChance: Double): StringGene {
        return StringGene(String(value.zip(other.value) { c1, c2 -> breed(c1, c2, mutationChance) }.toCharArray()))
    }

    private fun breed(c1: Char, c2: Char, mutationChance: Double): Char {
        return when {
            mutationChance > Random.nextDouble() -> Random.nextInt(256).toChar()
            Random.nextBoolean() -> c1
            else -> c2
        }
    }

    override fun getFitness(target: StringGene): Double {
        return value.zip(target.value) { c1, c2 -> abs(c2 - c1) }.average()
    }

    companion object {
        private fun randomString(length: Int) = (1..length).map { Random.nextInt(256).toChar() }.joinToString("")
    }
}

fun String.toGene() = StringGene(this)