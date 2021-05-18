package com.btntrung.pointmanagement.presentation.student;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.btntrung.pointmanagement.LoginActivity;
import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.entity.Semester;
import com.btntrung.pointmanagement.entity.Student;
import com.btntrung.pointmanagement.fragments.StudentPointFragment;
import com.btntrung.pointmanagement.fragments.SudentSubjectFragment;
import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMainActivity extends AppCompatActivity {
    private TextView username;
    private Student student;
    private CircleImageView profileImage;
    private String token="Bearer "+Hawk.get("FIREBASE_TOKEN", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        TabLayout tabLayout=findViewById(R.id.tab_layout);
        ViewPager viewPager=findViewById(R.id.view_pager);
        profileImage=findViewById(R.id.profile_image);
        username=findViewById(R.id.username);

        student=new Student("123",123,"nguyen abcd","","","","","");

        profileImage.setImageResource(R.mipmap.ic_launcher);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SudentSubjectFragment(),"Subject");
        viewPagerAdapter.addFragment(new StudentPointFragment(),"Point");

        viewPager.setAdapter(viewPagerAdapter) ;

        tabLayout.setupWithViewPager(viewPager);
        callApi();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
//                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentMainActivity.this, LoginActivity.class));
                finish();
                return true;
            case R.id.profile:
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

    private void callApi(){
        System.out.println(token);
        ApiService.apiService.getAllPoint(token,4).enqueue(new Callback<List<StudentPointModel>>() {
            @Override
            public void onResponse(Call<List<StudentPointModel>> call, Response<List<StudentPointModel>> response) {
                List<StudentPointModel> point=response.body();
                System.out.println("point size"+point.size());
                System.out.println("object+++++"+point.get(0).getSemester().getName());
//                username.setText(point.get(0).getSubject().getName().toString());
            }

            @Override
            public void onFailure(Call<List<StudentPointModel>> call, Throwable t) {
                Toast.makeText(StudentMainActivity.this,"Call Api fail",Toast.LENGTH_SHORT).show();
            }
        });

    }
}