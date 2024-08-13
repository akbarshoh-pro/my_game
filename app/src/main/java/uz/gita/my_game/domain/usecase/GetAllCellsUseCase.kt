package uz.gita.my_game.domain.usecase

import uz.gita.my_game.data.model.CellState
import uz.gita.my_game.domain.repository.AppRepository

class GetAllCellsUseCase(
    private val repo: AppRepository
) {
    operator fun invoke() : List<CellState> =
        repo.getAllCells()
}