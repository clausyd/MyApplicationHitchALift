package ie.wit.screens;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import customAdapters.JourneyAdapter;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import models.Journey;

public class My_journey_list extends AppCompatActivity {
        SwipeMenuListView listView;
        String email;
    RealmResults<Journey> realmResultsJourney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_journey_list);
        listView = findViewById(R.id.myjourneys_listView);
        Intent intent = getIntent();
        email  =  intent.getStringExtra("emailJourney");
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        realmResultsJourney=  realm.where(Journey.class)
                .equalTo("email", email, Case.INSENSITIVE)
                .findAll();


        JourneyAdapter adapter = new JourneyAdapter(this, realmResultsJourney);
        listView.setAdapter(adapter);
//GitHub. (2018). baoyongzhang/SwipeMenuListView. [online] Available at: https://github.com/baoyongzhang/SwipeMenuListView [Accessed 9 Mar. 2018].
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Update");
                // set item title fontsize
                openItem.setWidth(90);
                // set a icon
                openItem.setIcon(R.drawable.ic_edit);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }

        };
        listView.setMenuCreator(creator);
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });



    }




    }

