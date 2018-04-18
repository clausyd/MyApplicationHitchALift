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
import android.widget.Toast;

import models.Car;

public class AddCarActivity extends MainActivity {

    EditText carReg;
    EditText carMake;
    EditText carModel;

    String reg;
    String make;
    String model;

    Button addCar;

    Car car = new Car();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        carReg = findViewById(R.id.carReg);
        carMake = findViewById(R.id.carMake);
        carModel = findViewById(R.id.carModel);
        addCar = findViewById(R.id.addCar);
        email = myApp.getEmail();




        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reg = carReg.getText().toString();
                make = carMake.getText().toString();
                model = carModel.getText().toString();

                if(carReg.getText().toString().trim().length() >0 && carMake.getText().toString().trim().length() >0
                        && carModel.getText().toString().trim().length() >0){

                    car.setReg(reg);
                    car.setMake(make);
                    car.setModel(model);
                    myApp.dbManager.add(car);
                    Toast.makeText(getApplicationContext(), "Car Added", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), "Enter Detail", Toast.LENGTH_LONG).show();

                }

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.journey_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.journey_list) {
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);


        }else if(id == R.id.logOutJourneyList){
            Intent myIntent = new Intent(getApplicationContext(),Home_Screen_Activity.class);
            startActivityForResult(myIntent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

}
