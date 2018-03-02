package ie.wit.screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import models.Journey;

/**
 * Created by clausyd on 28/02/18.
 */
//Medium. (2018). Custom Array Adapters made Easy! – Mindorks – Medium. [online] Available at: https://medium.com/mindorks/custom-array-adapters-made-easy-b6c4930560dd [Accessed 28 Feb. 2018].
public class JourneyAdapter extends ArrayAdapter<Journey> {

    private Context context;
    public List<Journey> journeys = new ArrayList<>();

    public JourneyAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes RealmResults<Journey> list) {
        super(context, 0 , list);
        this.context = context;
        journeys = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.journey_row,parent,false);

        Journey currentJourney = journeys.get(position);

        TextView from = listItem.findViewById(R.id.row_journeyFrom);
        from.setText(currentJourney.getStartCounty());

        TextView to = (TextView) listItem.findViewById(R.id.row_journeyTo);
        to.setText(currentJourney.getFinishCounty());

//            TextView release = (TextView) listItem.findViewById(R.id.row_journeyDate);
//            release.setText(currentJourney.getDate());

        return listItem;
    }

    @Override
    public int getCount() {
        return journeys.size();
    }
}

