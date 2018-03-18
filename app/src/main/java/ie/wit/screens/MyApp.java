package ie.wit.screens;

import android.app.Application;
import android.util.Log;

import database.DBManager;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by clausyd on 01/03/18.
 */

public class MyApp extends Application{

    public DBManager dbManager;

    @Override
    public void onCreate() {

        {
            super.onCreate();
            Log.v("Donate", "Donation App Started");
            dbManager = new DBManager(this);
            dbManager.open();
            Log.d("HitchALift.realm", "Realm Database Created & Opened");


        }
    }
}

