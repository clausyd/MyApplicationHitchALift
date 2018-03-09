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
    public String password;
    //private RealmList<Journey> journeys;



    public Person()
    {}

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }



}

