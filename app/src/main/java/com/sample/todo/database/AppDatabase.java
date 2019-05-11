package com.sample.todo.database;

import android.content.Context;

import com.sample.todo.database.dao.ClassDao;
import com.sample.todo.database.dao.entity.ClassTable;
import com.sample.todo.database.dao.entity.Semester;
import com.sample.todo.database.dao.SemesterDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Semester.class, ClassTable.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todo.db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract SemesterDao semesterDao();

    public abstract ClassDao classDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'Semester' ('id' INTEGER, "
                    + "'first_name' TEXT, PRIMARY KEY('id'))");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };
}