package com.example.colombiadiversa

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.colombiadiversa.R.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException



class PoiListFragment : Fragment(), Adapter.OnItemClickListener {

    private lateinit var mPoi: ArrayList<PoiItem>
    private lateinit var mAdapter: Adapter
    private lateinit var recycler: RecyclerView


    private var sharingViewModel: SharingViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharingViewModel = ViewModelProvider(requireActivity())[SharingViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(layout.fragment_poi_list, container, false)

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
        mAdapter = Adapter(mPoi,this)
        recycler.adapter = mAdapter
    }

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

    override fun onItemCLick(position: Int) {
        val infoJson = readPoiJsonFile()
        val poiArray = JSONArray(infoJson)
        val infoFromItemClicked = poiArray.getJSONObject(position)
        println(infoFromItemClicked.getString("name"))

        sharingViewModel?.setPoiClickedData(infoFromItemClicked)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        findNavController().navigate(R.id.poiDetailFragment, null, options)
    }

    companion object {
        private val TAG = SingleActivity::class.java.simpleName
    }


}