package com.sample.todo.view_model;

import android.app.Application;
import android.os.AsyncTask;

import com.sample.todo.database.AppDatabase;
import com.sample.todo.database.dao.Semester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SemesterViewModel extends AndroidViewModel {

    private LiveData<List<Semester>> listOfSemesterData;

    private AppDatabase appDatabase;

    public SemesterViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        listOfSemesterData = appDatabase.semesterDao().getAll();
    }

    public LiveData<List<Semester>> getListOfSemesterData() {
        return listOfSemesterData;
    }

    public void insertItem(List<Semester> semester) {
        new insertAsyncTask(appDatabase).execute(semester);
    }

    public void deleteItem(Semester semester) {
        new deleteAsyncTask(appDatabase).execute(semester);
    }

    public void insertSingleData(Semester semester) {
        insertItem(new ArrayList<>(Collections.singleton(semester)));
    }

    private static class insertAsyncTask extends android.os.AsyncTask<List<Semester>, Void, Void> {

        private AppDatabase db;

        insertAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final List<Semester>... params) {
            for (int i=0;i<params.length;i++) {
                db.semesterDao().insert(params[0].get(i));

            }
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Semester, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Semester... params) {
            db.semesterDao().delete(params[0]);
            return null;
        }

    }

}
