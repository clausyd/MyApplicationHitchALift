package models;

/**
 * Created by clausyd on 14/02/18.
 */


import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Person extends RealmObject implements Serializable {


    @PrimaryKey
    private String email;
    private String firstName;
    public String surname;
    public String dob;
    //private RealmList<Car> carDetails;



    public Person()
    {}

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getDOB() {
        return dob;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDOB(String dob) {
        this.dob = dob;
    }



}

