package journeypackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ie.wit.screens.R;
import journeypackage.Journey;

/**
 * Created by clausyd on 27/02/18.
 */

public class JourneyAdapter extends ArrayAdapter<Journey>
{
    private Context context;
    public ArrayList<Journey> journeys;

    public JourneyAdapter(Context context, ArrayList<Journey> journeys)
    {
        super(context, R.layout.journey_row, journeys);
        this.context   = context;
        this.journeys = journeys;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View     view       = inflater.inflate(R.layout.journey_row, parent, false);
        Journey j   = journeys.get(position);
        TextView journeyFromView = (TextView) view.findViewById(R.id.row_journeyFrom);
        TextView journeyToView = (TextView) view.findViewById(R.id.row_journeyTo);
        TextView journeyDateView = (TextView) view.findViewById(R.id.row_journeyDate);

        journeyFromView.setText("$" + j.startCounty);
        journeyToView.setText(j.finishCounty);
        journeyDateView.setText(j.date);

        return view;
    }

    @Override
    public int getCount()
    {
        return journeys.size();
    }
}
