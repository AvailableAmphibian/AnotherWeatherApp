package hf.dra.anotherweatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hf.dra.anotherweatherapp.databinding.ViewHolderCityJsonBinding
import hf.dra.anotherweatherapp.listeners.SearchListener
import hf.dra.anotherweatherapp.model.CityJson

class CityJsonAdapter(
    private val cityList: List<CityJson>,
    private val listener: SearchListener,
    context: Context
) : RecyclerView.Adapter<CityJsonViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityJsonViewHolder {
        return CityJsonViewHolder(
            listener,
            ViewHolderCityJsonBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityJsonViewHolder, position: Int) {
        holder.onBind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}