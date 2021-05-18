package com.btntrung.pointmanagement.presentation.manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.btntrung.pointmanagement.R
import com.btntrung.pointmanagement.databinding.ActivityManagerMainBinding

class ManagerMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manager_main)
    }
}