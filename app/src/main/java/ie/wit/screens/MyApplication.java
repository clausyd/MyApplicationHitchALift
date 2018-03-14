package ie.wit.screens;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by clausyd on 01/03/18.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("HitchALift.realm").build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();
        Log.d("HitchALift.realm", "path: " + realm.getPath());


    }
}

