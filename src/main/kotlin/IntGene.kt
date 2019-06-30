import com.apc.evolvingbeings.Gene
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.log10
import kotlin.math.roundToInt
import kotlin.random.Random

data class IntGene(private var value: Int = Random.nextInt(100000)) :
    Gene<IntGene> {
    override fun breed(other: IntGene, mutationChance: Double): IntGene {
        return IntGene(breed(other.value, mutationChance))
    }

    private fun breed(other: Int, mutationChance: Double): Int {
        val mean = (value + other) / 2.0
        val magnitude = log10(mean).roundToInt().absoluteValue
        val span = if (magnitude > 0) magnitude else 1
        val mutation = if (mutationChance > Random.nextDouble()) Random.nextInt(-span, span) else 0
        return (mean + mutation).roundToInt()
    }

    override fun getFitness(target: IntGene): Double {
        return abs(value - target.value).toDouble()
    }
}

fun Int.toGene() = IntGene(this)