package com.sample.todo.database.dao;

import com.sample.todo.database.dao.entity.ClassTable;

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
public interface ClassDao {

    @Query("SELECT * FROM class_table")
    LiveData<List<ClassTable>> getAll();

    @Query("SELECT * FROM class_table WHERE class_id IN (:classIds)")
    List<ClassTable> loadAllByIds(int[] classIds);

    @Query("SELECT * FROM class_table WHERE name LIKE :first")
    ClassTable findByName(String first);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ClassTable> listOfClasses);

    @Delete
    void delete(ClassTable classTable);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ClassTable classTable);
}
