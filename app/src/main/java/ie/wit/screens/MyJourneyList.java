package ie.wit.screens;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import customAdapters.JourneyAdapter;
import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;
import models.Journey;

import static android.widget.AdapterView.*;

public class MyJourneyList extends AppCompatActivity {
    SwipeMenuListView listView;
    String email;
    Realm realm;
    RealmResults<Journey> realmResultsJourney;
    JourneyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_journey_list);
        listView = findViewById(R.id.myjourneys_listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        email  =  intent.getStringExtra("emailJourney");
        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();
        realmResultsJourney=  realm.where(Journey.class)
                .equalTo("email", email, Case.INSENSITIVE)
                .findAll();


        adapter= new JourneyAdapter(this, realmResultsJourney);
        listView.setAdapter(adapter);
//GitHub. (2018). baoyongzhang/SwipeMenuListView. [online] Available at: https://github.com/baoyongzhang/SwipeMenuListView [Accessed 9 Mar. 2018].
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                openItem.setWidth(90);
                openItem.setTitle("Update");
                openItem.setWidth(90);
                openItem.setIcon(R.drawable.ic_edit);
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());

                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(90);

                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }

        };
        listView.setMenuCreator(creator);
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                adapter= new JourneyAdapter(getApplicationContext(), realmResultsJourney);

                switch (index) {
                    case 0:
                        Intent myIntent = new Intent(getApplicationContext(), Update_JourneyActivity.class);
                        startActivityForResult(myIntent, 0);
                        break;
                    case 1:
                        realm.beginTransaction();
                        Journey j =realmResultsJourney.get(position);
                        RealmResults<Journey> r = realm.where(Journey.class)
                                .equalTo("email", j.getEmail())
                                .equalTo("startCounty", j.getStartCounty())
                                .equalTo("finishCounty", j.getFinishCounty())
                                .findAll();
                        r.deleteAllFromRealm();
                        realm.commitTransaction();
                        realm.close();
                        realmResultsJourney = realm.where(Journey.class).equalTo("email", email).findAllAsync();
                        if(listView.getAdapter().isEmpty()){

                            listView.setAdapter(adapter);
                        }else{
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            listView.invalidateViews();
                            listView.refreshDrawableState();
                        }

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
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
            Intent myIntent = new Intent(getApplicationContext(), Home_User_ScreenActivity.class);
            myIntent.putExtra("homeEmail",email);
            startActivityForResult(myIntent, 0);


        }
        return super.onOptionsItemSelected(item);
    }





}
