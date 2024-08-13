package uz.gita.my_game.presentation.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.my_game.core.BaseFragment
import uz.gita.my_game.databinding.ScreenMainBinding
import uz.gita.my_game.presentation.adapter.CellsAdapter

@AndroidEntryPoint
class MainScreen : BaseFragment<ScreenMainBinding>(ScreenMainBinding::inflate) {
    private val adapter by lazy { CellsAdapter() }
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.recyclerView.scrollToPosition(adapter.itemCount - 1)
            }
        })


        viewModel.getAllCells()

        viewModel.allCellsState.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        binding.createBtn.setOnClickListener {
            viewModel.createNewCell()
        }
    }
}