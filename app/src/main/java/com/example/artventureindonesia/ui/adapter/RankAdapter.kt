package com.example.artventureindonesia.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.artventureindonesia.databinding.ItemRankBinding
import com.example.artventureindonesia.remote.response.UserDataItem



class RankAdapter(private val context: Context) : ListAdapter<UserDataItem, RankAdapter.MyViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val rank = getItem(position)
        holder.bind(rank)
//        holder.itemView.setOnClickListener {
//            val moveDataUserIntent = Intent(holder.itemView.context, DetailRewardActivity::class.java)
//            moveDataUserIntent.putExtra(DetailRewardActivity.ID, rank.rewardDoc.toString())
//            Log.d("error", rank.rewardDoc.toString())
//            holder.itemView.context.startActivity(moveDataUserIntent)
//        }
    }

    class MyViewHolder(val binding: ItemRankBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rank: UserDataItem){
            binding.tvRankname.text = rank.userName
            binding.tvRankpoint.text = rank.userPoints.toString()
            binding.tvRanknumber.text = rank.userRank.toString()

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDataItem>() {
            override fun areItemsTheSame(oldItem: UserDataItem, newItem: UserDataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UserDataItem, newItem: UserDataItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}