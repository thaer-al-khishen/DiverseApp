package com.example.diverseapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diverseapp.Models.Service
import com.example.diverseapp.R
import com.example.diverseapp.databinding.NotSelectedServiceRowBinding

// Recyclerview for the horizontal recyclerview in the AllCategoriesFragment
class IsNotSelectedServiceAdapter (private val services: List<Service>) :
    RecyclerView.Adapter<IsNotSelectedServiceAdapter.IsNotSelectedServiceViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IsNotSelectedServiceViewHolder {
        return IsNotSelectedServiceViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_not_selected_service,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return services.size
    }

    override fun onBindViewHolder(holder: IsNotSelectedServiceViewHolder, position: Int) {

//        services.takeUnless {
//            !services[position].isSelected
//        }

//        if (!services[position].isSelected) {
////            removeAt(position)
//            holder.binding.parentLayout.visibility = View.INVISIBLE
//        }
//        if (position == 0) {
//            holder.binding.notSelectedServiceImage.setImageResource(R.drawable.mechanicimage)
//        }
//        if (position == 1) {
//            holder.binding.notSelectedServiceImage.setImageResource(R.drawable.maintenanceimage)
//        }
//        if (position == 2) {
//            holder.binding.notSelectedServiceImage.setImageResource(R.drawable.lawnmowingimage)
//        }
//        if (position == 3) {
//            holder.binding.notSelectedServiceImage.setImageResource(R.drawable.vacuumimage)
//        }

//        val params = holder.binding.ourConstraintLayout.layoutParams as ConstraintLayout.LayoutParams
//        params.endToEnd = holder.binding.guideline33percent.id
//
//        params.marginEnd = 8
//        params.marginStart = 8
//        holder.binding.parentLayout.requestLayout()

        holder.binding.notSelectedServiceImage.setImageResource(services[position].imageResource)
        holder.binding.notSelectedServiceName.text = services[position].service_name
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

    class IsNotSelectedServiceViewHolder(val binding: NotSelectedServiceRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(position: Int, service: Service)
    }

//    fun removeAt(position: Int) {
//        services.drop(position)
//        notifyDataSetChanged()
//    }
}