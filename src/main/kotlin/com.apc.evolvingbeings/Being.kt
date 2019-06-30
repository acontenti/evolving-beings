package com.apc.evolvingbeings

import kotlin.math.min
import kotlin.random.Random

data class Being<T : Gene<T>>(
    val genes: List<T>,
    val mutationChance: Double = 0.0,
    val fertility: Int = Random.nextInt(2, 10)
) {
    val id: Int = MAX_ID++

    fun getFitness(target: Being<T>): Double {
        return genes.zip(target.genes) { a, b -> a.getFitness(b) }.average()
    }

    fun breed(other: Being<T>): List<Being<T>> {
        val fertility = min(fertility, other.fertility)
        return (1..fertility).map {
            Being(genes.zip(other.genes) { a, b -> a.breed(b, mutationChance) }, mutationChance)
        }
    }

    companion object {
        var MAX_ID = 0
    }
}