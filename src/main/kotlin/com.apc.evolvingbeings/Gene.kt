package com.apc.evolvingbeings

interface Gene<T : Gene<T>> {
    fun breed(other: T, mutationChance: Double): T
    fun getFitness(target: T): Double
}