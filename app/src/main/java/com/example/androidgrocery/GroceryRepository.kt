package com.example.androidgrocery

//Creating the repository for the grocery database
class GroceryRepository(private val db:GroceryDatabase) {

    //Function for adding new grocery
    suspend fun insertitem(items:Grocerylist)=db.getGroceryItemsDao().insertitem(items)

    //Function for deleting a grocery
    suspend fun deleteitem(items:Grocerylist)=db.getGroceryItemsDao().deleteitem(items)

    //Function to get the list of groceries
    fun getthelistofgrocery()=db.getGroceryItemsDao().getthelistofgrocery()
}