package com.jesse.learningkotlin

class Classes {
    var kart: String = ""
    var speed: Int = 0
    var planador:String = ""
}

fun main() {
    val mario = Classes()
    mario.kart = "Standard Kart"
    mario.speed = 150
    mario.planador = "Super Glider"

    println("Mario's Kart: ${mario.kart} with speed ${mario.speed} and planador ${mario.planador}")
}