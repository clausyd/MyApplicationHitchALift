package ie.wit.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collection;

import customAdapters.JourneyAdapter;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import models.Journey;

public class ListActivity extends AppCompatActivity {

    Journey j;
    ListView listView;
    RealmResults<Journey> realmResultsPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.journeyList);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String from = bundle.getString("From");
        String to = bundle.getString("To");
        String date = bundle.getString("Date");

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        realmResultsPerson = getList();
        Log.d("", "path: " + realm.getPath());



        //RealmResults<Journey> realmResultsPerson=  realm.where(Journey.class).equalTo("startCounty", String.valueOf(from)).findAll();

        //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();


        JourneyAdapter adapter = new JourneyAdapter(this, realmResultsPerson);
        listView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private RealmResults<Journey> getList(){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Journey.class).findAll();
    }}

