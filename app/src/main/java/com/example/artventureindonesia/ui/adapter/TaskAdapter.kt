package com.example.artventureindonesia.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artventureindonesia.databinding.ItemPlaceBinding
import com.example.artventureindonesia.databinding.ItemTaskBinding
import com.example.artventureindonesia.remote.response.MuseumDataItem
import com.example.artventureindonesia.remote.response.TaskDataItem
import com.example.artventureindonesia.ui.detailtask.DetailTaskActivity
import com.example.artventureindonesia.ui.reward.RewardActivity
import com.example.artventureindonesia.ui.task.TaskActivity

class TaskAdapter (private val context: Context) : ListAdapter<TaskDataItem, TaskAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
        holder.itemView.setOnClickListener {
            val moveDataUserIntent = Intent(holder.itemView.context, DetailTaskActivity::class.java)
            moveDataUserIntent.putExtra(DetailTaskActivity.ID, task.objectDoc)
            holder.itemView.context.startActivity(moveDataUserIntent)
        }
    }


    class MyViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskDataItem){
            binding.taskName.text = task.objectName
            binding.taskDesc.text = task.objectDescription
            binding.textPoint.text = task.points.toString()

        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TaskDataItem>() {
            override fun areItemsTheSame(oldItem: TaskDataItem, newItem: TaskDataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TaskDataItem, newItem: TaskDataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}