package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.R
import com.example.diverseapp.databinding.ProfileRowBinding

// Adapter for the second recyclerview in the profile fragment
class SecondProfileAdapter (private val items: List<String>) :
    RecyclerView.Adapter<SecondProfileAdapter.SettingViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        return SettingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_profile,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.profileIcon.setImageResource(R.drawable.ic_home)
        }
        if (position == 1) {
            holder.binding.profileIcon.setImageResource(R.drawable.ic_profile)
        }
        if (position == 2) {
            holder.binding.profileIcon.setImageResource(R.drawable.ic_todo)
        }
        if (position == 3) {
            holder.binding.profileIcon.setImageResource(R.drawable.ic_settings)
            holder.binding.customSeparator.visibility = View.GONE
        }

        holder.binding.profileTextView.text = items[position]
        holder.binding.profileLayout.setOnClickListener {
            listener?.onItemClicked(items[position])
        }
    }

    class SettingViewHolder(val binding: ProfileRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(setting: String)
    }
}