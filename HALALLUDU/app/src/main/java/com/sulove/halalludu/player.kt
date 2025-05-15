package com.sulove.halalludu

class Player( val name: String, val id: Int) {
    private val tokens = List(4) { Token(id) }
    fun move(diceResult: Int, board: Board) {
        println("$name rolled a $diceResult")
        val token = tokens.firstOrNull { !it.isFinished } ?: return
        board.moveToken(token, diceResult)
    }
    fun hasWon(): Boolean{
        return tokens.all{ it.isFinished }

}
}