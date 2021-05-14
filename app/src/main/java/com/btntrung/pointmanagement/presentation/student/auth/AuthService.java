package com.btntrung.pointmanagement.presentation.student.auth;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import retrofit2.Call;

public class AuthService extends IntentService {

    private static final String TAG = "RegIntentService";


    public AuthService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        String token = Hawk.get("FIREBASE_TOKEN", "");
//        Call<Object> chain;
//        String request = chain.request();
//
//        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.i(TAG, "Bearer " + token);
    }

}
