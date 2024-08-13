package uz.gita.my_game.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.my_game.R
import uz.gita.my_game.data.model.CellState
import uz.gita.my_game.data.model.DeadCell
import uz.gita.my_game.data.model.Life
import uz.gita.my_game.data.model.LiveCell
import uz.gita.my_game.databinding.RvItemBinding

class CellsAdapter : ListAdapter<CellState, CellsAdapter.VH>(DiffU) {

    inner class VH(private val item: RvItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(data: CellState) {
            item.itemName.text = data.name
            item.itemDesc.text = data.description
            when(data) {
                is DeadCell -> {
                    item.itemImg.setImageResource(R.drawable.ic_die)
                    item.itemImgBg.setBackgroundResource(R.drawable.gr_img_1)
                }
                is LiveCell -> {
                    item.itemImg.setImageResource(R.drawable.ic_live)
                    item.itemImgBg.setBackgroundResource(R.drawable.gr_img_2)
                }
                is Life -> {
                    item.itemImg.setImageResource(R.drawable.ic_life)
                    item.itemImgBg.setBackgroundResource(R.drawable.gr_img_3)
                }
            }
        }
    }

    object DiffU : DiffUtil.ItemCallback<CellState>() {
        override fun areItemsTheSame(oldItem: CellState, newItem: CellState): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CellState, newItem: CellState): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))
}