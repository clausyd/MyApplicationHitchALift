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
    public String email;

    public void searchEmail(String email){
        this.email = email;

    }

    public String getEmail(){
        return email;
    }

    @Override
    public void onCreate() {

        {
            super.onCreate();
            Log.v("HitchALift", "Hitch A Lift App Started");
            dbManager = new DBManager(this);
            dbManager.open();
            Log.d("HitchALift2.realm", "Realm Database Created & Opened");


        }
    }
}

