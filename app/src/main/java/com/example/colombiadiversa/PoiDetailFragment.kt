package com.example.colombiadiversa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import org.json.JSONArray
import org.json.JSONObject


class PoiDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharingViewModel = ViewModelProvider(requireActivity())[SharingViewModel::class.java]

    }

    private var sharingViewModel: SharingViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poi_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameObserver: Observer<Any> = Observer<Any> { poiInfo ->

            val poiData: JSONObject = poiInfo as JSONObject

            view.findViewById<TextView>(R.id.name).text = poiData.getString("name")
            view.findViewById<ImageView>(R.id.imageView).setImageResource(resources.getIdentifier(
                poiData.getString("image"), "drawable", this.context?.packageName
            ))
            view.findViewById<TextView>(R.id.description).text = poiData.getString("info")
            view.findViewById<TextView>(R.id.location).text = poiData.getString("location")
            view.findViewById<TextView>(R.id.temperature).text = poiData.getString("temperature")
            view.findViewById<TextView>(R.id.activities).text = poiData.getString("activities")
        }

        sharingViewModel!!.getPoiClickedData()?.observe(viewLifecycleOwner, nameObserver)

        view.findViewById<Button>(R.id.button)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_poiDetailFragment_to_poiListFragment, null)
        )

        view.findViewById<Button>(R.id.button2)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_poiDetailFragment_to_mapsActivity, null)
        )

    }

}