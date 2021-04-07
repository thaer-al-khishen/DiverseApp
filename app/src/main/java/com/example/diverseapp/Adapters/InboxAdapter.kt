package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.R
import com.example.diverseapp.databinding.InboxRowBinding

//Adapter for the recyclerview in the InboxFragment
class InboxAdapter (private val items: List<String>) :
    RecyclerView.Adapter<InboxAdapter.InboxViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        return InboxViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_inbox,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.inboxIcon.setImageResource(R.drawable.ic_home)
            holder.binding.inboxMessageTextView.text = "I will gonna your home"
            holder.binding.inboxDateTextView.text = "12 mins ago"
        }
        if (position == 1) {
            holder.binding.inboxIcon.setImageResource(R.drawable.ic_profile)
            holder.binding.inboxMessageTextView.text = "Yes I'm"
            holder.binding.inboxDateTextView.text = "3 days ago"
        }
        if (position == 2) {
            holder.binding.inboxIcon.setImageResource(R.drawable.ic_todo)
            holder.binding.inboxMessageTextView.text = "Ok, thanks you"
            holder.binding.inboxDateTextView.text = "May 15 2020"
        }
        if (position == 3) {
            holder.binding.inboxIcon.setImageResource(R.drawable.ic_settings)
            holder.binding.inboxMessageTextView.text = "How can I call you?"
            holder.binding.inboxDateTextView.text = "Jan 30 2020"
        }
        if (position == 4) {
            holder.binding.inboxIcon.setImageResource(R.drawable.ic_chat_bubble_outline)
            holder.binding.inboxMessageTextView.text = "See you next time"
            holder.binding.inboxDateTextView.text = "Jan 23 2020"
        }

        holder.binding.inboxNameTextView.text = items[position]
        holder.binding.settingLayout.setOnClickListener {
            listener?.onItemClicked(items[position])
        }
    }

    class InboxViewHolder(val binding: InboxRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(setting: String)
    }
}