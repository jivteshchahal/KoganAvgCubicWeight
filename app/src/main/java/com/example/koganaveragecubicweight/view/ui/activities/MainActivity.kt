package com.example.koganaveragecubicweight.view.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.koganaveragecubicweight.R
import com.example.koganaveragecubicweight.view.ui.fragments.AvgCubicWeightFrag

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //fragment set up
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, AvgCubicWeightFrag.newInstance())
            .commit()
    }
}
