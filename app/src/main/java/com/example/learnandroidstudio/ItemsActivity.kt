package com.example.learnandroidstudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()

        items.add(Item(1, "_11", "Диван", "dsgdfgertgfsd dfgher", "rtgdfsgf erfg", 1200))
        items.add(Item(2, "_22", "Свет", "wqeqwe", "efrl;wekjroijq c lwekjrojqwer ", 999))
        items.add(Item(3, "_33", "Кухня", "wewsdsadsa werwerfv ", "ewlkjfdk 'dfkjhnihbn ,dsmhyigwjmkmdcyh3987ujd sdmnui3", 3205))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)
    }
}