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
    private val mPoi: ArrayList<PoiItem>,
    private val onclick: (PoiItem) -> Unit
    //param: (Any) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poi_list_item, parent, false)
        return ViewHolder(view)
    }


   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val poi = mPoi[position]
        holder.bind(poi = poi)

    }

    override fun getItemCount() = mPoi.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameLabel: TextView = itemView.findViewById(R.id.textView_name)
        var shortDescriptionLabel: TextView = itemView.findViewById(R.id.textView_shortDescription)
        var reviewLabel: TextView = itemView.findViewById(R.id.textView_review)
        var imageLabel: ImageView = itemView.findViewById(R.id.imageView2)
        var currentPoi: PoiItem? = null

        init {
            itemView.setOnClickListener {
                currentPoi?.let {
                    onclick(it)
                }
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind (poi:PoiItem){
           currentPoi = poi

            nameLabel.text = poi.name
            shortDescriptionLabel.text = poi.shortDescription
            reviewLabel.text = poi.review
            imageLabel.setImageDrawable(itemView.context.getDrawable(itemView.resources.getIdentifier(
                poi.image,
                "drawable",
                itemView.context.packageName)))




        }

    }

}


