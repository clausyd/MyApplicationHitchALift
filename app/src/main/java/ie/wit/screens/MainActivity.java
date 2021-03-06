package ie.wit.screens;

import android.app.DialogFragment;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import datePicker.DatePickerFragment;
import io.realm.Realm;
import io.realm.RealmResults;
import models.Journey;
import models.Person;

public class MainActivity extends Login_ChoiceActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText dateSelector;
    TextView nameBox;
    String loginEmail;
    String journeyForm;
    String journeyTo;
    String date;
    String name;
    String email;
    Button addJourney;
    AutoCompleteTextView autoCompleteTextViewUserFrom;
    AutoCompleteTextView autoCompleteTextViewUserTo;
    String [] Country_Names;
    GoogleSignInAccount account;
    String googleEmial;
    Uri myPic;
    String firstName, lastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addJourney = findViewById(R.id.addJounrey);
        autoCompleteTextViewUserFrom = findViewById(R.id.autoCompleteTextVieUserFrom);
        autoCompleteTextViewUserTo = findViewById(R.id.autoCompleteTextVieUserTo);
        Country_Names = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Country_Names);
        autoCompleteTextViewUserFrom.setAdapter(adapter);
        autoCompleteTextViewUserTo.setAdapter(adapter);
        dateSelector = findViewById(R.id.dateSelector);
        account = myApp.returnGoogleAccount();
        if(account !=null) {
            googleEmial = account.getEmail();
            firstName = account.getFamilyName();
             myPic = account.getPhotoUrl();
            lastName = account.getGivenName();

        }
        email  = myApp.getEmail();

        nameBox = findViewById(R.id.name);
        if(email != null){
            nameBox.setText(email);

        }else{
            nameBox.setText(googleEmial);

        }

        addJourney.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             returnUpdatedJourney();
            }
        });

        dateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView myName =navigationView.getHeaderView(0).findViewById(R.id.myName);
        TextView myEmail =navigationView.getHeaderView(0).findViewById(R.id.myEmail);
        ImageView myPicBox =navigationView.getHeaderView(0).findViewById(R.id.myPic);
        myName.setText(firstName + " "+ lastName);
        myEmail.setText(googleEmial);
        myPicBox.setImageURI(myPic);
        //Android, G. (2018). Get Google+ profile picture from Uri to Bitmap on Android. [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/36781830/get-google-profile-picture-from-uri-to-bitmap-on-android [Accessed 24 Apr. 2018].
        Picasso.with(this)
                .load(myPic)
                .into(myPicBox)
           ;

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
            RealmResults<Person> results = myApp.dbManager.deleteUserAccount(loginEmail);
            if(!results.isEmpty()){
                Toast.makeText(getApplicationContext(), "Account Removed ", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), Home_Screen_Activity.class);
                startActivityForResult(myIntent, 0);
            }else{
                Toast.makeText(getApplicationContext(), "Error Removing Account ", Toast.LENGTH_SHORT).show();
            }
            return true;
        }else if(id == R.id.my_journeys){

            Intent myIntent = new Intent(getApplicationContext(),MyJourneyList.class);
            myIntent.putExtra("emailJourney", email);
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
        } else if (id == R.id.addCar) {
            Intent myIntent = new Intent(getApplicationContext(), AddCarActivity.class);
            myIntent.putExtra("carEmail", email);
            startActivityForResult(myIntent, 0);


        } else if (id == R.id.nav_emailActivation) {


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void returnUpdatedJourney(){



        name = nameBox.getText().toString();
        journeyForm = autoCompleteTextViewUserFrom.getText().toString();
        journeyTo = autoCompleteTextViewUserTo.getText().toString();
        date = dateSelector.getText().toString();

//Realm.io. (2018). Realm: Create reactive mobile apps in a fraction of the time. [online] Available at: https://realm.io/docs/java/latest/ [Accessed 1 Mar. 2018].
        if(autoCompleteTextViewUserTo.getText().toString().trim().length() >0 && autoCompleteTextViewUserFrom.getText().toString().trim().length() >0
                && dateSelector.getText().toString().trim().length() >0) {
            final Journey journey = new Journey();
            journey.setEmail(name);
            journey.setStartCounty(journeyForm);
            journey.setFinishCounty(journeyTo);
            journey.setDate(String.valueOf(date));
            myApp.dbManager.add(journey);
        }else {
            Toast.makeText(getApplicationContext(), "Enter Journey", Toast.LENGTH_LONG).show();
        }
    }
}
