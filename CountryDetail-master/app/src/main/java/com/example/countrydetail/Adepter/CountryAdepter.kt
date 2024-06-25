import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countrydetail.Activity.Countrydetail
import com.example.countrydetail.Model.Countrymodel
import com.example.countrydetail.R
import com.google.gson.Gson

class CountryAdepter(data: ArrayList<Countrymodel>) :
    RecyclerView.Adapter<CountryAdepter.Countryholder>() {


    var list = data

    lateinit var context: Context

    class Countryholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.countryimg)
        var name: TextView = itemView.findViewById(R.id.countryname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Countryholder {
        context = parent.context
        return Countryholder(
            LayoutInflater.from(parent.context).inflate(R.layout.first_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Countryholder, position: Int) {
        holder.name.text = list.get(position).name

        Glide.with(holder.itemView.context)
            .load(list.get(position).flags?.svg)
            .into(holder.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Countrydetail::class.java)
            val gson = Gson()
            val countryJson = gson.toJson(list.get(position))
            intent.putExtra("country", countryJson)
            context.startActivity(intent)
        }
    }
}