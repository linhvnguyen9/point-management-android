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

}