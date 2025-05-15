package com.sulove.halalludu

class Board{
    private val positions = mutableMapOf<Int, MutableList<Token>>()
    fun moveToken (token: Token, steps: Int){
        val oldPosition = token.position
        token.move(steps)
        val newPosition = token.position

        if (oldPosition!=1){
            positions[oldPosition]?.remove(token)
        }
        positions.computeIfAbsent(newPosition){mutableListOf()}.add(token)
        println("Token moved from $oldPosition to $newPosition")
    }

}