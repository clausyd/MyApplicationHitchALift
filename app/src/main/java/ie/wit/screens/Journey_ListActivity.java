package ie.wit.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import journeypackage.Journey;
import journeypackage.JourneyManager;

public class Journey_ListActivity extends AppCompatActivity {

    Journey j;


    ListView listView;
    JourneyManager journeyManager = new JourneyManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.journeyList);

        Intent intent = getIntent();
        j = (Journey) intent.getSerializableExtra("Journey");
        String from = j.startCounty;
        String to = j.finishCounty;
        int date = j.date;
        ArrayList journeys = (ArrayList) journeyManager.getJourneyList();


        JourneyAdapter adapter = new JourneyAdapter(this, journeys);
        listView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public class JourneyAdapter extends ArrayAdapter<Journey> {
        private Context context;
        public ArrayList<Journey> journeys;

        public JourneyAdapter(Context context, ArrayList<Journey> journeys) {
            super(context, R.layout.journey_row, journeys);
            this.context = context;
            this.journeys = journeys;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.journey_row, parent, false);
            Journey j = journeys.get(position);
            TextView journeyFromView = (TextView) view.findViewById(R.id.row_journeyFrom);
            TextView journeyToView = (TextView) view.findViewById(R.id.row_journeyTo);
            TextView journeyDateView = (TextView) view.findViewById(R.id.row_journeyDate);

            journeyFromView.setText("$" + j.startCounty);
            journeyToView.setText(j.finishCounty);
            journeyDateView.setText(j.date);

            return view;
        }

        @Override
        public int getCount() {
            return journeys.size();
        }
    }

}


