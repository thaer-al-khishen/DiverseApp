package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.R
import com.example.diverseapp.databinding.MovingRowBinding
import com.example.diverseapp.databinding.RecommendedRowBinding

// Adapter for the recyclerview under the moving into a new home textview
class MovingAdapter(private val recommended: List<String>) :
    RecyclerView.Adapter<MovingAdapter.MovingViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovingViewHolder {
        return MovingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_moving,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return recommended.size
    }

    override fun onBindViewHolder(holder: MovingViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.recommendedImage.setImageResource(R.drawable.ic_home)
            holder.binding.recommendedPrice.text = "$45 - $146"
//            holder.binding.profileLayout.setOnClickListener {
//                listener?.onItemClicked(items[0])
//            }
        }
        if (position == 1) {
            holder.binding.recommendedImage.setImageResource(R.drawable.ic_profile)
            holder.binding.recommendedPrice.text = "$60 - $125"
        }
        if (position == 2) {
            holder.binding.recommendedImage.setImageResource(R.drawable.ic_todo)
            holder.binding.recommendedPrice.text = "$70 - $130"
        }
        if (position == 3) {
            holder.binding.recommendedImage.setImageResource(R.drawable.ic_settings)
            holder.binding.recommendedPrice.text = "$80 - $110"
        }

        holder.binding.recommendedTitle.text = recommended[position]
//        holder.binding.profileLayout.setOnClickListener {
//            listener?.onItemClicked(categories[position])
//        }
    }

    class MovingViewHolder(val binding: MovingRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(setting: String)
    }
}