package com.example.androidgrocery
//Importing Necessary files
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//For the dialog window to add new grocery in the app
import android.app.Dialog

//Layout Components
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider

//Presenting the list of groceries
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//Add grocery sign
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryRecyclerAdaptor.GroceryListClickInterface{
 //Variables involving other files of the project
    lateinit var itemsRV:RecyclerView
    lateinit var add:FloatingActionButton
    lateinit var list:List<Grocerylist>
    lateinit var groceryRecyclerAdaptor: GroceryRecyclerAdaptor
    lateinit var groceryViewModal:GroceryViewModal


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Views on the app UI
        itemsRV = findViewById(R.id.recview)
        add = findViewById(R.id.addb)

        //Getting the requirements for Recycler View
        list = ArrayList<Grocerylist>()
        groceryRecyclerAdaptor = GroceryRecyclerAdaptor(list, this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = groceryRecyclerAdaptor

        //Initialising databases required for storing grocery lists
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModalFactory(groceryRepository)
        groceryViewModal = ViewModelProvider(this, factory).get(GroceryViewModal::class.java)
        groceryViewModal.getthelistofgroceries().observe(
            this,
            {
                groceryRecyclerAdaptor.list = it
                groceryRecyclerAdaptor.notifyDataSetChanged()
            }

        )

        //Using the add button
        add.setOnClickListener {
            openDialog()
        }
    }

    //Code for dialog box to add new grocery
    fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.groceryadddialog)

        //View components on the dialog box
        val cancelbu = dialog.findViewById<Button>(R.id.cancelbutton)
        val addbu = dialog.findViewById<Button>(R.id.add)
        val itemedit = dialog.findViewById<EditText>(R.id.Edittextfield)
        val itempriceedit = dialog.findViewById<EditText>(R.id.Edittextfieldp)
        val itemnoedit = dialog.findViewById<EditText>(R.id.Edittextfieldn)

        //Initialising cancel buttons
        cancelbu.setOnClickListener {
            dialog.dismiss()

        }

        //Initialising add button
        addbu.setOnClickListener {
            val ItemName: String = itemedit.text.toString()
            val ItemPrice: String = itempriceedit.text.toString()
            val ItemNo: String = itemnoedit.text.toString()
            val no: Int = ItemNo.toInt()
            val p: Int = ItemPrice.toInt()
            if (ItemName.isNotEmpty() && ItemPrice.isNotEmpty() && ItemNo.isNotEmpty()) {
                val items = Grocerylist(ItemName, no, p)
                groceryViewModal.insertitem(items)
                Toast.makeText(
                    applicationContext,
                    "Grocery inserted successfully...",
                    Toast.LENGTH_SHORT
                ).show()
                groceryRecyclerAdaptor.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext,"Please enter all of the details....",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()

    }


    override fun OnItemClick(grocerylist: Grocerylist) {
        groceryViewModal.deleteitem(grocerylist)
        groceryRecyclerAdaptor.notifyDataSetChanged()

        Toast.makeText(applicationContext,"Grocery removed successfully",Toast.LENGTH_SHORT).show()
    }


}
