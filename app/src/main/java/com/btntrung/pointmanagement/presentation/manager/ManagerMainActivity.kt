package com.btntrung.pointmanagement.presentation.manager

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
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
import com.btntrung.pointmanagement.ProfileActivity
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber
import java.util.*

class ManagerMainActivity : AppCompatActivity() {
    private val viewModel: ManagerMainViewModel by viewModel()

    private lateinit var binding: ActivityManagerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manager_main)

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            for (profile in it.providerData) {
                val providerId = profile.providerId
                val uid = profile.uid
                val name = profile.displayName
                val email = profile.email
                val photoUrl = profile.photoUrl

                val actionBar = supportActionBar
                actionBar!!.title = name
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.logout) {
//            Toast.makeText(this, "Item Clicked", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.profile) {
            val intent:Intent=Intent(this,ProfileActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)

    }

}