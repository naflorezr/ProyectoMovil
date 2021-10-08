package com.example.colombiadiversa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var model: PoiViewModel
    private lateinit var nameView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameView = view.findViewById(R.id.name_text)

        // nameView = view.findViewById(R.id.name_text)
        model = ViewModelProvider(requireActivity()).get(PoiViewModel::class.java)
        model.getSelected().observe(viewLifecycleOwner, { PoiItem ->
            nameView.text = PoiItem.name

            //observeLiveData()

        })
    }

}