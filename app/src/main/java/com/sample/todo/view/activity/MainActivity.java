package com.sample.todo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
