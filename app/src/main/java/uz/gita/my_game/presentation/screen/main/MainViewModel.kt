package uz.gita.my_game.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.gita.my_game.data.model.CellState
import uz.gita.my_game.domain.usecase.CreateNewCellUseCase
import uz.gita.my_game.domain.usecase.GetAllCellsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val createNewCellUseCase: CreateNewCellUseCase,
    private val getAllCellsUseCase: GetAllCellsUseCase
) : ViewModel() {
    private val _allCellsState = MutableStateFlow(listOf<CellState>())
    val allCellsState = _allCellsState.asStateFlow()

    fun createNewCell() {
        createNewCellUseCase()
        getAllCells()
    }

    fun getAllCells() {
        viewModelScope.launch {
            val cells = getAllCellsUseCase().toList()
            _allCellsState.emit(cells)
        }
    }
}