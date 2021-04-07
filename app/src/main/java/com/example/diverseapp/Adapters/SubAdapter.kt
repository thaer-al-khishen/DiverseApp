package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.Models.Card
import com.example.diverseapp.R
import kotlinx.android.synthetic.main.list_item_sub.view.*

//Not included in the project
class SubAdapter(
    val card: Card

) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return card.rulings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rulingsText?.text = card.rulings.get(position).date
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_sub, parent, false))
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rulingsText = view.rulingsText
}