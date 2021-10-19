package com.example.colombiadiversa

import android.content.Intent
import android.os.Bundle
import android.system.Os.open
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import android.view.Menu
import android.view.MenuItem



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PoiListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PoiListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mPoi: ArrayList<PoiItem>
    private lateinit var mAdapter: Adapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /*val binding = DataBindingUtil.inflate<FragmentPoiListBinding>(inflater,
            R.layout.fragment_poi_list,container,false)*/

        return inflater.inflate(R.layout.fragment_poi_list, container, false)
        // return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.frag_poi_list)
        setupRecyclerView()
        generatePoi()

    }

    private fun setupRecyclerView() {
        mPoi = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        )
        mAdapter = Adapter(mPoi)
        recycler.adapter = mAdapter
    }

    /**
     * Generates mock contact data to populate the UI from a JSON file in the
     * assets directory, called from the options menu.
     */
    private fun generatePoi() {
        val poiString = readPoiJsonFile()
        try {
            val poiJson = JSONArray(poiString)
            for (i in 0 until poiJson.length()) {
                val poiJson = poiJson.getJSONObject(i)
                val poi = PoiItem(
                    poiJson.getString("name"),
                    poiJson.getString("shortDescription"),
                    poiJson.getString("review"),
                    poiJson.getString("image")
                )
                Log.d(TAG, "generateContacts: $poi")
                mPoi.add(poi)
            }
            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * Reads a file from the assets directory and returns it as a string.
     *
     * @return The resulting string.
     */
    private fun readPoiJsonFile(): String? {
        var poiString: String? = null
        try {
            val inputStream = context?.assets?.open("poi.json")
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            inputStream?.read(buffer)
            inputStream?.close()
            poiString = buffer?.let { String(it) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return poiString
    }

    companion object {
        private val TAG = SingleActivity::class.java.simpleName
    }

/*    companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PoiListFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PoiListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/

}