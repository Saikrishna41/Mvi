package com.example.mvi.model

// domain model, it never changes
data class Blog(
    var id: Int,
    var title: String,
    var body: String,
    var category: String,
    var image: String
)