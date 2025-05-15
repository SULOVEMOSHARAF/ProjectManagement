package com.sulove.halalludu
import java.util.Scanner
class Ludogame {
    private val players = mutableListOf<Player>()
    private val board = Board()
    private var currentPlayerIndex = 0

    init {
        stetupPlayers()
    }

    private fun stetupPlayers() {
        val scanner = Scanner(System.`in`)
        println("Enter the number of players:")
        val numPlayers = scanner.nextInt()

        for (i in 1..numPlayers) {
            println("Enter the name of player $i:")
            val name = scanner.next()
            players.add(Player(name, i))

        }
    }

    fun start() {
        while (!isGameOver()) {
            val currentPlayer = players[currentPlayerIndex]
            println("It's ${currentPlayer.name}'s turn.")
            val diceresult = readLine()?.toIntOrNull() ?: 1
            currentPlayer.move(diceresult, board)

            if (currentPlayer.hasWon()) {
                println("${currentPlayer.name} has won!")
                break
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size
        }


    }
    private fun isGameOver():Boolean{
        return players.any{it.hasWon()}
    }
}