package com.example.androidgrocery

//Making necessary imports
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//Creating view modal factory class
class GroceryViewModalFactory(
    private val repository: GroceryRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel>
            create(modelClass: Class<T>): T {
        return GroceryViewModal(repository) as T
    }
}