package com.example.praveenkumarl.r2kid;

/**
 * Created by PraveenKumarL on 11/25/2017.
 */

public class kid_struct {
    int id;
    String name;
    String dob;
    String gender;
    byte[] photo;

    kid_struct(String name, String dob, String gender, byte[] photo, int id){
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.photo = photo;
    }

    kid_struct(){
        this.id = 0;
        this.name = "";
        this.dob = "";
        this.gender = "";
        this.photo = "".getBytes();
    }

}