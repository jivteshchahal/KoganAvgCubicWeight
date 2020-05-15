package com.example.koganaveragecubicweight.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.koganaveragecubicweight.R
import com.example.koganaveragecubicweight.view.ui.fragments.AvgCubicWeightFrag

class MainActivity : AppCompatActivity() {
    private lateinit var getDataButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        getDataButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.frag_container, AvgCubicWeightFrag.newInstance())
                .commit()
        }
    }

    private fun initView() {
        getDataButton = findViewById(R.id.btnGetData)
    }
}
