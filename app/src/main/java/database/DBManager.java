package database;

import android.content.Context;
import android.database.SQLException;
import android.widget.Toast;

import customAdapters.JourneyAdapter;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import models.Car;
import models.Journey;
import models.Person;
import models.UserCradentials;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

/**
 * Created by clausyd on 17/03/18.
 */

public class DBManager {
    public Realm realmDatabase;
    public RealmResults<Journey> realmResultsJourney;
    public JourneyAdapter adapter;


    public DBManager(Context context) {

        Realm.init(context);
        RealmConfiguration config3 = new RealmConfiguration.Builder().name("HitchALift.realm").build();
        Realm.setDefaultConfiguration(config3);
        realmDatabase = Realm.getDefaultInstance();


    }

    public void open() throws SQLException {
        realmDatabase = Realm.getDefaultInstance();
    }

    public void close() {
        realmDatabase.close();
    }





    public void add(Journey j) {
        try {
            realmDatabase.beginTransaction();
            realmDatabase.copyToRealm(j);
            realmDatabase.commitTransaction();
            Toast.makeText(getApplicationContext(), "Journey Added", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Journey Already Exists", Toast.LENGTH_LONG).show();
        }
    }

    public void add(Person p) {
        try {
            realmDatabase.beginTransaction();
            realmDatabase.copyToRealm(p);
            realmDatabase.commitTransaction();
            Toast.makeText(getApplicationContext(), "Customer Added", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_LONG).show();
        }
    }

    public void add(UserCradentials u) {
           try{
            realmDatabase.beginTransaction();
            realmDatabase.copyToRealm(u);
            realmDatabase.commitTransaction();
        Toast.makeText(getApplicationContext(), "Journey Added", Toast.LENGTH_LONG).show();

    } catch (Exception e) {
        Toast.makeText(getApplicationContext(), "Journey Already Exists", Toast.LENGTH_LONG).show();
    }
    }

    public void add(Car car) {
    try{
        realmDatabase.beginTransaction();
        realmDatabase.copyToRealm( car);
        realmDatabase.commitTransaction();
        Toast.makeText(getApplicationContext(), "Car Added", Toast.LENGTH_LONG).show();

    } catch (Exception e) {
        Toast.makeText(getApplicationContext(), "Car Already Exists", Toast.LENGTH_LONG).show();
    }
    }

    public Journey updateJourney(String id, String updateJourneyFrom, String updateJourneyTo, String updateJourneyDate) {
        Journey realmResultsJourney = null;

        if (updateJourneyFrom.trim().length() > 0) {
            realmDatabase.beginTransaction();
            realmResultsJourney = realmDatabase.where(Journey.class).equalTo("id", id).findFirst();
            realmResultsJourney.setStartCounty(updateJourneyFrom);
            realmDatabase.commitTransaction();
            Toast.makeText(getApplicationContext(), "Journey Starting Point Updated", Toast.LENGTH_LONG).show();

        }
        if (updateJourneyTo.trim().length() > 0) {
            realmDatabase.beginTransaction();
            realmResultsJourney = realmDatabase.where(Journey.class).equalTo("id", id).findFirst();
            realmResultsJourney.setFinishCounty(updateJourneyTo);
            realmDatabase.commitTransaction();
            Toast.makeText(getApplicationContext(), "Journey Destination Updated", Toast.LENGTH_LONG).show();

        }
        if (updateJourneyDate.trim().length() > 0) {
            realmDatabase.beginTransaction();
            realmResultsJourney = realmDatabase.where(Journey.class).equalTo("id", id).findFirst();
            realmResultsJourney.setDate(updateJourneyDate);
            realmDatabase.commitTransaction();
            Toast.makeText(getApplicationContext(), "Journey Date Updated", Toast.LENGTH_LONG).show();

        }
        return realmResultsJourney;
    }

    public RealmResults<Person> p_word(String email, String password) {

        RealmResults realmResults = realmDatabase.where(UserCradentials.class).equalTo("email", email).equalTo("password", password).findAll();
        return realmResults;
    }

    public JourneyAdapter deleteJourneyList(String e, String f, String t) {
        realmDatabase.beginTransaction();
        RealmResults<Journey> r = realmDatabase.where(Journey.class).equalTo("email", e).equalTo("startCounty", f).equalTo("finishCounty", t).findAll();
        r.deleteAllFromRealm();
        realmDatabase.commitTransaction();
        return adapter;
    }


    public JourneyAdapter getJourenys(String from, String to, String date) {

        realmResultsJourney = realmDatabase.where(Journey.class)
                .equalTo("startCounty", from, Case.INSENSITIVE)
                .equalTo("finishCounty", to, Case.INSENSITIVE)
                .equalTo("date", date, Case.INSENSITIVE).findAll();
        adapter = new JourneyAdapter(getApplicationContext(), realmResultsJourney);
        return adapter;
    }

    public JourneyAdapter getUserJourneys(String email) {

            realmResultsJourney = realmDatabase.where(Journey.class)
                    .equalTo("email", email, Case.INSENSITIVE).findAll();
            adapter = new JourneyAdapter(getApplicationContext(), realmResultsJourney);
            return adapter;

    }

    public Journey get(String id) {
        return realmDatabase.where(Journey.class).equalTo("id", id).findAll().first();
    }


    public void reset() {
        realmDatabase.beginTransaction();
        realmDatabase.where(Journey.class).findAll().deleteAllFromRealm();
        realmDatabase.commitTransaction();
    }


    public RealmResults<Person> deleteUserAccount(String loginEmail) {

        RealmResults<Person> results = realmDatabase.where(Person.class).equalTo("email", loginEmail).findAll();
            realmDatabase.beginTransaction();
            results.deleteAllFromRealm();
            realmDatabase.commitTransaction();

        return results;
    }
}
