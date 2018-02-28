package personpackage;

/**
 * Created by clausyd on 14/02/18.
 */


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;



public class Person implements Serializable {
    public String firstName;
    public String surname;
    public String password;
    public String email;

    public String getEmail() {
        return email;
    }

    public Person(String email, String firstname, String surname, String password)
    {
        this.firstName = firstname;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

}

