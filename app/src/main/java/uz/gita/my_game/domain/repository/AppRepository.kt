package uz.gita.my_game.domain.repository

import uz.gita.my_game.data.model.CellState

interface AppRepository {
    fun createNewCell()
    fun getAllCells() : List<CellState>
}