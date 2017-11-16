package kmitl.project.surasee2012.eatrightnow.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gun on 11/6/2017.
 */

public class FoodDbAdapter {

    private static final String DB_NAME = "EatRightNow_DB.db";

    private int previousRandomIndex = -1;

    public FoodDbAdapter(Context context) {
        FoodDB.getInstance(context, DB_NAME);
    }

    public ArrayList<FoodsListItems> getData() {
        String query = "SELECT * FROM Foods";
        Cursor c1 = FoodDB.rawQuery(query);
        ArrayList<FoodsListItems> foodList = new ArrayList<>();

        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    FoodsListItems foodsListItems = new FoodsListItems();
                    foodsListItems.setFood_ID(c1.getInt(c1.getColumnIndex("Food_ID")));
                    foodsListItems.setFood_Name(c1.getString(c1.getColumnIndex("Food_Name")));
                    foodsListItems.setFood_Calories(c1.getInt(c1.getColumnIndex("Food_Calories")));
                    foodsListItems.setFood_Favorite(c1.getInt(c1.getColumnIndex("Food_Favorite")));
                    foodList.add(foodsListItems);
                } while (c1.moveToNext());
            }
        }
        c1.close();

        return foodList;
    }

    public ArrayList<FoodsListItems> getData(String tagFilter, String specialFilter) {
        String query = "SELECT Food_Name, Food_Calories FROM Foods";
        if (tagFilter.equals("ทั้งหมด")) {
            if (specialFilter.equals("ของโปรด")) {
                query += " WHERE Food_Favorite = 1 GROUP BY Food_Name";
            }
        } else {
            query += " INNER JOIN Foods_Tags ON Foods.Food_ID = Foods_Tags.Food_ID" +
                    " INNER JOIN Tags ON Foods_Tags.Tag_ID = Tags.Tag_ID" +
                    " WHERE Tags.Tag_Title = '" + tagFilter + "'";
            if (specialFilter.equals("ของโปรด")) {
                query += " AND Foods.Food_Favorite = 1";
            }
        }
        query += ";";

        Cursor c1 = FoodDB.rawQuery(query);
        ArrayList<FoodsListItems> foodList = new ArrayList<>();

        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    FoodsListItems foodsListItems = new FoodsListItems();
                    foodsListItems.setFood_Name(c1.getString(c1.getColumnIndex("Food_Name")));
                    foodsListItems.setFood_Calories(c1.getInt(c1.getColumnIndex("Food_Calories")));
                    foodList.add(foodsListItems);
                } while (c1.moveToNext());
            }
        }
        c1.close();

        return foodList;
    }

    public FoodRandom getRandom(ArrayList<FoodsListItems> foodList) {
        FoodRandom foodRandom = new FoodRandom();
        try {
            Random random = new Random();
            int randomIndex;
            do {
                randomIndex = random.nextInt(foodList.size());
            } while (randomIndex == previousRandomIndex && foodList.size() > 1);
            if (foodList.size() == 1 && randomIndex == previousRandomIndex) {
                foodRandom.setErrorCollector(1);
                return foodRandom;
            }
            previousRandomIndex = randomIndex;
            foodRandom.setFood_Name(foodList.get(randomIndex).getFood_Name());
            foodRandom.setFood_Calories(foodList.get(randomIndex).getFood_Calories());
        } catch (Exception e) {
            foodRandom.setErrorCollector(2);
            return foodRandom;
        }
        foodRandom.setErrorCollector(0);
        return foodRandom;
    }

    public void setPreviousRandomIndex(int previousRandomIndex) {
        this.previousRandomIndex = previousRandomIndex;
    }

    public void setFavorite(int foodID, int favoriteValue) {
        String UpdateQuery = "UPDATE Foods SET Food_Favorite = " + Integer.toString(favoriteValue)
                + " WHERE Food_ID = " + Integer.toString(foodID) + ";";
        FoodDB.execute(UpdateQuery);
    }

    static class FoodDB extends SQLiteOpenHelper {

        private static SQLiteDatabase sqliteDb;
        private static FoodDB instance;
        private static final int DATABASE_VERSION = 1;

        static Cursor cursor = null;

        FoodDB(Context context, String name, SQLiteDatabase.CursorFactory factory,
               int version) {
            super(context, name, factory, version);
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
