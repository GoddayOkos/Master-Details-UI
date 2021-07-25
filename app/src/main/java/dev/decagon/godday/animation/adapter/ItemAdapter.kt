package dev.decagon.godday.animation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.decagon.godday.animation.R
import dev.decagon.godday.animation.model.Contacts

class ItemAdapter(
    private val dataSet: List<Contacts>, private val listener: ItemAdapterListener
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val view: View): View.OnClickListener, RecyclerView.ViewHolder(view) {
        private lateinit var contact: Contacts

        init {
            view.setOnClickListener(this)
        }

        fun bind(contact: Contacts) {
            this.contact = contact
            val imageView: ImageView = view.findViewById(R.id.profileImage)
            val textView: TextView = view.findViewById(R.id.fullName)
            imageView.setImageResource(contact.imageResourceId)
            textView.text = contact.fullName
        }

        override fun onClick(view: View) {
          listener.onContactSelected(view, contact)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    interface ItemAdapterListener {
        fun onContactSelected(view: View, contact: Contacts)
    }
}