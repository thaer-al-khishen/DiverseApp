package com.example.diverseapp.Adapters

//import com.zxy.tiny.Tiny
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diverseapp.R
import com.example.diverseapp.databinding.CategoriesRowBinding

//Recyclerview adapter for the first horizontal recyclerview in the home fragment
class CategoriesAdapter (private val categories: List<String>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var listener: ISettingSelected? = null

    fun setOnSettingSelectedListener(listener: ISettingSelected) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_categories,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val myOptions = RequestOptions()
            .override(100, 100)
//        val bitmap = Bitmap.createScaledBitmap(holder.binding.categoryImage, width, height, true)
//        Tiny.getInstance().init(MyApplication);
//        val compressedImage = Builder(this)
//            .setMaxWidth(640)
//            .setMaxHeight(480)
//            .setQuality(75)
//            .setCompressFormat(Bitmap.CompressFormat.WEBP)
//            .setDestinationDirectoryPath(
//                Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES
//                ).getAbsolutePath()
//            )
//            .build()
//            .compressToFile(holder.binding.categoryImage)

        if (position == 0) {
//            holder.binding.categoryImage.setImageResource(R.drawable.ic_home)
            holder.binding.parentLayout.setOnClickListener {
//                listener?.onItemClicked(items[0])
//                val b: Bitmap = holder.binding.categoryImage.getDrawingCache()
//                Log.v("Your Bitmap Width", holder.binding.categoryImage.width.toString())
//                Log.v("Your Bitmap Height", holder.binding.categoryImage.height.toString())
//                Toast.makeText(holder.binding.parentLayout.getContext(),"this is toast message",Toast.LENGTH_LONG).show()
            }
        }
        if (position == 1) {
//            holder.binding.categoryImage.setImageResource(R.drawable.ic_profile)
        }
        if (position == 2) {
//            holder.binding.categoryImage.setImageResource(R.drawable.ic_todo)
        }
        if (position == 3) {
//            holder.binding.categoryImage.setImageResource(R.drawable.ic_settings)
        }
        if (position == 4) {
//            holder.binding.categoryImage.setImageResource(R.drawable.ic_home)
        }

        Glide.with(holder.itemView.context)
            .asBitmap()
            .apply(myOptions)
            .load("http://labs3.kentooz.com/simplex/wp-content/uploads/2013/04/Nature-Wallpaper-99.jpg")
            .into(holder.binding.categoryImage)

        val bitmapWidth = holder.binding.categoryImage.width
        val bitmapHeight = holder.binding.categoryImage.height

//        Log.v("Your Bitmap Width", holder.binding.categoryImage)
//        Log.v("Your Bitmap Height", bitmapHeight.toString())

        holder.binding.categoryNameText.text = categories[position]
        holder.binding.parentLayout.setOnClickListener {
            listener?.onItemClicked(categories[position])
        }
    }

    class CategoriesViewHolder(val binding: CategoriesRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface ISettingSelected {
        fun onItemClicked(setting: String)
    }
}