package com.example.androidgrocery

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Using database to store the list of groceries added by the user
@Database(entities = [Grocerylist::class], version = 1)

//Creating an abstract class
abstract class GroceryDatabase :RoomDatabase(){
    abstract fun getGroceryItemsDao():GroceryItemsDao

    //Using implicit intent
    companion object{
        @Volatile
        private var instance:GroceryDatabase?=null
        private val LOCK=Any()

        operator fun invoke(context:Context)= instance?: synchronized(LOCK){
            instance?: createDb(context).also {
                instance=it
            }
        }

//Building database
 private fun createDb(context: Context)=
     Room.databaseBuilder(context.applicationContext,GroceryDatabase::class.java,"Grocery.db").build()
}}