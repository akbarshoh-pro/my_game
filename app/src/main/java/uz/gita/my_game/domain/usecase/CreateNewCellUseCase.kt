package uz.gita.my_game.domain.usecase

import uz.gita.my_game.domain.repository.AppRepository

class CreateNewCellUseCase(
    private val repo: AppRepository
) {
    operator fun invoke() =
        repo.createNewCell()
}