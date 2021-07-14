package com.dev.james.roomlesson

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.james.roomlesson.databinding.ActivityMainBinding
import com.dev.james.roomlesson.databinding.SingleStudentCardBinding
import com.dev.james.roomlesson.models.Student

class MainActivity : AppCompatActivity(), StudentRecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val newWordActivityRequestCode = 1
    private val studentViewModel : StudentsViewModel by viewModels {
        StudentsViewModelFactory((application as StudentsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerView()
        setupObservers()

        binding.apply {
            addFab.setOnClickListener {
                val intent = Intent(this@MainActivity , AddEditSchools::class.java)
                startActivityForResult(intent, newWordActivityRequestCode)
            }
        }
    }



    val studentAdapter = StudentRecyclerAdapter(this)

    private fun setupRecyclerView() {
        binding.apply {
            schoolsRv.apply {
                adapter = studentAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun setupObservers() {
        studentViewModel.allStudents.observe(this , Observer { students ->
            students?.let {
                studentAdapter.submitList(it)
            }
        })

        studentViewModel.updateMessages.observe(this , Observer { status ->
            if (status)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        })
    }


    override fun onItemClick(student: Student) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}