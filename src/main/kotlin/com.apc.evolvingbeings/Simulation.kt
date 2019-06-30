package com.apc.evolvingbeings

class Simulation<T : Gene<T>>(populations: Int, factory: (Int) -> Population<T>) {
    private val world: List<Population<T>> = List(populations, factory)

    fun start(callback: (Int, List<Double>) -> Unit = { _, _ -> }): Triple<Int, Population<T>, List<List<Double>>> {
        var i = 0
        var running = true
        lateinit var winner: Population<T>
        val iterations = mutableListOf<List<Double>>()
        while (running) {
            val iteration = world.map {
                it.evolve()
                if (it.fitness == 0.0) {
                    winner = it
                    running = false
                }
                it.fitness
            }
            iterations.add(iteration)
            callback(i++, iteration)
        }
        return Triple(i - 1, winner, iterations)
    }
}