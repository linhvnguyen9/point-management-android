package com.btntrung.pointmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.FacebookSdk
import com.firebase.ui.auth.AuthUI
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("Key hash: ${FacebookSdk.getApplicationSignature(this)}")
    }
}