package com.sample.todo.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.sample.todo.R;
import com.sample.todo.adapters.ClassDetailRecyclerAdapter;
import com.sample.todo.adapters.RecyclerViewAdapter;
import com.sample.todo.api_implementation.ApiAction;
import com.sample.todo.api_implementation.common.ApiCallPresenter;
import com.sample.todo.database.dao.entity.ClassTable;
import com.sample.todo.database.dao.entity.Semester;
import com.sample.todo.databinding.ActivityMainBinding;
import com.sample.todo.responsePojo.ClassDetail;
import com.sample.todo.responsePojo.TeacherDetail;
import com.sample.todo.view_model.ClassViewModel;
import com.sample.todo.view_model.SemesterViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ApiAction.GET_Teacher_CLASS_DETAILS {

    ActivityMainBinding activityMainBinding;
    private SemesterViewModel semesterViewModel;
    private ClassViewModel classViewModel;

    private RecyclerViewAdapter recyclerViewAdapter;
    private ClassDetailRecyclerAdapter classDetailRecyclerAdapter;

    ApiCallPresenter apiCallPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        apiCallPresenter = new ApiCallPresenter(this,this);
        apiCallPresenter.callDetailsAPI();

        callCalendar();

        Button fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddSemesterActivity.class));
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
        classDetailRecyclerAdapter = new ClassDetailRecyclerAdapter(new ArrayList<>());

        semesterViewModel = ViewModelProviders.of(this).get(SemesterViewModel.class);

        semesterViewModel.getListOfSemesterData().observe(MainActivity.this, new Observer<List<Semester>>() {
            @Override
            public void onChanged(@Nullable List<Semester> semesterList) {
                recyclerViewAdapter.addItems(semesterList);
            }
        });
        classViewModel = ViewModelProviders.of(this).get(ClassViewModel.class);
        classViewModel.getListOfClassTableData().observe(MainActivity.this, new Observer<List<ClassTable>>() {
            @Override
            public void onChanged(@Nullable List<ClassTable> classList) {
                classDetailRecyclerAdapter.addItems(classList);
            }
        });

        activityMainBinding.setClassRecyclerAdapter(classDetailRecyclerAdapter);

        activityMainBinding.setAdapter(recyclerViewAdapter);


    }

    private void callCalendar() {
        final CompactCalendarView compactCalendarView = findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
        Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
        compactCalendarView.addEvent(ev1);

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev2 = new Event(Color.GREEN, 1433704251000L);
        compactCalendarView.addEvent(ev2);

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
        List<Event> events = compactCalendarView.getEvents(1433701251000L); // can also take a Date object

        // events has size 2 with the 2 events inserted previously

        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });
    }


    public void longPressed(Semester item) {
        semesterViewModel.deleteItem(item);
    }

    public void longPressed(ClassTable item) {
        classViewModel.deleteItem(item);
    }

    @Override
    public void onSuccessTeacherClassDetailDetails(TeacherDetail teacherDetail) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();


        List<ClassDetail> a = teacherDetail.getClassDetails();
        List<ClassTable> classTableList = new ArrayList<>();

        for(ClassDetail classDetail : a){
            classTableList.add(new ClassTable(
                    classDetail.getClassId(),
                    classDetail.getClassName(),
                    classDetail.getStartTime(),
                    classDetail.getEndTime()));
        }

        classViewModel.insertItem(classTableList);
    }

    @Override
    public void onFailureTeacherClassDetailDetails() {
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
    }
}
