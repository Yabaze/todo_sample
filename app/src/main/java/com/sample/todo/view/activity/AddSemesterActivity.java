package com.sample.todo.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sample.todo.R;
import com.sample.todo.database.dao.entity.Semester;
import com.sample.todo.view_model.SemesterViewModel;

import java.util.Calendar;
import java.util.Date;

public class AddSemesterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText semesterNameButton;
    private EditText startDateButton;
    private Button endDateButton , fab;
    private SemesterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);

        viewModel = ViewModelProviders.of(this).get(SemesterViewModel.class);

        semesterNameButton = findViewById(R.id.semesterNameButton);
        startDateButton = findViewById(R.id.startDateButton);
        endDateButton = findViewById(R.id.endDateButton);
        fab = findViewById(R.id.fab);

        calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, AddSemesterActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        fab.setOnClickListener(this);
        endDateButton.setOnClickListener(this);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.endDateButton:
                datePickerDialog.show();
                break;
            case R.id.fab:
                if (semesterNameButton.getText() == null || startDateButton.getText() == null || date == null)
                    Toast.makeText(AddSemesterActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    viewModel.insertSingleData(new Semester(
                            semesterNameButton.getText().toString(),
                            date,
                            startDateButton.getText().toString()
                    ));
                    finish();
                }

        }
    }
}
