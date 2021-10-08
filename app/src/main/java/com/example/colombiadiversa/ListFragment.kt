package com.example.colombiadiversa

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {

       private lateinit var mPoi: ArrayList<PoiItem>
       private lateinit var mAdapter: Adapter
       private lateinit var recycler: RecyclerView
       private lateinit var model: PoiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(requireActivity()).get(PoiViewModel::class.java)

        recycler = view.findViewById(R.id.poi_list)
        setupRecyclerView()
    }

    /**
     * Sets up the RecyclerView: empty data set, item dividers, swipe to delete.
     */
    private fun setupRecyclerView() {
        mPoi = createMockContacts()
        recycler.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        mAdapter = Adapter(mPoi) { PoiItem ->
            contactOnClick(PoiItem)
        }

        recycler.adapter = mAdapter
    }

            private fun contactOnClick(poi: PoiItem) {
            Log.d(TAG, "Click on: $poi")
            model.select(poi)
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)

            }


    private fun createMockContacts(): java.util.ArrayList<PoiItem> {
        return arrayListOf(
            PoiItem("Río Guejar", "Maravilla natural. Ecoturismo.", "Puntuación: 4/5", "guejar"),
            PoiItem("Caño Cristales", "Maravilla natural. Ecoturismo.", "Puntuación: 4/5", "cristales")
        )
    }

    companion object {
        private val TAG = ListFragment::class.java.simpleName
    }






}