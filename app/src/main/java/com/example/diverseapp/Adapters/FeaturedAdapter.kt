package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.R
import com.example.diverseapp.databinding.FeaturedRowBinding
import com.example.diverseapp.databinding.MovingRowBinding

// Adapter for the recyclerview in the AboutActivity below the featured projects textview
class FeaturedAdapter (private val featuredList: List<String>) :
    RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        return FeaturedViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_featured,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return featuredList.size
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.featuredImage.setImageResource(R.drawable.adapterimage)
            holder.binding.featuredPrice.text = "Approx. $50"
//            holder.binding.profileLayout.setOnClickListener {
//                listener?.onItemClicked(items[0])
//            }
        }
        if (position == 1) {
            holder.binding.featuredImage.setImageResource(R.drawable.adapterimage)
            holder.binding.featuredPrice.text = "Approx. $60"
        }
        if (position == 2) {
            holder.binding.featuredImage.setImageResource(R.drawable.adapterimage)
            holder.binding.featuredPrice.text = "Approx. $70"
        }
        if (position == 3) {
            holder.binding.featuredImage.setImageResource(R.drawable.adapterimage)
            holder.binding.featuredPrice.text = "Approx. $80"
        }

        holder.binding.featuredTitle.text = featuredList[position]
//        holder.binding.profileLayout.setOnClickListener {
//            listener?.onItemClicked(categories[position])
//        }
    }

    class FeaturedViewHolder(val binding: FeaturedRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(setting: String)
    }
}