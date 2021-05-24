package com.btntrung.pointmanagement.presentation.student;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.btntrung.pointmanagement.LoginActivity;
import com.btntrung.pointmanagement.ProfileActivity;
import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.entity.Student;
import com.btntrung.pointmanagement.fragments.StudentPointFragment;
import com.btntrung.pointmanagement.fragments.SudentSubjectFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class StudentMainActivity extends AppCompatActivity {
    private Student student;
    private String token="Bearer "+Hawk.get("FIREBASE_TOKEN", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        ActionBar actionBar = getSupportActionBar();

        TabLayout tabLayout=findViewById(R.id.tab_layout);
        ViewPager viewPager=findViewById(R.id.view_pager);
//        profileImage=findViewById(R.id.profile_image);
//        username=findViewById(R.id.username);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            actionBar.setTitle(name);
            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setLogo();
            actionBar.setDisplayUseLogoEnabled(true);

        }


        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SudentSubjectFragment(),"OVERVIEW");
        viewPagerAdapter.addFragment(new StudentPointFragment(),"Point");

        viewPager.setAdapter(viewPagerAdapter) ;

        tabLayout.setupWithViewPager(viewPager);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentMainActivity.this, LoginActivity.class));
                finish();
                return true;
            case R.id.profile:
                startActivity(new Intent(StudentMainActivity.this, ProfileActivity.class));
                finish();
                return true;

        }
        return false;
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public  void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}