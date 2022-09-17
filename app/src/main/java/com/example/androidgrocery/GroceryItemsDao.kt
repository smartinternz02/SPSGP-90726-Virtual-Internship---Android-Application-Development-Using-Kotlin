package com.example.androidgrocery

//Making necessary imports
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//Creating data access object for grocery database
@Dao
interface GroceryItemsDao {

    //Inserting new grocery into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertitem(item:Grocerylist)

    //Removing grocery from the database
    @Delete
    suspend fun deleteitem(item:Grocerylist)

    //Creating a query
    @Query("Select * FROM Grocery_items")
    fun getthelistofgrocery():LiveData<List<Grocerylist>>
}