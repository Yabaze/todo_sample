package com.sample.todo.view_model;

import android.app.Application;
import android.os.AsyncTask;

import com.sample.todo.database.AppDatabase;
import com.sample.todo.database.dao.entity.ClassTable;
import com.sample.todo.database.dao.entity.ClassTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ClassViewModel extends AndroidViewModel {

    private LiveData<List<ClassTable>> listOfClassTableData;

    private AppDatabase appDatabase;

    public ClassViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        listOfClassTableData = appDatabase.classDao().getAll();

    }

    public LiveData<List<ClassTable>> getListOfClassTableData() {
        return listOfClassTableData;
    }

    public void insertItem(List<ClassTable> classTables) {
        new insertAsyncTask(appDatabase).execute(classTables);
    }

    public void deleteItem(ClassTable semester) {
        new deleteAsyncTask(appDatabase).execute(semester);
    }

    public void insertSingleData(ClassTable semester) {
        //insertItem(new ArrayList<>(Collections.singleton(semester)));
        appDatabase.classDao().insertAll(new ArrayList<>(Collections.singleton(semester)));
    }

    private static class insertAsyncTask extends AsyncTask<List<ClassTable>, Void, Void> {

        private AppDatabase db;

        insertAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final List<ClassTable>... params) {
            for (int i=0;i<params[0].size();i++) {
                db.classDao().insert(params[0].get(i));
            }
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<ClassTable, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final ClassTable... params) {
            db.classDao().delete(params[0]);
            return null;
        }

    }

}
