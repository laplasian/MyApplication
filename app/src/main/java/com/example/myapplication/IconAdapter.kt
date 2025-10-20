package com.example.myapplication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.app.R

class IconAdapter(private val items: List<IconItem>) :
    RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.icon_image_view)
        val nameTextView: TextView = itemView.findViewById(R.id.icon_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon_card_item, parent, false)
        return IconViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.iconImageView.load(item.iconUrl)
    }

}
