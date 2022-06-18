package sg.edu.np.mad.Assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context c) {super(c, "Videos.db", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE VideoDetails (ID Text, Title TEXT, TimeStamp TEXT, VideoURL TEXT, ID INTEGER PRIMARY KEY, Follow INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserDetails");
        onCreate(db);
    }

    public void insertUsers(ModelVideos modelVideos) {
        //int f;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", modelVideos.id);
        contentValues.put("Title", modelVideos.title);
        contentValues.put("TimeStamp", modelVideos.timestamp);
        contentValues.put("VideoURL", modelVideos.videoUrl);

        db.insert("VideoDetails", null, contentValues);
        db.close();
    }

    public ArrayList<ModelVideos> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ModelVideos> videosList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT*FROM VideoDetails", null);
        while(cursor.moveToNext()){
            ModelVideos modelVideos = new ModelVideos();
            modelVideos.id = cursor.getString(0);
            modelVideos.title = cursor.getString(1);
            modelVideos.timestamp = cursor.getString(2);
            modelVideos.videoUrl = cursor.getString(3);

            videosList.add(modelVideos);
        }
        db.close();
        return videosList;
    }
}
