package com.example.colombiadiversa

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class Adapter(
    private val mPoi: ArrayList<PoiItem>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poi_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, shortDescription, review, image) = mPoi[position]
        holder.nameLabel.text = name
        holder.shortDescriptionLabel.text = shortDescription
        holder.reviewLabel.text = review
        holder.imageLabel.setImageDrawable(holder.itemView.context.getDrawable(holder.itemView.resources.getIdentifier(
            image,
            "drawable",
            holder.itemView.context.packageName
        )))
    }

    override fun getItemCount() = mPoi.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameLabel: TextView = itemView.findViewById(R.id.textView_name)
        var shortDescriptionLabel: TextView = itemView.findViewById(R.id.textView_shortDescription)
        var reviewLabel: TextView = itemView.findViewById(R.id.textView_review)
        var imageLabel: ImageView = itemView.findViewById(R.id.imageView2)
    }
}
