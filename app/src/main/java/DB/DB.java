package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "schedule_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "schedule_table";
    private static final String COL_DATE = "date";
    private static final String COL_TIME = "time";
    private static final String COL_LOCATION = "location";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_DATE + " TEXT, " +
                COL_TIME + " TEXT, " +
                COL_LOCATION + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String date, String time, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DATE, date);
        values.put(COL_TIME, time);
        values.put(COL_LOCATION, location);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void insertData(String date, String time, String location, String scheduleType) {
    }
}
