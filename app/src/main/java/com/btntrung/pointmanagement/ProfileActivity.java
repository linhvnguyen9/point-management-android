package com.btntrung.pointmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.btntrung.pointmanagement.presentation.student.StudentMainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    TextView txtname,txtemail,txtdes;
    ImageView avata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtname=findViewById(R.id.txt_name);
        txtemail=findViewById(R.id.txt_email);
        txtdes=findViewById(R.id.txt_description);
        avata=findViewById(R.id.profileimage);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String uid = user.getUid();
            boolean emailVerified = user.isEmailVerified();

            txtname.setText("Name: "+name);
            txtemail.setText("Email: "+email);
            avata.setImageURI(photoUrl);

            if (emailVerified)
                txtdes.setText("User's email is verified");
            else txtdes.setText("User's email is not verified");


            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setLogo();
            actionBar.setDisplayUseLogoEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                startActivity(new Intent(ProfileActivity.this, StudentMainActivity.class));
                finish();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}