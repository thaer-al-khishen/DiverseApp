package com.example.diverseapp.Adapters

import com.example.diverseapp.Models.Card
import com.example.diverseapp.R
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.Models.Ruling
import com.example.diverseapp.databinding.CardRowBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_card.view.*

// Not included in the project
class VerticalCardsAdapter(private val cards: ArrayList<Card>, val iCardsItemSelected: ICardsItemSelected) :
    RecyclerView.Adapter<VerticalCardsAdapter.CardsItemViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()
    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsItemViewHolder {
        return CardsItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardsItemViewHolder, position: Int) {
        val model = cards[position]
        val innerClass = cards[position].rulings
        holder.binding.nameText.text = "Name: " + model.name
        holder.binding.artistText.text = "Artist: " + model.artist
        holder.binding.rarityText.text = "Rarity: " + model.rarity
        holder.binding.powerText.text = "Power: " + model.power
        holder.binding.toughnessText.text = "Toughness: " + model.toughness
        holder.binding.flavorText.text = "Flavor: " + model.flavor
//        var nearby: List<Ruling> = ArrayList()
////        nearby.size
//        nearby = model.rulings
////        for (i in 0 until nearby.length()) {
////            val item = nearby.getString(i)
////            // use item
////        }
//        for (item in nearby) {
//            holder.binding.flavorText.text = "Flavor: " + item + "/n"
//        }

        if (!TextUtils.isEmpty(model.imageUrl))
            Picasso.get().load(model.imageUrl).placeholder(R.drawable.news_ic_placeholder)
                .into(holder.binding.categoryDetailsAllImage)
        holder.binding.parentLayout.setOnClickListener {
            iCardsItemSelected.onCardsItemsClicked(position)
        }

        val childLayoutManager = LinearLayoutManager(holder.itemView.rvSub.context, RecyclerView.HORIZONTAL, false)

        holder.itemView.rvSub.apply {
            layoutManager = childLayoutManager
            adapter = SubAdapter(cards.get(position))
            setRecycledViewPool(viewPool)
        }
    }


    class CardsItemViewHolder(val binding: CardRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ICardsItemSelected {
        fun onCardsItemsClicked(position: Int)
    }
}
