package com.example.inventorystarter

import android.app.Application
import com.example.inventorystarter.data.ItemRoomDatabase

// for getting a single database instance after launching the application

class InventoryApplication: Application() {
    val database: ItemRoomDatabase by lazy {
        ItemRoomDatabase.getDatabase(this)
    }
}