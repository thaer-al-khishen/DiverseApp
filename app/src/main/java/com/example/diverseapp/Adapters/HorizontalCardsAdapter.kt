package com.example.diverseapp.Adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diverseapp.Models.Card
import com.example.diverseapp.R
import com.example.diverseapp.databinding.CardHorizontalRowBinding

//Not included in the project
class HorizontalCardsAdapter (private val cards: ArrayList<Card>, val iCardsItemSelected: ICardsItemSelected) :
    RecyclerView.Adapter<HorizontalCardsAdapter.CardsItemViewHolder>() {

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsItemViewHolder {
        return CardsItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_horizontal_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardsItemViewHolder, position: Int) {
        val model = cards[position]
        holder.binding.nameText.text = model.name
        val myOptions = RequestOptions()
            .override(100, 100)
        if (!TextUtils.isEmpty(model.imageUrl))
//            Picasso.get().load(model.imageUrl).placeholder(R.drawable.news_ic_placeholder)
//                .into(holder.binding.cardImage)
            Glide.with(holder.itemView.context)
                .asBitmap()
                .apply(myOptions)
                .load(model.imageUrl)
                .into(holder.binding.categoryDetailsAllImage)
        holder.binding.parentLayout.setOnClickListener {
            iCardsItemSelected.onCardsItemsClicked(position)
        }
    }


    class CardsItemViewHolder(val binding: CardHorizontalRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ICardsItemSelected {
        fun onCardsItemsClicked(position: Int)
    }
}