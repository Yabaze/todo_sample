package com.sample.todo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sample.todo.R;
import com.sample.todo.adapters.RecyclerViewAdapter;
import com.sample.todo.api_implementation.ApiAction;
import com.sample.todo.api_implementation.common.ApiCallPresenter;
import com.sample.todo.database.dao.Semester;
import com.sample.todo.databinding.ActivityMainBinding;
import com.sample.todo.view_model.SemesterViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements ApiAction.GET_DETAILS {

    private SemesterViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;

    ApiCallPresenter apiCallPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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

        viewModel = ViewModelProviders.of(this).get(SemesterViewModel.class);

        viewModel.getListOfSemesterData().observe(MainActivity.this, new Observer<List<Semester>>() {
            @Override
            public void onChanged(@Nullable List<Semester> semesterList) {
                recyclerViewAdapter.addItems(semesterList);
            }
        });

        activityMainBinding.setAdapter(recyclerViewAdapter);

    }


    public void longPressed(Semester item) {
        viewModel.deleteItem(item);
    }

    @Override
    public void onSuccessDetails(List<Semester> semesterList) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        viewModel.insertItem(semesterList);
    }

    @Override
    public void onFailureDetails() {
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
    }
}
