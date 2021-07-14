package com.dev.james.roomlesson

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.james.roomlesson.databinding.ActivityMainBinding
import com.dev.james.roomlesson.databinding.SingleStudentCardBinding
import com.dev.james.roomlesson.models.Student

class StudentRecyclerAdapter(private val listener : OnItemClickListener) : androidx.recyclerview.widget.ListAdapter<Student,StudentRecyclerAdapter.StudentViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = SingleStudentCardBinding.inflate(LayoutInflater.from(parent.context) ,
            parent , false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
       val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    inner class StudentViewHolder(private val binding : SingleStudentCardBinding) :
            RecyclerView.ViewHolder(binding.root){

                init {
                    binding.apply {
                        root.setOnClickListener{
                            val position = adapterPosition
                            if(position != RecyclerView.NO_POSITION){
                                val student = getItem(position)
                                listener.onItemClick(student)
                            }
                        }
                    }
                }

        fun bind(student: Student){
            binding.apply {
                stdNm.text = student.name
                stdDep.text = student.department
                stdTopic.text = student.topic
            }

        }

            }

    class DiffCallback : DiffUtil.ItemCallback<Student>(){
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem ==newItem

    }

    interface OnItemClickListener {
        fun onItemClick(student: Student)
    }


}