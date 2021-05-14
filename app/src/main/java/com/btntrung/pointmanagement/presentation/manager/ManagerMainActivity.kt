package com.btntrung.pointmanagement.presentation.manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.btntrung.pointmanagement.R
import com.btntrung.pointmanagement.adapter.ManagerSearchClassAdapter
import com.btntrung.pointmanagement.databinding.ActivityManagerMainBinding
import com.btntrung.pointmanagement.entity.Classroom
import com.btntrung.pointmanagement.entity.Semester
import com.btntrung.pointmanagement.presentation.manager.ClassroomListAdapter
import com.btntrung.pointmanagement.presentation.manager.ManagerMainViewModel
import com.btntrung.pointmanagement.presentation.student.StudentMainActivity
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class ManagerMainActivity : AppCompatActivity() {
    private val viewModel: ManagerMainViewModel by viewModel()

    private lateinit var binding: ActivityManagerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manager_main)
    }

    override fun onStart() {
        super.onStart()

        binding.recycleView.adapter = ClassroomListAdapter(object : ClassroomClickListener {
            override fun onClick(classroom: Classroom) {
                Timber.d("Click on classroom $classroom")
            }
        })
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, StudentMainActivity::class.java)
            startActivity(intent)
        }

        binding.spinnerManagerSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val semester = parent?.adapter?.getItem(position) as Semester
                viewModel.getClassrooms(semester.id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        viewModel.semesters.observe(this) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerManagerSemester.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        viewModel.classroom.observe(this) {
            (binding.recycleView.adapter as ClassroomListAdapter).submitList(it)
        }
    }
}