package com.btntrung.pointmanagement.presentation.manager

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.btntrung.pointmanagement.databinding.ItemClassroomBinding
import com.btntrung.pointmanagement.entity.Classroom

class ClassroomListAdapter : ListAdapter<Classroom, ClassroomListAdapter.ClassroomViewHolder>(ClassroomListDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassroomViewHolder {
        return ClassroomViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ClassroomViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ClassroomViewHolder(private val binding: ItemClassroomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(classroom: Classroom) {
            binding.classroom = classroom
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ClassroomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemClassroomBinding.inflate(layoutInflater, parent, false)
                return ClassroomViewHolder(binding)
            }
        }
    }
}

class ClassroomListDiffUtil: DiffUtil.ItemCallback<Classroom>() {
    override fun areItemsTheSame(oldItem: Classroom, newItem: Classroom): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Classroom, newItem: Classroom): Boolean =
        oldItem.name == newItem.name
}