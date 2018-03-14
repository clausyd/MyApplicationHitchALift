package ie.wit.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import models.Journey;
import models.Person;

public class Update_JourneyActivity extends AppCompatActivity {
    Person person;
    String id,email, fName,lName;
    Journey realmResultsJourney;
    Realm realm;
    EditText updateJourneyFrom,updateJourneyTo,updateJourneyDate;
    Button update;
    String uJF,uJT,uJD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__journey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateJourneyFrom = findViewById(R.id.updateJourneyFrom);
        updateJourneyTo = findViewById(R.id.updateJourneyTo);
        updateJourneyDate = findViewById(R.id.updateDate);
        update = findViewById(R.id.updateButton);

        Intent intent;
        intent = getIntent();
        id = intent.getStringExtra("PersonID");
        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

      update.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              uJF = updateJourneyFrom.getText().toString();
              uJT = updateJourneyTo.getText().toString();
              uJD = updateJourneyDate.getText().toString();
              if(uJF.trim().length() >0){
                  realm.beginTransaction();
                  realmResultsJourney=  realm.where(Journey.class)
                          .equalTo("id", id)
                          .findFirst();
                  realmResultsJourney.setStartCounty(uJF);
                  realm.commitTransaction();
                  realm.close();
                  Toast.makeText(getApplicationContext(), "Journey Starting Point Updated", Toast.LENGTH_LONG).show();

              } if(uJT.trim().length() >0){
                  realm.beginTransaction();
                  realmResultsJourney=  realm.where(Journey.class)
                          .equalTo("id", id)
                          .findFirst();
                  realmResultsJourney.setFinishCounty(uJT);
                  realm.commitTransaction();
                  realm.close();
                  Toast.makeText(getApplicationContext(), "Journey Destination Updated", Toast.LENGTH_LONG).show();

              } if(uJD.trim().length() >0) {
                    realm.beginTransaction();
                    realmResultsJourney = realm.where(Journey.class).equalTo("id", id).findFirst();
                    realmResultsJourney.setDate(uJD);
                    realm.commitTransaction();
                    realm.close();
                    Toast.makeText(getApplicationContext(), "Journey Date Updated", Toast.LENGTH_LONG).show();

                }
              
              realmResultsJourney = realm.where(Journey.class).equalTo("id", id).findFirst();
              email = realmResultsJourney.getEmail();
              Intent myIntent = new Intent(getApplicationContext(), MyJourneyList.class);
              myIntent.putExtra("journeyEmail", email);
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
            Intent myIntent = new Intent(getApplicationContext(), Home_User_ScreenActivity.class);
            myIntent.putExtra("homeEmail",email);
            startActivityForResult(myIntent, 0);


        }else if(id == R.id.logOutJourneyList){
            Intent myIntent = new Intent(getApplicationContext(),Home_Screen_Activity.class);
            startActivityForResult(myIntent, 0);
        }
        return super.onOptionsItemSelected(item);
    }
    }


