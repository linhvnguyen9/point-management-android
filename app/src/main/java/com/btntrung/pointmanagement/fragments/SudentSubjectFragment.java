package com.btntrung.pointmanagement.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.adapter.StudentSubjectAdapter;
import com.btntrung.pointmanagement.entity.Subject;
import com.btntrung.pointmanagement.presentation.student.ApiService;
import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SudentSubjectFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentSubjectAdapter subjectAdapter;
    private List<Subject> subjects=new ArrayList<>();
    private TextView txtGpa,txtDescription;

    private CombinedChart mChart;
    private int textColor=Color.RED;
    private String token="Bearer "+ Hawk.get("FIREBASE_TOKEN", "");
    private List<StudentPointModel> list=null;
    private FirebaseUser firebaseUser;
    private String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_sudent_subject,container,false);

        txtGpa=view.findViewById(R.id.txt_gpa);
        txtDescription=view.findViewById(R.id.txt_description);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mChart = (CombinedChart) view.findViewById(R.id.combinedChart);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        uid= firebaseUser.getUid();

//        subjectAdapter=new StudentSubjectAdapter(getContext(),subjects);
//        recyclerView.setAdapter(subjectAdapter);

        callApi();

        return view;
    }
    private void callApi(){
        System.out.println(token);
        ApiService.apiService.getAllPoint(token,uid).enqueue(new Callback<List<StudentPointModel>>() {
            @Override
            public void onResponse(Call<List<StudentPointModel>> call, Response<List<StudentPointModel>> response) {
                outChart(response.body());
                List<StudentPointModel> list=response.body();
                float avg=0;
                for (StudentPointModel studentPointModel:list)
                    avg+=studentPointModel.getAvg();
                float gpa= (float) ((avg/list.size())/2.5);
                txtGpa.setText(gpa+"");
                if (gpa>=3.2)
                    txtDescription.setText("Higt Point");
                else if (gpa>=2.5) txtDescription.setText("Medium Point");
                else if (gpa>=1.5) txtDescription.setText("Low Point");


            }

            @Override
            public void onFailure(Call<List<StudentPointModel>> call, Throwable t) {
                System.out.println("Call API fail");
            }
        });

    }
    private void outChart(List<StudentPointModel> list){

        float[] point = new float[20];
        final List<String> semesterName = new ArrayList<>();

        for (int i=0;i<list.size();i++)
            for (int j=i+1;j<list.size();j++)
                if (list.get(i).getSemester().getName().equals(list.get(j).getSemester().getName()))
                    list.remove(j);

        int d=-1;
        for (StudentPointModel studentPointModel:list) {
            d++;
            semesterName.add(studentPointModel.getSemester().getName());
            point[d]= (float) ((float) (studentPointModel.getAvg())/2.5);
        }

        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
//        mChart.setOnChartValueSelectedListener(this);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(6f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(2f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return semesterName.get((int) value % semesterName.size());
            }
        });

        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart(textColor,point,d));

        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();
    }

    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), "Value: " + e.getY() + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected() {

    }
    private static DataSet dataChart(int textColor,float [] data,int size) {

        LineData d = new LineData();


        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index <=size; index++) {
            entries.add(new Entry(index, data[index]));
        }

        LineDataSet set = new LineDataSet(entries, "Point");
        set.setColor(textColor);
        set.setLineWidth(3f);
        set.setCircleColor(textColor);
        set.setCircleRadius(5f);
        set.setFillColor(textColor);
//        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(textColor);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

}