package com.example.androidgrocery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="grocery_items")
data class Grocerylist (
    //Creating Name column
    @ColumnInfo(name="itemName")
    var itemName:String,

            //Creating quantity column
    @ColumnInfo(name="itemNo")
    var itemNo:Int,

                    //Creating price column
    @ColumnInfo(name="itemPrice")
    var itemPrice:Int,

    ){

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}