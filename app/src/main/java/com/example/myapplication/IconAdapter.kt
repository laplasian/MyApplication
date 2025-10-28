package com.example.myapplication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

private const val ITEM_VIEW_TYPE_TITLE = 0
private const val ITEM_VIEW_TYPE_ICON = 1

sealed class ItemBase{
    data class IconItem(
        val name: String,
        val status: String,
        val iconUrl: String,
    ) : ItemBase()

    data class TitleItem(
        val title: String
    ) : ItemBase()

}
class IconAdapter(private var items: List<ItemBase>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.icon_image_view)
        val nameTextView: TextView = itemView.findViewById(R.id.character_name)
        val statusTextView: TextView = itemView.findViewById(R.id.character_status)
    }
    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ItemBase.IconItem -> ITEM_VIEW_TYPE_ICON
            is ItemBase.TitleItem -> ITEM_VIEW_TYPE_TITLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_VIEW_TYPE_TITLE -> {
                val view = inflater.inflate(R.layout.icon_title, parent, false)
                TitleViewHolder(view)
            }
            ITEM_VIEW_TYPE_ICON -> {
                val view = inflater.inflate(R.layout.icon_card_item, parent, false)
                IconViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val currentItem = items[position]) {
            is ItemBase.IconItem -> {
                val iconHolder = holder as IconViewHolder
                iconHolder.nameTextView.text = currentItem.name
                iconHolder.statusTextView.text = currentItem.status
                iconHolder.iconImageView.load(currentItem.iconUrl)
            }
            is ItemBase.TitleItem -> {
                val titleHolder = holder as TitleViewHolder
                titleHolder.titleTextView.text = currentItem.title
            }
        }
    }

    fun updateData(newItems: List<ItemBase>) {
        items = newItems
        notifyDataSetChanged()
    }
}
