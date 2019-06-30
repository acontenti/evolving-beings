package com.apc.evolvingbeings

class Population<T : Gene<T>>(
    val id: Int,
    val target: Being<T>,
    val size: Int,
    val survivalRate: Double,
    factory: (Int) -> Being<T>
) {
    var fitness: Double = Double.MAX_VALUE
        private set(value) {
            field = value
        }
    private val saved = (size * survivalRate / 2.0).toInt()
    private var beings = List(size, factory)

    fun evolve() {
        beings = beings.sortedBy { it.getFitness(target) }
        beings = beings.take(saved) + beings.takeLast(size - saved).shuffled().take(saved)
        beings = beings + beings.shuffled().zipWithNext { f, m -> f.breed(m) }.flatten()
        updateFitness()
    }

    private fun updateFitness() {
        fitness = beings.map { it.getFitness(target) }.average()
    }
}