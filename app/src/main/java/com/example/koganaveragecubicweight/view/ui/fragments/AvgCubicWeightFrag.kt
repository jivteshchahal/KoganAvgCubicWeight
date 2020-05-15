package com.example.koganaveragecubicweight.view.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

import com.example.koganaveragecubicweight.R
import com.example.koganaveragecubicweight.service.model.ObjectContentModel
import com.example.koganaveragecubicweight.view.adapters.CubicWeightRvAdapter
import com.example.koganaveragecubicweight.viewModel.AvgCubicWeightViewModel

class AvgCubicWeightFrag : Fragment() {
    private lateinit var viewModel: AvgCubicWeightViewModel
    private var dataList: List<ObjectContentModel> = ArrayList()
    private lateinit var mAdapter: CubicWeightRvAdapter

    companion object {
        fun newInstance() = AvgCubicWeightFrag()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.avg_cubic_weight_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rcViewData)
//        mAdapter = CubicWeightRvAdapter(dataList)
//        recyclerView.adapter =mAdapter
//
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AvgCubicWeightViewModel::class.java)
        viewModel.init()
        viewModel.getContacts(activity!!, "products/1").observe(activity!!, Observer {
            dataList = it
        })
    }
}
