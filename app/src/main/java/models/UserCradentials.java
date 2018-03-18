package models;

import io.realm.RealmObject;

/**
 * Created by clausyd on 18/03/18.
 */

public class UserCradentials extends RealmObject{
    String email;
    String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserCradentials(){}
}
