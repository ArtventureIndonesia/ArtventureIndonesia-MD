package com.example.artventureindonesia.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artventureindonesia.databinding.ItemRewardBinding
import com.example.artventureindonesia.remote.response.RewardsData

import com.example.artventureindonesia.remote.response.RewardsDataItem
import com.example.artventureindonesia.ui.detailreward.DetailRewardActivity
import com.example.artventureindonesia.ui.reward.RewardActivity


class RewardAdapter(private val context: Context) : ListAdapter<RewardsDataItem, RewardAdapter.MyViewHolder>(
    DIFF_CALLBACK ){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val reward = getItem(position)
        holder.bind(reward)
        holder.itemView.setOnClickListener {
            val moveDataUserIntent = Intent(holder.itemView.context, DetailRewardActivity::class.java)
            moveDataUserIntent.putExtra(DetailRewardActivity.ID, reward.rewardDoc.toString())
            Log.d("error", reward.rewardDoc.toString())
            holder.itemView.context.startActivity(moveDataUserIntent)
        }

    }

    class MyViewHolder(val binding: ItemRewardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reward: RewardsDataItem){
            binding.tvReward.text = reward.rewardName
            binding.tvRewardPoint.text = reward.rewardPoint.toString()
            Glide.with(binding.root.context)
                .load(reward.urlRewardImg)
                .into(binding.imgItemPhoto)

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RewardsDataItem>() {
            override fun areItemsTheSame(oldItem: RewardsDataItem, newItem: RewardsDataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: RewardsDataItem, newItem: RewardsDataItem): Boolean {
                return oldItem == newItem
            }
        }
    }



}

