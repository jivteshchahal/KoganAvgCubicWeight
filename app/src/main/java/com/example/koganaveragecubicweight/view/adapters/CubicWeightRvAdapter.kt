package com.example.koganaveragecubicweight.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.koganaveragecubicweight.R
import com.example.koganaveragecubicweight.service.model.RvDataShowModel

class CubicWeightRvAdapter(private val mValues: List<RvDataShowModel>) :
    RecyclerView.Adapter<CubicWeightRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.data_rv_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: CubicWeightRvAdapter.ViewHolder, position: Int) {
        //set data
        holder.tvTitle.text = mValues[position].title
        holder.tvCategory.text = mValues[position].category
        holder.tvAvgCubicWeight.text = mValues[position].avgCubeVol
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        //view fetch
        val tvTitle: TextView = mView.findViewById(R.id.tvTitle)
        val tvCategory: TextView = mView.findViewById(R.id.tvCategory)
        val tvAvgCubicWeight: TextView = mView.findViewById(R.id.tvAvgCubeWeight)
    }
}