package customAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import ie.wit.screens.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import models.Journey;


/**
 * Created by clausyd on 28/02/18.
 */
//Medium. (2018). Custom Array Adapters made Easy! – Mindorks – Medium. [online] Available at: https://medium.com/mindorks/custom-array-adapters-made-easy-b6c4930560dd [Accessed 28 Feb. 2018].
public class JourneyAdapter extends RealmBaseAdapter {

    private Context context;
    private OrderedRealmCollection <Journey> realmCollection;

    public JourneyAdapter(@NonNull Context context, OrderedRealmCollection<Journey> realmCollection) {
        super(realmCollection);
        this.realmCollection = realmCollection;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.journey_row,parent,false);

        Journey currentJourney = realmCollection.get(position);

        TextView from = listItem.findViewById(R.id.row_journeyFrom);
        from.setText(currentJourney.getStartCounty());

        TextView to = (TextView) listItem.findViewById(R.id.row_journeyTo);
        to.setText(currentJourney.getFinishCounty());

           TextView date = (TextView) listItem.findViewById(R.id.row_journeyDate);
            date.setText(currentJourney.getDate());

       TextView email = (TextView) listItem.findViewById(R.id.row_email);
        email.setText(currentJourney.getEmail());

        return listItem;
    }

    @Override
    public int getCount() {
        return realmCollection.size();
    }
}

