package com.btntrung.pointmanagement.presentation.manager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.btntrung.pointmanagement.databinding.ItemStudentBinding
import com.btntrung.pointmanagement.entity.Student

class StudentListAdapter(private val studentClickListener: StudentClickListener) : ListAdapter<Student, StudentListAdapter.StudentViewHolder>(StudentListDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, studentClickListener)
    }

    class StudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student, clickListener: StudentClickListener) {
            binding.student = student
            binding.listener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : StudentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemStudentBinding.inflate(layoutInflater, parent, false)
                return StudentViewHolder(binding)
            }
        }
    }
}

class StudentListDiffUtil: DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean = oldItem == newItem
}

interface StudentClickListener {
    fun onClick(student: Student)
}