package ie.wit.screens;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import customAdapters.JourneyAdapter;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import models.Journey;

public class ListActivity extends Home_Screen_Activity {

    ListView listView;
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

        final JourneyAdapter adapter = myApp.dbManager.getJourenys(from,to,date);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Journey j = (Journey) listView.getAdapter().getItem(1);
                String custEmail = j.getEmail();
                Intent myIntent = new Intent(getApplicationContext(), Update_JourneyActivity.class);
                myIntent.putExtra("custEmail",custEmail );
                startActivityForResult(myIntent, 0);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.journey_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.journey_list) {
            Intent myIntent = new Intent(getApplicationContext(), Home_Screen_Activity.class);
            startActivityForResult(myIntent, 0);}
        return super.onOptionsItemSelected(item);
    }
}

