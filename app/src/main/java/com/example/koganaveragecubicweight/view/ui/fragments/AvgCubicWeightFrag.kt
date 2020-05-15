@file:Suppress("DEPRECATION")

package com.example.koganaveragecubicweight.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koganaveragecubicweight.R
import com.example.koganaveragecubicweight.service.model.RvDataShowModel
import com.example.koganaveragecubicweight.view.adapters.CubicWeightRvAdapter
import com.example.koganaveragecubicweight.viewModel.AvgCubicWeightViewModel

class AvgCubicWeightFrag : Fragment() {
    private lateinit var viewModel: AvgCubicWeightViewModel
    private val dataList: MutableList<RvDataShowModel> = ArrayList()
    private lateinit var mAdapter: CubicWeightRvAdapter
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = AvgCubicWeightFrag()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.avg_cubic_weight_fragment, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.rcViewData)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AvgCubicWeightViewModel::class.java)
        viewModel.init()
        viewModel.getContacts(activity!!, getString(R.string.api_ul_postfix))
            .observe(activity!!, Observer {
                for (jsonData in it) {
                    val title = jsonData.title
                    val category = jsonData.category
//                    val weight = jsonData.weight
                    val width = jsonData.size.width
                    val length = jsonData.size.length
                    val height = jsonData.size.height
                    val volume = (width / 10) * (length / 10) * (height / 10)
                    if (category == "Air Conditioners") {
                        val cubicWeight = (volume * 250)
                        dataList.add(
                            RvDataShowModel(
                                title,
                                category,
                                cubicWeight.toString() + "kg"
                            )
                        )
                    }
                }
                mAdapter = CubicWeightRvAdapter(dataList)
                recyclerView.adapter = mAdapter
            })

    }
}
