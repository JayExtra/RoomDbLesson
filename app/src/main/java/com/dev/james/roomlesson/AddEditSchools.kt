package com.dev.james.roomlesson

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dev.james.roomlesson.databinding.ActivityAddEditSchoolsBinding
import com.dev.james.roomlesson.models.Student

class AddEditSchools : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditSchoolsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditSchoolsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.apply {
            val name = nameTv.text.toString()
            val department = departmentTv.text.toString()
            val topic = topicTv.text.toString()

            buttonAdd.setOnClickListener {
                val replyIntent = Intent()
                if(name.isEmpty()){
                    setResult(Activity.RESULT_CANCELED , replyIntent)
                    Toast.makeText(this@AddEditSchools, "provide name", Toast.LENGTH_SHORT).show()
                }else if(department.isEmpty()){
                    setResult(Activity.RESULT_CANCELED , replyIntent)
                    Toast.makeText(this@AddEditSchools, "provide department", Toast.LENGTH_SHORT).show()
                }else if(topic.isEmpty()){
                    setResult(Activity.RESULT_CANCELED , replyIntent)
                    Toast.makeText(this@AddEditSchools, "provide topic taking", Toast.LENGTH_SHORT).show()
                }else{
                    val student = Student(name = name , topic = topic , department = department)
                    replyIntent.putExtra(EXTRA_REPLY ,student)
                    setResult(Activity.RESULT_OK , replyIntent)
                }
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.dev.james.roomlesson"
    }
}