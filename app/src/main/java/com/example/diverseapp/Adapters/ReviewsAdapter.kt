package com.example.diverseapp.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.R
import com.example.diverseapp.databinding.CardDetailsAllBinding
import com.example.diverseapp.databinding.CardReviewsBinding

class ReviewsAdapter  (private val items: List<String>) :
    RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        return ReviewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_reviews,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.reviewUserImage.setImageResource(R.drawable.adapterimage)
            holder.binding.reviewCategory.text = "Lawn Mowing and Trimming"
            holder.binding.reviewDetailsTextView.text = "What a great concept and value. I have wasted weeks trying interviewing"
        }
        if (position == 1) {
            holder.binding.reviewUserImage.setImageResource(R.drawable.adapterimage)
            holder.binding.reviewCategory.text = "Lawn Mowing and Trimming"
            holder.binding.reviewDetailsTextView.text = "Super easy to work with and get connected to a professional. My lawn looks awesome now!"
        }
        if (position == 2) {
            holder.binding.reviewUserImage.setImageResource(R.drawable.adapterimage)
            holder.binding.reviewCategory.text = "Lawn Mowing and Trimming"
            holder.binding.reviewDetailsTextView.text = "Great!!\nI'll be booking next time."
            holder.binding.customSeparator.visibility = View.GONE
        }

        holder.binding.reviewUserName.text = items[position]
//        holder.binding.reviewsCard.setOnClickListener {
//            listener?.onItemClicked(items[position])
//        }
    }

    class ReviewsViewHolder(val binding: CardReviewsBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(setting: String)
    }
}