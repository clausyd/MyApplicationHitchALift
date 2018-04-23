package datePicker;

/**
 * Created by clausyd on 18/04/18.
 */

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Dialog;
import java.util.Calendar;

import ie.wit.screens.R;

//Android, H. (2018). How to use DatePickerDialog in Android. [online] Android--examples.blogspot.ie. Available at: https://android--examples.blogspot.ie/2015/05/how-to-use-datepickerdialog-in-android.html [Accessed 18 Apr. 2018].

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Create a new DatePickerDialog instance and return it
        /*
            DatePickerDialog Public Constructors - Here we uses first one
            public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth)
            public DatePickerDialog (Context context, int theme, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth)
         */
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Do something with the date chosen by the user
        EditText dateSelectorHomePage =  getActivity().findViewById(R.id.dateSelectorHomePage);
        EditText dateSelector =  getActivity().findViewById(R.id.dateSelector);
        EditText dob =  getActivity().findViewById(R.id.dob);

        if(dateSelectorHomePage != null){
            String stringOfDate = day + "/" + month + "/" + year;
            dateSelectorHomePage.setText(dateSelectorHomePage.getText() +  stringOfDate);

        }else if (dateSelector != null){
            String stringOfDate = day + "/" + month + "/" + year;
            dateSelector.setText(dateSelector.getText() +  stringOfDate);

        }else{
            String stringOfDate = day + "/" + month + "/" + year;
            dob.setText(dob.getText() +  stringOfDate);
        }
    }
}
