package com.example.koganaveragecubicweight.view.ui.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.koganaveragecubicweight.R
import com.example.koganaveragecubicweight.view.ui.fragments.AvgCubicWeightFrag

class MainActivity : AppCompatActivity() {
    private lateinit var getDataButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, AvgCubicWeightFrag.newInstance())
            .commit()
//        getDataButton.setOnClickListener {
//
//        }
    }

    private fun initView() {
//        getDataButton = findViewById(R.id.btnGetData)
    }
}
