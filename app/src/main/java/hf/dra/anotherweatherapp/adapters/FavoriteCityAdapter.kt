package hf.dra.anotherweatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import hf.dra.anotherweatherapp.databinding.ViewHolderFavoriteCityBinding
import hf.dra.anotherweatherapp.listeners.OnClickFavorite
import hf.dra.anotherweatherapp.model.CityData
import hf.dra.anotherweatherapp.model.Units

class FavoriteCityAdapter(
    private val cities: List<CityData>,
    context: Context,
    private val onClick: OnClickFavorite
) : RecyclerView.Adapter<FavoriteCityViewHolder>() {
    private val units: Units = Units.getValueOf(
        PreferenceManager.getDefaultSharedPreferences(context).getString("Standard", "Standard")!!
    )
    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCityViewHolder {
        return FavoriteCityViewHolder(
            ViewHolderFavoriteCityBinding.inflate(
                inflater,
                parent,
                false
            ), onClick, units
        )
    }

    override fun onBindViewHolder(holder: FavoriteCityViewHolder, position: Int) {
        holder.onBind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}