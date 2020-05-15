package com.example.koganaveragecubicweight.service.model

import org.json.JSONObject

class ObjectContentModel(
    val category: String,
    val title: String,
    val weight: String,
    val size: JSONObject
) {
}