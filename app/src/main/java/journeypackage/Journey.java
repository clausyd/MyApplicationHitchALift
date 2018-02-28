package journeypackage;


import java.io.Serializable;

/**
 * Created by clausyd on 14/02/18.
 */

public class Journey implements Serializable {
    public String email;
   public String startCounty;
   public String finishCounty;
   public int date;

    public String getEmail() {
        return email;
    }

    public Journey( String startCountyIn, String finishCountyIn, int dateIn){

        //email = emailIn;
        startCounty = startCountyIn;
        finishCounty = finishCountyIn;
        date = dateIn;
    }
}

