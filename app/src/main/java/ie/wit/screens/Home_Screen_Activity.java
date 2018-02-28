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
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import journeypackage.Journey;
import journeypackage.JourneyManager;

public class Home_Screen_Activity extends AppCompatActivity {


    JourneyManager journeyManager = new JourneyManager();
    String Antrim;
    String Armagh;
    String Carlow;
    String Clare;
    String   Cavan;
    String Cork;
    String Derry;
    String Donegal;
    String Down;
    String Dublin;
    String Fermanagh;
    String Galway;
    String Kerry;
    String Kildare;
    String Kilkenny;

    AutoCompleteTextView autoCompleteTextView1;
    AutoCompleteTextView autoCompleteTextView2;
    TextView dateSelector;
    String [] Country_Names;
    int day, month, year;
    TextView date;
    ImageButton loginSignUp;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // YouTube. (2018). Android Studio Tutorial - 10 - Working with AutoCompleteTextView. [online] Available at: https://www.youtube.com/watch?v=dEhE9MMR7mg&list=PL70y70C-OGAZucFOcbWyTmXZmB4amoHP3&index=1 [Accessed 7 Feb. 2018].        autoCompleteTextView1 = findViewById(R.id.to);
        autoCompleteTextView1 = findViewById(R.id.from);
        autoCompleteTextView2 = findViewById(R.id.to);
        dateSelector =findViewById(R.id.dateSelectorHomePage);
        Country_Names = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Country_Names);
        autoCompleteTextView1.setAdapter(adapter);
        autoCompleteTextView2.setAdapter(adapter);

        journeyManager.addJourney(new Journey(Kilkenny, Dublin, 2018 ));
        journeyManager.addJourney(new Journey(Kerry, Kildare, 2018 ));
        journeyManager.addJourney(new Journey(Galway, Clare, 2018 ));
        journeyManager.addJourney(new Journey(Cork, Carlow, 2018 ));

        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String to;
                String from;
                int d;
                from = autoCompleteTextView1.getText().toString();
                to = autoCompleteTextView2.getText().toString();
                d =Integer.parseInt( dateSelector.getText().toString());
                Journey j = new Journey(from, to, d);
                Intent myIntent = new Intent(view.getContext(), Journey_ListActivity.class);
                myIntent.putExtra("Journey", j);
                startActivityForResult(myIntent, 0);
            }
        });

        loginSignUp = findViewById(R.id.loginSignUp);

        loginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Login_ChoiceActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        //YouTube. (2018). Using Date/Time Picker Dialog in Android Studio. [online] Available at: https://www.youtube.com/watch?v=a_Ap6T4RlYU&index=9&list=PL70y70C-OGAZucFOcbWyTmXZmB4amoHP3 [Accessed 7 Feb. 2018].
        date = findViewById(R.id.dateSelector);

//        date.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View veiw){
////                datePicker();
//            }
//        });
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
                date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);


            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
