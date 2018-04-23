package models;

import java.util.UUID;

import io.realm.RealmObject;

/**
 * Created by clausyd on 14/03/18.
 */

public class Car extends RealmObject {

    String carId = UUID.randomUUID().toString();
    String email;
    String reg;
    String make;
    String model;

    public Car(){};
    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarId() {

        return carId;
    }

    public String getReg() {
        return reg;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
