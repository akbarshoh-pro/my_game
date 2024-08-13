package uz.gita.my_game.data.model

sealed class CellState(open val id: Int, val name: String, val description: String)