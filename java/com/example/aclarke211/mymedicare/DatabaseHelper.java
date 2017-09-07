package com.example.aclarke211.mymedicare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//=========================================================================================================================================\\
public class DatabaseHelper extends SQLiteOpenHelper {

    //variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mymedicare.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_GP_NAME = "gpName";
    private static final String COLUMN_GP_NUMBER = "gpNumber";
    private static final String COLUMN_TEMPERATURE = "temperature";
    private static final String COLUMN_LOW_BLOOD_PRESSURE = "lowBloodPressure";
    private static final String COLUMN_HIGH_BLOOD_PRESSURE = "highBloodPressure";
    private static final String COLUMN_HEART_RATE = "heartRate";
    private static final String COLUMN_BACKGROUND_COLOUR = "backgroundColour";

    //database object
    SQLiteDatabase db;

    //add table to database
    /*needed to remove "not null" from newly added values for table to be created successfully*/
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY not null , " +
            "name TEXT not null , email TEXT not null , username TEXT not null , password TEXT not null , " +
            "age TEXT , address TEXT , gpName TEXT , gpNumber TEXT , " +
            "temperature FLOAT , lowBloodPressure TEXT , highBloodPressure TEXT , heartRate TEXT , backgroundColour TEXT );";


    //constructor
    public DatabaseHelper(Context context) {

        //calls parent method's constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

//=========================================================================================================================================\\
    //needed as class extends SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {

        //create the database
        db.execSQL(TABLE_CREATE);
        //assign to database object created in class
        this.db = db;

    }

//=========================================================================================================================================\\
    public void insertContact(Contact contact) {

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //select all values from contact table in database
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        //finding how many rows in database
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_USERNAME, contact.getUsername());
        values.put(COLUMN_PASSWORD, contact.getPassword());
        values.put(COLUMN_AGE, contact.getAge());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_GP_NAME, contact.getGpName());
        values.put(COLUMN_GP_NUMBER, contact.getGpNumber());
        values.put(COLUMN_TEMPERATURE, contact.getTemperature());
        values.put(COLUMN_LOW_BLOOD_PRESSURE, contact.getLowBloodPressure());
        values.put(COLUMN_HIGH_BLOOD_PRESSURE, contact.getHighBloodPressure());
        values.put(COLUMN_HEART_RATE, contact.getHeartRate());
        values.put(COLUMN_BACKGROUND_COLOUR, contact.getBackgroundColour());

        //insert data to database (String table, String nullColumnHack, ContentValues)
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

//=========================================================================================================================================\\
    public void updateBackgroundColour(Contact contact) {

        db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_BACKGROUND_COLOUR, contact.getBackgroundColour());

        // Which row to update, based on the ID
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(contact.getId()) };

        db.update(
                DatabaseHelper.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        db.close();

    }


//=========================================================================================================================================\\
    public void updateDetails(Contact contact) {

        db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_AGE, contact.getAge());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_GP_NAME, contact.getGpName());
        values.put(COLUMN_GP_NUMBER, contact.getGpNumber());

        // Which row to update, based on the ID
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(contact.getId()) };

        db.update(
                DatabaseHelper.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        db.close();

    }

    //=========================================================================================================================================\\
    public void updateValues(Contact contact) {

        db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEMPERATURE, contact.getTemperature());
        values.put(COLUMN_LOW_BLOOD_PRESSURE, contact.getLowBloodPressure());
        values.put(COLUMN_HIGH_BLOOD_PRESSURE, contact.getHighBloodPressure());
        values.put(COLUMN_HEART_RATE, contact.getHeartRate());

        // Which row to update, based on the ID
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(contact.getId()) };

        db.update(
                DatabaseHelper.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        db.close();

    }

    //=========================================================================================================================================\\
    public String searchID(String username) {

        db = this.getReadableDatabase();
        String query = "SELECT username, id FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, idReceived;
        idReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(username)) {

                    idReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return idReceived;
    }

//=========================================================================================================================================\\
    public String searchName(String username) {

        db = this.getReadableDatabase();
        String query = "SELECT username, name FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, nameReceived;
        nameReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(username)) {

                    nameReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return nameReceived;
    }

//=========================================================================================================================================\\
    public String searchEmail(String username) {

        db = this.getReadableDatabase();
        String query = "SELECT username, email FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, emailReceived;
        emailReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(username)) {

                    emailReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return emailReceived;
    }

//=========================================================================================================================================\\
    public String searchUsername(String username) {

        db = this.getReadableDatabase();
        String query = "SELECT username FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, usernameReceived;
        usernameReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(username)) {

                    usernameReceived = cursor.getString(0);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return usernameReceived;
    }

//=========================================================================================================================================\\
    public String searchPassword(String password) {

        db = this.getReadableDatabase();
        String query = "SELECT username , password FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, passwordReceived;
        passwordReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(password)) {

                    passwordReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return passwordReceived;
    }

//=========================================================================================================================================\\
    public String searchAge(String age) {

        db = this.getReadableDatabase();
        String query = "SELECT username, age FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, ageReceived;
        ageReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(age)) {

                    ageReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return ageReceived;
    }

//=========================================================================================================================================\\
    public String searchAddress(String address) {

        db = this.getReadableDatabase();
        String query = "SELECT username, address FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, addressReceived;
        addressReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(address)) {

                    addressReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return addressReceived;
    }

    //=========================================================================================================================================\\
    public String searchGpName(String gpName) {

        db = this.getReadableDatabase();
        String query = "SELECT username, gpName FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, gpNameReceived;
        gpNameReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(gpName)) {

                    gpNameReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return gpNameReceived;
    }

//=========================================================================================================================================\\
    public String searchGpNumber(String gpNumber) {

        db = this.getReadableDatabase();
        String query = "SELECT username, gpNumber FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, gpNumberReceived;
        gpNumberReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(gpNumber)) {

                    gpNumberReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return gpNumberReceived;
    }

//=========================================================================================================================================\\
    public String searchTemperature(String temperature) {

        db = this.getReadableDatabase();
        String query = "SELECT username, temperature FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, strTemperatureReceived;
        float temperatureReceived;
        strTemperatureReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(temperature)) {

                    temperatureReceived = cursor.getFloat(1);
                    strTemperatureReceived = Float.toString(temperatureReceived);
                    break;

                }
            }
            while(cursor.moveToNext());
        }


        return strTemperatureReceived;
    }

//=========================================================================================================================================\\
    public String searchLowBloodPressure(String bloodPressure) {

        db = this.getReadableDatabase();
        String query = "SELECT username, lowBloodPressure FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, lowBloodPressureReceived;
        lowBloodPressureReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(bloodPressure)) {

                    lowBloodPressureReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return lowBloodPressureReceived;
    }

//=========================================================================================================================================\\
    public String searchHighBloodPressure(String bloodPressure) {

        db = this.getReadableDatabase();
        String query = "SELECT username, highBloodPressure FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, highBloodPressureReceived;
        highBloodPressureReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(bloodPressure)) {

                    highBloodPressureReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return highBloodPressureReceived;
    }

//=========================================================================================================================================\\
    public String searchHeartRate(String heartRate) {

        db = this.getReadableDatabase();
        String query = "SELECT username, heartRate FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, heartRateReceived;
        heartRateReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(heartRate)) {

                    heartRateReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return heartRateReceived;
    }

    //=========================================================================================================================================\\
    public String searchBackgroundColour(String bgColour) {

        db = this.getReadableDatabase();
        String query = "SELECT username, backgroundColour FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String string, backgroundColourReceived;
        backgroundColourReceived = "not found";

        if (cursor.moveToFirst()) {

            do {

                //store string value of column '0' in database
                string = cursor.getString(0);

                if (string.equals(bgColour)) {

                    backgroundColourReceived = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        return backgroundColourReceived;
    }

//=========================================================================================================================================\\
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

//=========================================================================================================================================\\
}
//=========================================================================================================================================\\