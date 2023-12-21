package com.example.artventureindonesia.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artventureindonesia.databinding.ItemArticelBinding
import com.example.artventureindonesia.remote.response.NewsDataItem
import com.example.artventureindonesia.remote.response.RewardDataItem


class ArticelAdapter(private val context: Context) : ListAdapter<NewsDataItem, ArticelAdapter.MyViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemArticelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val reward = getItem(position)
        holder.bind(reward)
//        holder.itemView.setOnClickListener {
//            val moveDataUserIntent = Intent(holder.itemView.context, DetailArticelActivity::class.java)
//            moveDataUserIntent.putExtra(DetailArticelActivity.ID, reward.rewardDoc.toString())
//            Log.d("error", reward.rewardDoc.toString())
//            holder.itemView.context.startActivity(moveDataUserIntent)
//        }

    }

    class MyViewHolder(val binding: ItemArticelBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(articel: NewsDataItem){
            binding.tvArtikel.text = articel.newsTitle
            Glide.with(binding.root.context)
                .load(articel.urlNewsImg)
                .into(binding.imgArticel)

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsDataItem>() {
            override fun areItemsTheSame(oldItem: NewsDataItem, newItem: NewsDataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: NewsDataItem, newItem: NewsDataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}