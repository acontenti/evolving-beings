import com.apc.evolvingbeings.Being
import com.apc.evolvingbeings.Population
import com.apc.evolvingbeings.Simulation
import koma.*
import kotlin.random.Random

fun main() {
    val populations = 4
    val colors = plotColors.keys.toList()
    val simulation = Simulation(populations) {
        Population(it, Being(listOf(1.toGene(), 2.toGene())), Random.nextInt(10, 500), Random.nextDouble(0.1, 0.25)) {
            Being(List(2) { IntGene() }, 0.01)
        }
    }
    val (i, winner, iterations) = simulation.start { i, list ->
        print("Iteration: $i world =")
        list.forEach { print(" [$it]") }
        println()
    }
    println("Winner: population ${winner.id} in $i iterations, target was '${winner.target.genes}'")
    (0 until populations).map { j ->
        iterations.mapIndexed { index, list ->
            index to list[j]
        }
    }.forEachIndexed { j, it ->
        figure(1)
        val x = it.map { it.first.toDouble() }.toDoubleArray()
        val fa = it.map { it.second }
        val fmax = fa.max() ?: 1.0
        val y = fa.map { (fmax - it) / fmax }.toDoubleArray()
        plotArrays(x, y, colors[j % colors.size], "Population $j")
        xlabel("Iteration")
        ylabel("Fitness")
        title("Fitness over time")
    }
}

