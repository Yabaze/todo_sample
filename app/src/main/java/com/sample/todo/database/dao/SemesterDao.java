package com.sample.todo.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

@Dao
@TypeConverters(DateConverter.class)
public interface SemesterDao {

        @Query("SELECT * FROM semester")
        LiveData<List<Semester>> getAll();

        @Query("SELECT * FROM semester WHERE uid IN (:userIds)")
        List<Semester> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM semester WHERE start_time LIKE :first AND " +
                "end_time LIKE :last LIMIT 1")
        Semester findByName(String first, String last);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Semester semester);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAll(List<Semester> semesters);

        @Delete
        void delete(Semester semester);

}
