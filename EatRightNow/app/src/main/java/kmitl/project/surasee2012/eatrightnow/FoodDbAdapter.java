package kmitl.project.surasee2012.eatrightnow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Gun on 11/6/2017.
 */

public class FoodDbAdapter {

    FoodDB foodDB;
    private static final String DB_NAME = "EatRightNow_DB.db";

    public FoodDbAdapter(Context context) {
        foodDB = FoodDB.getInstance(context, DB_NAME);
    }

//    public long insertData(String name, String pass) {
//        SQLiteDatabase dbb = foodDB.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(FoodDB.NAME, name);
//        long id = dbb.insert(FoodDB.TABLE_NAME, null , contentValues);
//        return id;
//    }

    public String getData() {
//        SQLiteDatabase db = foodDB.getWritableDatabase();
//        String[] columns = {FoodDB.ID, FoodDB.NAME};
//        Cursor cursor =db.query(FoodDB.TABLE_NAME,columns,null,null,null,null,null);
//        StringBuffer buffer= new StringBuffer();
//        while (cursor.moveToNext()) {
//            int cid =cursor.getInt(cursor.getColumnIndex(FoodDB.ID));
//            String name =cursor.getString(cursor.getColumnIndex(FoodDB.NAME));
//            buffer.append(cid+ "   " + name + " \n");
//        }
        String query = "SELECT * FROM Foods";
        Cursor c1 = FoodDB.rawQuery(query);
        StringBuffer buffer= new StringBuffer();

        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
//                    FoodsListItems foodsListItems = new FoodsListItems();
//
//                    foodsListItems.setFood_Name(c1.getString(c1
//                            .getColumnIndex("Food_Name")));

                    String foodName = c1.getString(c1.getColumnIndex("Food_Name"));
                    buffer.append(foodName + " \n");
                } while (c1.moveToNext());
            }
        }
        c1.close();

        return buffer.toString();
    }

//    public  int delete(String uname) {
//        SQLiteDatabase db = foodDB.getWritableDatabase();
//        String[] whereArgs ={uname};
//
//        int count =db.delete(FoodDB.TABLE_NAME ,FoodDB.NAME+" = ?",whereArgs);
//        return  count;
//    }
//
//    public int updateName(String oldName , String newName) {
//        SQLiteDatabase db = foodDB.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(FoodDB.NAME,newName);
//        String[] whereArgs= {oldName};
//        int count =db.update(FoodDB.TABLE_NAME,contentValues, FoodDB.NAME+" = ?",whereArgs );
//        return count;
//    }

    static class FoodDB extends SQLiteOpenHelper {

        private static SQLiteDatabase sqliteDb;
        private static FoodDB instance;
        private static final int DATABASE_VERSION = 1;

        private Context context;
        static Cursor cursor = null;

        FoodDB(Context context, String name, SQLiteDatabase.CursorFactory factory,
               int version) {
            super(context, name, factory, version);
            this.context = context;

        }

        private static void initialize(Context context, String databaseName) {
            if (instance == null) {

                if (!checkDatabase(context, databaseName)) {

                    try {
                        copyDataBase(context, databaseName);
                    } catch (IOException e) {

                        System.out.println(databaseName
                                + " does not exists ");
                    }
                }

                instance = new FoodDB(context, databaseName, null,
                        DATABASE_VERSION);
                sqliteDb = instance.getWritableDatabase();

                System.out.println("instance of  " + databaseName + " created ");
            }
        }

        public static final FoodDB getInstance(Context context,
                                               String databaseName) {
            initialize(context, databaseName);
            return instance;
        }

        public SQLiteDatabase getDatabase() {
            return sqliteDb;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        private static void copyDataBase(Context aContext, String databaseName)
                throws IOException {

            InputStream myInput = aContext.getAssets().open(databaseName);

            String outFileName = getDatabasePath(aContext, databaseName);

            File f = new File("/data/data/" + aContext.getPackageName()
                    + "/databases/");
            if (!f.exists())
                f.mkdir();

            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();

            System.out.println(databaseName + " copied");
        }

        public static boolean checkDatabase(Context aContext, String databaseName) {
            SQLiteDatabase checkDB = null;

            try {
                String myPath = getDatabasePath(aContext, databaseName);

                checkDB = SQLiteDatabase.openDatabase(myPath, null,
                        SQLiteDatabase.OPEN_READONLY);

                checkDB.close();
            } catch (SQLiteException e) {

                System.out.println(databaseName + " does not exists");
            }

            return checkDB != null ? true : false;
        }

        private static String getDatabasePath(Context aContext, String databaseName) {
            return "/data/data/" + aContext.getPackageName() + "/databases/"
                    + databaseName;
        }

        public static Cursor rawQuery(String query) {
            try {
                if (sqliteDb.isOpen()) {
                    sqliteDb.close();
                }
                sqliteDb = instance.getWritableDatabase();

                cursor = null;
                cursor = sqliteDb.rawQuery(query, null);
            } catch (Exception e) {
                System.out.println("DB ERROR  " + e.getMessage());
                e.printStackTrace();
            }
            return cursor;
        }

        public static void execute(String query) {
            try {
                if (sqliteDb.isOpen()) {
                    sqliteDb.close();
                }
                sqliteDb = instance.getWritableDatabase();
                sqliteDb.execSQL(query);
            } catch (Exception e) {
                System.out.println("DB ERROR  " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
