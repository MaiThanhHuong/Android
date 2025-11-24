package com.example.play_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCategories = findViewById<RecyclerView>(R.id.rvCategories)

        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = CategoryAdapter(fakeData())
        rvCategories.setHasFixedSize(true)
    }

    private fun fakeData(): List<Category> {

        val apps1 = listOf(
            AppItem("Garena Liên Quân Mobile", "MOBA", 4.3, "500 MB", R.drawable.lienquan),
            AppItem("My Talking Tom 2", "Giải trí - Casual", 4.5, "150 MB", R.drawable.my_talking_tom),
            AppItem("Candy Crush Saga", "Giải đố Match-3", 4.6, "100 MB", R.drawable.candy_crush_saga),
            AppItem("Magic Garden", "Trang trại", 4.7, "120 MB", R.drawable.magic_garden),
        )

        val apps2 = listOf(
            AppItem("Zalo", "Giao tiếp/Xã hội", 4.5, "180 MB", R.drawable.zalo),
            AppItem("Facebook", "Mạng xã hội", 4.0, "250 MB", R.drawable.facebook),
            AppItem("YouTube", "Xem video", 4.3, "150 MB", R.drawable.youtube),
            AppItem("Spotify", "Nghe nhạc trực tuyến", 4.4, "120 MB", R.drawable.spotify)
        )

        return listOf(
            Category("Suggested for you", apps1, 0),
            Category("Recommended for you", apps2, 1)
        )
    }
}