package uz.gita.my_game.data.model

data class DeadCell(override val id: Int) : CellState(id,name = "Мёртвая", description = "или прикидывается")