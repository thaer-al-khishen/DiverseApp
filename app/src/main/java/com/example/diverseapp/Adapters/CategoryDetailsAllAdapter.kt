package com.example.diverseapp.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.R
import com.example.diverseapp.databinding.CardDetailsAllBinding
import com.example.diverseapp.databinding.InboxRowBinding

//Recyclerview for the XategoryDetailsActivity
class CategoryDetailsAllAdapter (private val items: List<String>) :
    RecyclerView.Adapter<CategoryDetailsAllAdapter.CategoryDetailsAllViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailsAllViewHolder {
        return CategoryDetailsAllViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_category_details_all,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CategoryDetailsAllViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.categoryDetailsAllImage.setImageResource(R.drawable.stevejobsimage)
        }
        if (position == 1) {
            holder.binding.categoryDetailsAllImage.setImageResource(R.drawable.stevejobsimage)
            holder.binding.categoryDetailsAllCostAmount.text = "$32"
            holder.binding.categoryDetailsAllCostText.text = "Starting Cost"
            holder.binding.inHighDemandTextView.text = "Offers Remote Available"
            holder.binding.inHighDemandTextView.setBackgroundColor(Color.parseColor("#7CFC00"))
            holder.binding.in2HighDemandTextView.visibility = View.GONE
        }

        holder.binding.categoryDetailsAllName.text = items[position]
        holder.binding.categoryDetailsAllCard.setOnClickListener {
            listener?.onItemClicked(items[position])
        }
        if (position == 2) {
            holder.binding.categoryDetailsAllImage.setImageResource(R.drawable.stevejobsimage)
            holder.binding.categoryDetailsAllCostAmount.text = "$55"
        }
        if (position == 3) {
            holder.binding.categoryDetailsAllImage.setImageResource(R.drawable.stevejobsimage)
        }
        if (position == 4) {
            holder.binding.categoryDetailsAllImage.setImageResource(R.drawable.stevejobsimage)
        }
    }

    class CategoryDetailsAllViewHolder(val binding: CardDetailsAllBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(setting: String)
    }
}