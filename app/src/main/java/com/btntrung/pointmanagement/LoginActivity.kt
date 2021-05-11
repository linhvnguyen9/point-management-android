package com.btntrung.pointmanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.orhanobut.hawk.Hawk
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val response = IdpResponse.fromResultIntent(data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                FirebaseAuth.getInstance().currentUser?.getIdToken(false)?.addOnCompleteListener {
                    if (it.isSuccessful) {
//                        val isAdmin = it.result?.claims?.get("admin") as Boolean
                        val isAdmin = true
                        Hawk.put("FIREBASE_TOKEN", it.result?.token)
                        val intent = if (isAdmin) {
                            Intent(this, ManagerMainActivity::class.java)
                        } else {
                            Intent(this, StudentMainActivity::class.java)
                        }
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                    Timber.e(response?.error?.errorCode.toString())
                    Timber.e(response?.error?.localizedMessage)
                    Timber.e(response?.error)
            }
        }
    }

    companion object {
        const val RC_SIGN_IN = 1
    }
}