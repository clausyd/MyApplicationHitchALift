package ie.wit.screens;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.Serializable;
import java.security.AccessController;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import datePicker.DatePickerFragment;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import models.Journey;
import models.Person;
import models.UserCradentials;

public class Home_Screen_Activity extends AppCompatActivity {


    AutoCompleteTextView autoCompleteTextView1;
    AutoCompleteTextView autoCompleteTextView2;
    EditText dateSelector;
    ListView listView;
    String [] Country_Names;
    int day, month, year;
    public MyApp myApp;

    ImageButton loginSignUp;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = (MyApp) getApplication();
        myApp.dbManager.open();
        //addUserOnStart();
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // YouTube. (2018). Android Studio Tutorial - 10 - Working with AutoCompleteTextView. [online] Available at: https://www.youtube.com/watch?v=dEhE9MMR7mg&list=PL70y70C-OGAZucFOcbWyTmXZmB4amoHP3&index=1 [Accessed 7 Feb. 2018].        autoCompleteTextView1 = findViewById(R.id.to);
        autoCompleteTextView1 = findViewById(R.id.from);
        autoCompleteTextView2 = findViewById(R.id.to);
        dateSelector = findViewById(R.id.dateSelectorHomePage);
        Country_Names = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Country_Names);
        autoCompleteTextView1.setAdapter(adapter);
        autoCompleteTextView2.setAdapter(adapter);
        search = findViewById(R.id.search);
        loginSignUp = findViewById(R.id.loginSignUp);

        dateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });





        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnJourneys(view);
            }
        });

        loginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Login_ChoiceActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myApp.dbManager.close();
    }

        public void returnJourneys(View  view) {
            String to;
            String from;
            String date;
            RealmResults<Journey> realmResultsJourney;

            from = autoCompleteTextView1.getText().toString();
            to = autoCompleteTextView2.getText().toString();
            date = dateSelector.getText().toString();

            //realmResultsJourney = myApp.dbManager.selectedJourneys(to, from, date);

            if (autoCompleteTextView1.getText().toString().trim().length() > 0 && autoCompleteTextView2.getText().toString().trim().length() > 0 && dateSelector.getText().toString().trim().length() > 0) {

                    Intent myIntent = new Intent(view.getContext(), ListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("To", to);
                    bundle.putString("Date", date);
                    bundle.putString("From", from);
                    myIntent.putExtras(bundle);
                    startActivityForResult(myIntent, 0);

            } else {
                Toast.makeText(getApplicationContext(), "Please Enter A Journey", Toast.LENGTH_LONG).show();
            }
        }
 public void addUserOnStart(){
     Person p = new Person();
     p.setFirstName("eoin");
     p.setSurname("Dalton");
     p.setEmail("c@gmail.com");
     p.setDOB("05/12/1984");
     myApp.dbManager.add(p);

     UserCradentials u = new UserCradentials();
     u.setEmail("c@gmail.com");
     u.setPassword("1234");
     myApp.dbManager.add(u);

     Journey j = new Journey();
     j.setStartCounty("Kilkenny");
     j.setFinishCounty("Dublin");
     j.setDate("2018");
     myApp.dbManager.add(j);

     Journey r = new Journey();
     r.setStartCounty("Limrick");
     r.setFinishCounty("Louth");
     r.setDate("2018");
     myApp.dbManager.add(r);

     Person l = new Person();
     p.setFirstName("John");
     p.setSurname("Dalton");
     p.setEmail("e@gmail.com");
     p.setDOB("05/12/1995");
     myApp.dbManager.add(p);

     UserCradentials t = new UserCradentials();
     u.setEmail("e@gmail.com");
     u.setPassword("1235");
     myApp.dbManager.add(u);

     Journey o = new Journey();
     o.setStartCounty("Kilkenny");
     o.setFinishCounty("Dublin");
     o.setDate("2018");
     myApp.dbManager.add(o);

     Journey h = new Journey();
     h.setStartCounty("Limrick");
     h.setFinishCounty("Louth");
     h.setDate("2018");
     myApp.dbManager.add(h);
 }


    }


