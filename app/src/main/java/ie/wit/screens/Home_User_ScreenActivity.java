package ie.wit.screens;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import journeypackage.Journey;
import journeypackage.JourneyManager;
import personpackage.Person;

public class Home_User_ScreenActivity extends AppCompatActivity {

    EditText dateSelector;
    TextView nameBox;

    String journeyForm;
    String journeyTo;
    int date;
    String name;
    String custFName;
    String email;
    JourneyManager journeyManager = new JourneyManager();
    Button addJourney;
    AutoCompleteTextView autoCompleteTextViewUserFrom;
    AutoCompleteTextView autoCompleteTextViewUserTo;
    String [] Country_Names;
    int day, month, year;
    int finalDay, finalMonth, finalYear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        autoCompleteTextViewUserFrom = findViewById(R.id.autoCompleteTextVieUserFrom);
        autoCompleteTextViewUserTo = findViewById(R.id.autoCompleteTextVieUserTo);
        Country_Names = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Country_Names);
        autoCompleteTextViewUserFrom.setAdapter(adapter);
        autoCompleteTextViewUserTo.setAdapter(adapter);

        Intent intent = getIntent();
        email  =  intent.getStringExtra("Email");




        addJourney = findViewById(R.id.addJounrey);
        nameBox = findViewById(R.id.name);
        nameBox.setText(email);
        dateSelector = findViewById(R.id.dateSelector);



//        dateSelector.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View veiw){
//               datePicker();
//            }
//        });


        addJourney.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = nameBox.getText().toString();
                journeyForm = autoCompleteTextViewUserFrom.getText().toString();
                journeyTo = autoCompleteTextViewUserTo.getText().toString();
                date = Integer.parseInt(dateSelector.getText().toString());
                boolean ifJourneyAdded;

                if(autoCompleteTextViewUserTo.getText().toString().trim().length() >0 && autoCompleteTextViewUserFrom.getText().toString().trim().length() >0
                        && dateSelector.getText().toString().trim().length() >0) {
                    Journey journey = new Journey(journeyForm, journeyTo, date);
                    journeyManager.addJourney(journey);

                        Toast.makeText(getApplicationContext(), "Journey Added", Toast.LENGTH_LONG).show();


                }else {
                    Toast.makeText(getApplicationContext(), "Enter Journey", Toast.LENGTH_LONG).show();

                }


            }
        });


    }


private void datePicker(){
    Calendar calendar = Calendar.getInstance();
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar newDate = Calendar.getInstance();
            year = view.getYear();
            monthOfYear = view.getMonth();
            dayOfMonth = view.getDayOfMonth();
            dateSelector.setText(dayOfMonth + "/" + monthOfYear + "/" + year);


        }

    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    datePickerDialog.show();
}


}











