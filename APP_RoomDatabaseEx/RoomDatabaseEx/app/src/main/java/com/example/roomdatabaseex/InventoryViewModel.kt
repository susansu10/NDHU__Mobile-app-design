package com.example.roomdatabaseex

import androidx.lifecycle.*
import com.example.roomdatabaseex.data.Item
import com.example.roomdatabaseex.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao): ViewModel() {

    // declare an LiveData to hold all items in the database
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    /**
     * Inserts the new Item into database.
     */
    fun addNewUser(userName: String,userAge: String) {
        val newUser = getNewItemEntry(userName, userAge)
        insertUser(newUser)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertUser(user: Item) {
        viewModelScope.launch {
            itemDao.insert(user)
        }
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(userName: String, userAge: String): Item {
        return Item(
            userName = userName,
            userAge = userAge.toInt(),
        )
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(userName: String, userAge: String): Boolean {
        if (userName.isBlank() || userAge.isBlank()) {
            return false
        }
        return true
    }
}


/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}