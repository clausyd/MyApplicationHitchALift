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
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmQuery;

public class Home_Screen_Activity extends AppCompatActivity {


    AutoCompleteTextView autoCompleteTextView1;
    AutoCompleteTextView autoCompleteTextView2;
    EditText dateSelector;
    String [] Country_Names;
    int day, month, year;

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




        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String to;
                String from;
                String date;
                from = autoCompleteTextView1.getText().toString();
                to = autoCompleteTextView2.getText().toString();
                date = dateSelector.getText().toString();
                Intent myIntent = new Intent(view.getContext(), Journey_ListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("From" ,from);
                bundle.putString("To" ,to);
                bundle.putString("Date" ,date);
                myIntent.putExtras(bundle);
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
    }
}
