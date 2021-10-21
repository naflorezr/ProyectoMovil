package com.example.colombiadiversa

import android.content.Intent
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PoiDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PoiDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

/*        // Get the ViewModel.
        model = ViewModelProviders.of(this).get(NameViewModel::class.java)


        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            nameTextView.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(this, nameObserver)*/

        sharingViewModel = ViewModelProvider(requireActivity())[SharingViewModel::class.java]


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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PoiDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PoiDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

