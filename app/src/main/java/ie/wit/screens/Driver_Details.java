package ie.wit.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import models.Car;
import models.Person;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

public class Driver_Details extends Home_Screen_Activity {
    TextView makeBox,modelBox,regBox,nameBox,emailBox;
    String make,model,reg,name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        makeBox = findViewById(R.id.make);
        modelBox = findViewById(R.id.model);
        regBox = findViewById(R.id.registration);
        nameBox = findViewById(R.id.name);
        emailBox = findViewById(R.id.driverEmail);

        Intent intent = getIntent();
        email = intent.getStringExtra("custEmail");
        //GoogleSignInAccount account = myApp.returnGoogleAccount();
        //Person p = myApp.dbManager.returnPerson(email);
        Car c = myApp.dbManager.returnCar(email);
        try {
            makeBox.setText(c.getMake());
            modelBox.setText(c.getModel());
            regBox.setText(c.getReg());
            emailBox.setText(email);
        }catch(NullPointerException e){
            Toast.makeText(getApplicationContext(), "No Car Details Available", Toast.LENGTH_LONG).show();

        }

        emailBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), EmailActivity.class);
                myIntent.putExtra("email", email);
                startActivityForResult(myIntent, 0);
            }
        });





    }

}
