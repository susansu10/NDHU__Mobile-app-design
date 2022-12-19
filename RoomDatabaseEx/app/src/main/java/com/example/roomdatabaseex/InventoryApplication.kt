package com.example.roomdatabaseex

import android.app.Application
import com.example.roomdatabaseex.data.ItemRoomDatabase

// for getting a single database instance after launching the application

class InventoryApplication: Application() {
    val database: ItemRoomDatabase by lazy {
        ItemRoomDatabase.getDatabase(this)
    }
}