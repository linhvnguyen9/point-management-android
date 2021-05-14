package com.btntrung.pointmanagement.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.adapter.StudentSubjectAdapter;
import com.btntrung.pointmanagement.entity.Subject;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class SudentSubjectFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentSubjectAdapter subjectAdapter;
    private List<Subject> subjects=new ArrayList<>();

    private CombinedChart mChart;
    private int textColor=Color.RED;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_sudent_subject,container,false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Subject subject=new Subject(1,"Lap trinh",0,0,0);
        Subject subject2=new Subject(2,"Lap trinh nhung",0,0,0);
        Subject subject3=new Subject(3,"Kien truc thiet ke phan mem",0,0,0);
        subjects.add(subject);
        subjects.add(subject2);
        subjects.add(subject3);
        subjectAdapter=new StudentSubjectAdapter(getContext(),subjects);
        recyclerView.setAdapter(subjectAdapter);
//        ------------------------------chart

        mChart = (CombinedChart) view.findViewById(R.id.combinedChart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
//        mChart.setOnChartValueSelectedListener(view);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        final List<String> xLabel = new ArrayList<>();
        xLabel.add("Kỳ 1");
        xLabel.add("Kỳ 2");
        xLabel.add("Kỳ 3");


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xLabel.get((int) value % xLabel.size());
            }
        });

        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart(textColor));

        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();

        return view;
    }

    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), "Value: " + e.getY() + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected() {

    }
    private static DataSet dataChart(int textColor) {

        LineData d = new LineData();
        int[] data = new int[] {8,9,3 };

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < 3; index++) {
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