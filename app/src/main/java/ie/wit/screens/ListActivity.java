package ie.wit.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import customAdapters.JourneyAdapter;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import models.Journey;

public class ListActivity extends AppCompatActivity {

    Journey j;
    ListView listView;
    RealmResults<Journey> realmResultsJourney;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.journeyList);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String from = bundle.getString("From");
        String to = bundle.getString("To");
        String date = bundle.getString("Date");


        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        realmResultsJourney=  realm.where(Journey.class)
                .equalTo("startCounty", from, Case.INSENSITIVE)
                .equalTo("finishCounty",to, Case.INSENSITIVE)
                .equalTo("date", date, Case.INSENSITIVE)
                .findAll();


        JourneyAdapter adapter = new JourneyAdapter(this, realmResultsJourney);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 //int p =



            }
        });




    }


    private RealmResults<Journey> getList(){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Journey.class).findAll();
    }
}

