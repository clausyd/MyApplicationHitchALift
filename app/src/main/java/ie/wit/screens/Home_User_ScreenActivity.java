package ie.wit.screens;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import models.Journey;
import models.Person;

public class Home_User_ScreenActivity extends AppCompatActivity {

    private EditText dateSelector;
    TextView nameBox;

    String journeyForm;
    String journeyTo;
    String date;
    String name;
    String email;
    Button addJourney;
    AutoCompleteTextView autoCompleteTextViewUserFrom;
    AutoCompleteTextView autoCompleteTextViewUserTo;
    String [] Country_Names;
    Realm realm = Realm.getDefaultInstance();
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        view = this.view;


        autoCompleteTextViewUserFrom = findViewById(R.id.autoCompleteTextVieUserFrom);
        autoCompleteTextViewUserTo = findViewById(R.id.autoCompleteTextVieUserTo);
        Country_Names = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Country_Names);
        autoCompleteTextViewUserFrom.setAdapter(adapter);
        autoCompleteTextViewUserTo.setAdapter(adapter);
        dateSelector = findViewById(R.id.dateSelector);
        Intent intent = getIntent();
        email  =  intent.getStringExtra("Email");


        addJourney = findViewById(R.id.addJounrey);
        nameBox = findViewById(R.id.name);
        nameBox.setText(email);



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
                        journey.setEmail(email);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<Person> results = realm.where(Person.class).equalTo("email", email).findAll();
            realm.beginTransaction();
            if(results != null){
                results.deleteAllFromRealm();
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Account Removed ", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), Home_Screen_Activity.class);
                startActivityForResult(myIntent, 0);

            }else{
                Toast.makeText(getApplicationContext(), "Error Removing Account ", Toast.LENGTH_SHORT).show();

            }
            return true;
        }else if(id == R.id.update_account){

        }else if(id == R.id.logOut){

        }

        return super.onOptionsItemSelected(item);
    }


//private void datePicker(){
//    Calendar calendar = Calendar.getInstance();
//    final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            Calendar newDate = Calendar.getInstance();
//            year = view.getYear();
//            monthOfYear = view.getMonth();
//            dayOfMonth = view.getDayOfMonth();
//            dateSelector.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
//
//
//        }
//
//    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//    datePickerDialog.show();
//}


}











