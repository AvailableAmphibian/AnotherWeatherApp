package hf.dra.anotherweatherapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hf.dra.anotherweatherapp.databinding.ViewHolderFavoriteCityBinding
import hf.dra.anotherweatherapp.listeners.OnClickFavorite
import hf.dra.anotherweatherapp.model.CityData
import hf.dra.anotherweatherapp.model.Units

class FavoriteCityViewHolder(
    private val binding: ViewHolderFavoriteCityBinding,
    private val onClick: OnClickFavorite,
    private val units: Units
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.holder = this
    }

    private lateinit var item: CityData

    fun onBind(item: CityData) {
        this.item = item
        this.binding.data = item
    }

    fun View.onClickItem() {
        onClick.onClickFav(this@FavoriteCityViewHolder.item)
    }

    val temp get() = "${item.temp}${units.degree}"
}
