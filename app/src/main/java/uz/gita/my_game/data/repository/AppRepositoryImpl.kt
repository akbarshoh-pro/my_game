package uz.gita.my_game.data.repository

import android.util.Log
import uz.gita.my_game.data.model.CellState
import uz.gita.my_game.data.model.DeadCell
import uz.gita.my_game.data.model.Life
import uz.gita.my_game.data.model.LiveCell
import uz.gita.my_game.domain.repository.AppRepository
import javax.inject.Singleton
import kotlin.random.Random
import kotlin.reflect.KClass

class AppRepositoryImpl : AppRepository {
    private val cells = mutableListOf<CellState>()
    private var nextId = 0

    override fun createNewCell() {
        val newCell = if (Random.nextBoolean()) LiveCell(nextId++) else DeadCell(nextId++)
        when {
            lastThreeCellsAre(LiveCell::class) -> cells.add(Life(nextId++))
            lastThreeCellsAre(DeadCell::class) -> {
                removeLifeAroundIfNeeded()
                cells.add(newCell)
            }
            else -> cells.add(newCell)
        }
    }

    override fun getAllCells(): List<CellState> = cells

    private fun lastThreeCellsAre(stateClass: KClass<out CellState>): Boolean {
        if (cells.size < 3) return false
        return cells.takeLast(3).all { it::class == stateClass }
    }

    private fun removeLifeAroundIfNeeded() {
        if (lastThreeCellsAre(DeadCell::class)) {
            val index = cells.indexOfLast { it is Life }
            if (index != -1)
                cells.removeAt(index)
        }
    }
}

