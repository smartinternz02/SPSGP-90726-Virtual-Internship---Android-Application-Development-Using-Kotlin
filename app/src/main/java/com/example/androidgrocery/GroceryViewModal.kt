package com.example.androidgrocery

//Imports necessary for the view model class
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//Creating a view model class
class GroceryViewModal(private val repository: GroceryRepository):ViewModel() {

    //Inserted groceries
    fun insertitem(items:Grocerylist)=GlobalScope.launch{
        repository.insertitem(items)
    }

    //Deleted groceries
    fun deleteitem(items:Grocerylist)=GlobalScope.launch{
        repository.deleteitem(items)
    }

    //List of groceries
   fun getthelistofgroceries()=repository.getthelistofgrocery()
}