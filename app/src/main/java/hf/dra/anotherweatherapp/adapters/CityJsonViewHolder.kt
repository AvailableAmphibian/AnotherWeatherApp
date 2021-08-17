package hf.dra.anotherweatherapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hf.dra.anotherweatherapp.databinding.ViewHolderCityJsonBinding
import hf.dra.anotherweatherapp.listeners.OnClickSearch
import hf.dra.anotherweatherapp.model.CityJson

class CityJsonViewHolder(
    private val onClick: OnClickSearch,
    private val binding: ViewHolderCityJsonBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var item: CityJson

    fun onBind(item: CityJson) {
        this.item = item
        binding.item = item
        binding.viewHolder = this
    }

    fun View.onClickItem() {
        onClick.onClickSearch(this@CityJsonViewHolder.item)
    }
}