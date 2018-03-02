package models;


import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by clausyd on 14/02/18.
 */

public class Journey extends RealmObject implements Serializable {

    private String email;
    public String startCounty;
    public String finishCounty;
    @PrimaryKey
    public String date;

    public Journey(){

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStartCounty(String startCounty) {
        this.startCounty = startCounty;
    }

    public void setFinishCounty(String finishCounty) {
        this.finishCounty = finishCounty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartCounty() {

        return startCounty;
    }

    public String getFinishCounty() {
        return finishCounty;
    }

    public String getDate() {
        return date;
    }



    public String getEmail() {
        return email;
    }


}

