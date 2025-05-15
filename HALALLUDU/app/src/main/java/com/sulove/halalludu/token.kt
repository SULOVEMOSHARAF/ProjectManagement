package com.sulove.halalludu

class Token(private val playerId: Int){
    var position = -1
    val isFinished: Boolean
        get() = position == 57

    fun move(steps: Int){
        position +=steps
        if (position>57){
            position = 57
        }
    }
}