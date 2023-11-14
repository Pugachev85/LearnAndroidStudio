package com.example.learnandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val image: ImageView = findViewById(R.id.item_card_image)
        val title: TextView = findViewById(R.id.item_card_title)
        val text: TextView = findViewById(R.id.item_card_text)
        val price: TextView = findViewById(R.id.item_card_price)

        val imageId = resources.getIdentifier(
            intent.getStringExtra("itemImage"),
            "drawable",
            this.packageName
        )

        image.setImageResource(imageId)
        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")
        price.text = intent.getStringExtra("itemPrice") + " $"
    }
}