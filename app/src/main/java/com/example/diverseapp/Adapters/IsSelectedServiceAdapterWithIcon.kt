package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.Models.Service
import com.example.diverseapp.R
import com.example.diverseapp.databinding.SelectedServiceWithIconRowBinding

// Adapter for the recyclerview in the carousel fragment
// This was a challenge to display the icon of the item in the selected items recyclerview
class IsSelectedServiceAdapterWithIcon (private val services: List<Service>) :
    RecyclerView.Adapter<IsSelectedServiceAdapterWithIcon.IsSelectedServiceViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: IsSelectedServiceAdapterWithIcon.ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IsSelectedServiceViewHolder {
        return IsSelectedServiceViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_selected_service_with_icon,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onBindViewHolder(holder: IsSelectedServiceViewHolder, position: Int) {
//        if (position == 0) {
//            holder.binding.selectedServiceImage.setImageResource(R.drawable.ic_home)
////            holder.binding.profileLayout.setOnClickListener {
////                listener?.onItemClicked(items[0])
////            }
//        }
//        if (position == 1) {
//            holder.binding.selectedServiceImage.setImageResource(R.drawable.ic_profile)
//        }
//        if (position == 2) {
//            holder.binding.selectedServiceImage.setImageResource(R.drawable.ic_todo)
//        }
//        if (position == 3) {
//            holder.binding.selectedServiceImage.setImageResource(R.drawable.ic_settings)
//        }

        holder.binding.categoryIcon.setImageResource(services[position].imageResource)
        holder.binding.selectedServiceName.text = services[position].service_name
        holder.binding.cancelButton.setOnClickListener {
            listener?.onItemClicked(position, services[position])
        }
    }

    class IsSelectedServiceViewHolder(val binding: SelectedServiceWithIconRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(position: Int, service: Service)
    }
}