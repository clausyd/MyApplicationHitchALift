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
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import models.Person;

public class LoginActivity extends AppCompatActivity {
    EditText loginEmial;
    EditText loginPassword;
    Button login;
    RealmResults <Person> realmResults;



    private String email;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginEmial = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        login = findViewById(R.id.email_sign_in_button);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = loginEmial.getText().toString();
                password = loginPassword.getText().toString();
                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();
                realmResults = realm.where(Person.class)
                        .equalTo("email", email).equalTo("password", password).findAll();

                        if (realmResults.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Password or Email Does Not Exist", Toast.LENGTH_SHORT).show();


                        } else {
                            Intent myIntent = new Intent(getApplicationContext(), Home_User_ScreenActivity.class);
                            myIntent.putExtra("loginEmail", email);
                            startActivityForResult(myIntent, 0);


                        }

                    }
                });


            }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent myIntent = new Intent(getApplicationContext(), Home_Screen_Activity.class);
            startActivityForResult(myIntent, 0);


        }
        return super.onOptionsItemSelected(item);
    }
}
