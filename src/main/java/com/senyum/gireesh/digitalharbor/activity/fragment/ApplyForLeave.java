package com.senyum.gireesh.digitalharbor.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.senyum.gireesh.digitalharbor.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.LinkedHashSet;
import java.util.Set;

public class ApplyForLeave extends Fragment {

    Set<String> leaveList;
    private View rootView;
    private Context context;
    private CalendarView calendarView;
    private TextView tvSelectedDates;

    public ApplyForLeave() {
//         Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_leave_apply, container, false);
        context = rootView.getContext();

        tvSelectedDates = (TextView) rootView.findViewById(R.id.selected_leaves);
        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setMinDate(System.currentTimeMillis() - 1000);

        //setting spinner
        String[] SPINNERLIST = {"Casual Leave", "Sick Leave", "Comp Off", "Maternity Leave"};
        leaveList = new LinkedHashSet<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                rootView.findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "-" + month + "-" + year;
//                Toast.makeText(context, dayOfMonth + " " + month + " " + year, Toast.LENGTH_SHORT).show();
                displaySelectedDates(date);
            }
        });
        return rootView;
    }

    private void displaySelectedDates(String newDate) {
        leaveList.add(newDate);
        Log.e("LEAVELIST", String.valueOf(leaveList));
        if (tvSelectedDates.getVisibility() == View.GONE)
            tvSelectedDates.setVisibility(View.VISIBLE);

        if (leaveList.size() > 5) {
            Toast.makeText(context, "Sorry..! You can put maximum 5 days of leave through app..", Toast.LENGTH_SHORT).show();
            return;
        }

        tvSelectedDates.setText(null);
        StringBuilder dataToDisp = new StringBuilder();
        for (String leaveDate : leaveList) {
            dataToDisp.append(leaveDate + " ■ ");
        }
        String totalLeaves = dataToDisp.substring(0, dataToDisp.length() - 2);
        tvSelectedDates.setText(totalLeaves);
    }
}