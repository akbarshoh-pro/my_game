package uz.gita.my_game.data.model

data class LiveCell(override val id: Int) : CellState(id, name = "Живая", description = "и шевелится!")