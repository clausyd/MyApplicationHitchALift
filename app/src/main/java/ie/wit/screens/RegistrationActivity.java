package ie.wit.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import io.realm.Realm;
import models.Person;
import models.UserCradentials;

public class RegistrationActivity extends Login_ChoiceActivity {

     EditText fNameBox;
     EditText lNameBox;
     EditText emailBox;
     EditText passwordBox;
     EditText rePasswordBox;
     EditText dob;
     Button submmit;


    String dateOFBirth;
    String firstName;
    String surname;
    String email;
    String password;
    String rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submmit = findViewById(R.id.submit);


        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCustomer();

            }
        });

    }


    public void addCustomer() {
        fNameBox = findViewById(R.id.firstName);
        lNameBox = findViewById(R.id.surname);
        emailBox = findViewById(R.id.email);
        passwordBox = findViewById(R.id.password);
        rePasswordBox = findViewById(R.id.retypePassword);
        dob = findViewById(R.id.dob);

        firstName = fNameBox.getText().toString();
        surname = lNameBox.getText().toString();
        email = emailBox.getText().toString();
        dateOFBirth = dob.getText().toString();
        password = passwordBox.getText().toString();
        rePassword = rePasswordBox.getText().toString();

        if (fNameBox.getText().toString().trim().length() > 0 && lNameBox.getText().toString().trim().length() > 0
                && emailBox.getText().toString().trim().length() > 0 && passwordBox.getText().toString().trim().length() > 0 &&
                rePasswordBox.getText().toString().trim().length() > 0) {
            if (password.equals(rePassword)) {

                    final Person cust = new Person();
                    final UserCradentials u = new UserCradentials();
                    cust.setEmail(email);
                    cust.setFirstName(firstName);
                    cust.setSurname(surname);
                    cust.setDOB(dateOFBirth);
                    u.setEmail(email);
                    u.setPassword(password);
                    myApp.searchEmail(email);
                    myApp.dbManager.add(u);
                    myApp.dbManager.add(cust);

                    Toast.makeText(getApplicationContext(), "Account Setup is Complete", Toast.LENGTH_LONG).show();

                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    myIntent.putExtra("Email", email);
                    startActivityForResult(myIntent, 0);
                }

            }else {
                    Toast.makeText(getApplicationContext(), "Enter Your Details", Toast.LENGTH_LONG).show();
        }

    }

}
