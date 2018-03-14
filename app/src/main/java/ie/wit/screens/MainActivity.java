package ie.wit.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import models.Journey;
import models.Person;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText dateSelector;
    TextView nameBox;
    String loginEmail;
    String journeyForm;
    String homeEmail;
    String journeyTo;
    String date;
    String name;
    String email;
    String emailJourney;
    Button addJourney;
    AutoCompleteTextView autoCompleteTextViewUserFrom;
    AutoCompleteTextView autoCompleteTextViewUserTo;
    String [] Country_Names;
    Realm realm = Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        autoCompleteTextViewUserFrom = findViewById(R.id.autoCompleteTextVieUserFrom);
        autoCompleteTextViewUserTo = findViewById(R.id.autoCompleteTextVieUserTo);
        Country_Names = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Country_Names);
        autoCompleteTextViewUserFrom.setAdapter(adapter);
        autoCompleteTextViewUserTo.setAdapter(adapter);
        dateSelector = findViewById(R.id.dateSelector);
        Intent intent = getIntent();
        email  =  intent.getStringExtra("Email");
        loginEmail = intent.getStringExtra("loginEmail");
        emailJourney = intent.getStringExtra("emailJourney");
        homeEmail = intent.getStringExtra("homeEmail");


        addJourney = findViewById(R.id.addJounrey);
        nameBox = findViewById(R.id.name);

        if(email != null){
            nameBox.setText(email);
        }else if(loginEmail != null){
            nameBox.setText(loginEmail);
        }else if(emailJourney != null){
            nameBox.setText(emailJourney);
        }else{
            nameBox.setText(homeEmail);
        }




        addJourney.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = nameBox.getText().toString();
                journeyForm = autoCompleteTextViewUserFrom.getText().toString();
                journeyTo = autoCompleteTextViewUserTo.getText().toString();
                date = dateSelector.getText().toString();
                boolean ifJourneyAdded;
//Realm.io. (2018). Realm: Create reactive mobile apps in a fraction of the time. [online] Available at: https://realm.io/docs/java/latest/ [Accessed 1 Mar. 2018].
                if(autoCompleteTextViewUserTo.getText().toString().trim().length() >0 && autoCompleteTextViewUserFrom.getText().toString().trim().length() >0
                        && dateSelector.getText().toString().trim().length() >0) {
                    Realm realm = Realm.getDefaultInstance();
                    try {
                        final Journey journey = new Journey();
                        journey.setEmail(name);
                        journey.setStartCounty(journeyForm);
                        journey.setFinishCounty(journeyTo);
                        journey.setDate(String.valueOf(date));
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                try{
                                    realm.copyToRealm(journey);
                                    Toast.makeText(getApplicationContext(), "Journey Added", Toast.LENGTH_LONG).show();

                                }catch(Exception e){
                                    Toast.makeText(getApplicationContext(), "Journey Already Exists", Toast.LENGTH_LONG).show();

                                }
                            }
                        });

                    }finally {
                        realm.close();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Enter Journey", Toast.LENGTH_LONG).show();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home__user__screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<Person> results = realm.where(Person.class)
                    .equalTo("email", loginEmail).findAll();
            realm.beginTransaction();
            if(!results.isEmpty()){
                results.deleteAllFromRealm();
                realm.commitTransaction();
                realm.close();
                Toast.makeText(getApplicationContext(), "Account Removed ", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), Home_Screen_Activity.class);
                startActivityForResult(myIntent, 0);

            }else{
                Toast.makeText(getApplicationContext(), "Error Removing Account ", Toast.LENGTH_SHORT).show();

            }
            return true;
        }else if(id == R.id.my_journeys){

            Intent myIntent = new Intent(getApplicationContext(),MyJourneyList.class);
            myIntent.putExtra("emailJourney", loginEmail);
            startActivityForResult(myIntent, 0);

        }else if(id == R.id.logOut){
            Intent myIntent = new Intent(getApplicationContext(),Home_Screen_Activity.class);
            startActivityForResult(myIntent, 0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
