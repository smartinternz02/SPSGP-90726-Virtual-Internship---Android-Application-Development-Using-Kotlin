package com.example.androidgrocery

//Importing necessary views for recycler view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//Importing files for view components
import android.widget.ImageView
import android.widget.TextView

//importing recycler view package
import androidx.recyclerview.widget.RecyclerView

//Creating an adaptor class
class GroceryRecyclerAdaptor(var list: List<Grocerylist>,
                             val groceryListClickInterface:GroceryListClickInterface
                             ):RecyclerView.Adapter<GroceryRecyclerAdaptor.GroceryViewHolder>() {

    //View holder in recycler view
     inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //Initialising view components on each element in the recycler view
        val itemnamea = itemView.findViewById<TextView>(R.id.itemname)
        val Quantitya = itemView.findViewById<TextView>(R.id.Quantity)
        val ratea = itemView.findViewById<TextView>(R.id.Rate)
        val totalamounta = itemView.findViewById<TextView>(R.id.totalamount)
        val deletebuttona = itemView.findViewById<ImageView>(R.id.Deletebutton)


    }


    interface GroceryListClickInterface {
        fun OnItemClick(grocerylist: Grocerylist)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.groceryrecycler, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.itemnamea.text = list.get(position).itemName
        holder.Quantitya.text = list.get(position).itemNo.toString()
        holder.ratea.text = "Rs. " + list.get(position).itemPrice.toString()
        val itemtotal: Int = list.get(position).itemPrice * list.get(position).itemNo
        holder.totalamounta.text = "Rs." + itemtotal.toString()
        holder.deletebuttona.setOnClickListener {
            groceryListClickInterface.OnItemClick(list.get(position))

        }

    }

    override fun getItemCount(): Int {

        return list.size
    }
}