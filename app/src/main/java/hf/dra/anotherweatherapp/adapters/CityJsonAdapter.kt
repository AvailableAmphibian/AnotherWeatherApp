package hf.dra.anotherweatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hf.dra.anotherweatherapp.databinding.ViewHolderCityJsonBinding
import hf.dra.anotherweatherapp.listeners.OnClickSearch
import hf.dra.anotherweatherapp.model.CityJson

class CityJsonAdapter(
    private val cityList: List<CityJson>,
    context: Context,
    private val onClick: OnClickSearch
) : RecyclerView.Adapter<CityJsonViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityJsonViewHolder {
        return CityJsonViewHolder(
            onClick,
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