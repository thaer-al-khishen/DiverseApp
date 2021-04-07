package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.Models.Service
import com.example.diverseapp.R
import com.example.diverseapp.databinding.BubbleServiceRowBinding
import com.example.diverseapp.databinding.NotSelectedServiceGridRowBinding
import com.example.diverseapp.databinding.NotSelectedServiceRowBinding
import com.example.diverseapp.databinding.SelectedServiceWithoutIconRowBinding

// Adapter for the bottom recyclerview in the AllCategoriesFragment that pops up in the slider
class SliderServiceAdapter (private val services: List<Service>) :
    RecyclerView.Adapter<SliderServiceAdapter.SliderServiceViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderServiceViewHolder {
        return SliderServiceViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_bubble_service,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return services.size
    }

    override fun onBindViewHolder(holder: SliderServiceViewHolder, position: Int) {

        if (position == 0) {
            holder.binding.bubbleServiceImage.setImageResource(R.drawable.mechanicimage)
        }
        if (position == 1) {
            holder.binding.bubbleServiceImage.setImageResource(R.drawable.maintenanceimage)
        }
        if (position == 2) {
            holder.binding.bubbleServiceImage.setImageResource(R.drawable.lawnmowingimage)
        }
        if (position == 3) {
            holder.binding.bubbleServiceImage.setImageResource(R.drawable.vacuumimage)
        }
        if (position == 4) {
            holder.binding.bubbleServiceImage.setImageResource(R.drawable.vacuumimage)
        }
        if (position == 5) {
            holder.binding.bubbleServiceImage.setImageResource(R.drawable.vacuumimage)
        }

        holder.binding.bubbleServiceName.text = services[position].service_name
        holder.binding.parentLayout.setOnClickListener {
            listener?.onItemClicked(position, services[position])
//            if(services[position].isSelected) {
//                services[position].isSelected = false
//
//            }
//            if(!services[position].isSelected) {
//                services[position].isSelected = true
//            }
        }
    }

    class SliderServiceViewHolder(val binding: BubbleServiceRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(position: Int, service: Service)
    }

}