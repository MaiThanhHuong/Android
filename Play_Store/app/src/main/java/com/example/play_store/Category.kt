package com.example.play_store

data class Category(
    val title: String,
    val apps: List<AppItem>,
    val type: Int
)