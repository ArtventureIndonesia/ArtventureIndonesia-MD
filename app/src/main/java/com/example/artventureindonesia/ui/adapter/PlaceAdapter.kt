package com.example.artventureindonesia.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artventureindonesia.databinding.ItemPlaceBinding
import com.example.artventureindonesia.remote.response.MuseumDataItem
import com.example.artventureindonesia.ui.detailtask.DetailTaskActivity
import com.example.artventureindonesia.ui.task.TaskActivity

class PlaceAdapter(private val context: Context) : ListAdapter<MuseumDataItem, PlaceAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



            val place = getItem(position)
            holder.bind(place)
            holder.itemView.setOnClickListener {
                if (place.isOpen == true){
                    val moveDataUserIntent = Intent(holder.itemView.context, TaskActivity::class.java)
                    moveDataUserIntent.putExtra(TaskActivity.ID, place.museumDoc)
                    holder.itemView.context.startActivity(moveDataUserIntent)

                } else {
                    Toast.makeText(holder.itemView.context, "Place not open", Toast.LENGTH_SHORT).show()
                }

            }
    }

    class MyViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(place: MuseumDataItem){
            binding.tvTitle.text = place.museumName
            binding.tvAlamat.text = place.address ?:"Address Not Found"
            Glide.with(binding.root.context)
                .load(place.urlMuseumImg)
                .into(binding.itemImage)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MuseumDataItem>() {
            override fun areItemsTheSame(oldItem: MuseumDataItem, newItem: MuseumDataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: MuseumDataItem, newItem: MuseumDataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}