package com.example.praveenkumarl.r2kid;

/**
 * Created by PraveenKumarL on 11/27/2017.
 */

public class ManageListModel {
    private int id=0;
    private  String name="";
    private  String dob="";
    private String gender="";
    private byte[] photo="".getBytes();

    /*********** Set Methods ******************/
    public void setId(int inId)
    {
        this.id = inId;
    }

    public void setName(String inName)
    {
        this.name = inName;
    }

    public void setDob(String inDob)
    {
        this.dob = inDob;
    }

    public void setGender(String inGender)
    {
        this.gender = inGender;
    }

    public void setPhoto(byte[] inPhoto)
    {
        this.photo = inPhoto;
    }

    /*********** Get Methods ****************/
    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDob()
    {
        return this.dob;
    }

    public String getGender()
    {
        return this.gender;
    }

    public byte[] getPhoto()
    {
        return this.photo;
    }
}
