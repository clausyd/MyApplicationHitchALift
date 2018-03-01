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
import journeypackage.Journey;
import personpackage.Person;
import personpackage.PersonManager;

public class RegistrationActivity extends AppCompatActivity {

    private EditText fNameBox;
    private EditText lNameBox;
    private EditText emailBox;
    private EditText passwordBox;
    private EditText rePasswordBox;
    private Button submmit;



    String firstName;
    String surname;
    String email;
    String password;
    String rePassword;
    PersonManager cust = new PersonManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fNameBox = findViewById(R.id.firstName);
        lNameBox = findViewById(R.id.surname);
        emailBox = findViewById(R.id.email);
        passwordBox = findViewById(R.id.password);
        rePasswordBox = findViewById(R.id.retypePassword);
        submmit = findViewById(R.id.submit);




        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCustomer(view);


            }
        });

    }


    public void addCustomer(View view) {

        firstName = fNameBox.getText().toString();
        surname = lNameBox.getText().toString();
        email = emailBox.getText().toString();
        password = passwordBox.getText().toString();
        rePassword = rePasswordBox.getText().toString();
        boolean ifPersonAdded;




        if (fNameBox.getText().toString().trim().length() > 0 && lNameBox.getText().toString().trim().length() > 0
                && emailBox.getText().toString().trim().length() > 0 && passwordBox.getText().toString().trim().length() > 0 &&
                rePasswordBox.getText().toString().trim().length() > 0) {
            if (password.equals(rePassword)) {

                Realm realm = Realm.getDefaultInstance();
                try {
                    final Person cust = new Person();
                    cust.setEmail(email);
                    cust.setFirstName(firstName);
                    cust.setSurname(surname);
                    cust.setPassword(password);
//                    realm.beginTransaction();
//                    Person realmOwner = realm.copyToRealm(cust);
//                    realm.commitTransaction();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            try{
                                realm.copyToRealm(cust);
                                Toast.makeText(getApplicationContext(), "Customer Added", Toast.LENGTH_LONG).show();

                            }catch(Exception e){
                                Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                    Toast.makeText(getApplicationContext(), "Account Setup is Complete", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(view.getContext(), Home_User_ScreenActivity.class);
                    myIntent.putExtra("Email", email);
                    startActivityForResult(myIntent, 0);
                }finally {
                    realm.close();
                }
            }


        }else {
            Toast.makeText(getApplicationContext(), "Enter Your Details", Toast.LENGTH_LONG).show();

        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
