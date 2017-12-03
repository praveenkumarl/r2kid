package com.example.praveenkumarl.r2kid;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by PraveenKumarL on 11/28/2017.
 */

public class ManageListData {
    /*
    1.CREATE SPACESHIP OBJECTS AND ASSIGN THEM PROPERTIES
    2.RETURN LIST OF THESE SPACESHIP OBJECTS
     */
    public static Context mContext;
    public ManageListData(Context context){
        this.mContext = context;
    }

    public static ArrayList<ManageListModel> getManageListData()
    {
        ArrayList<ManageListModel> manageList=new ArrayList<>();
        ArrayList<kid_struct> records;
        DBHelper db;
        int num_records = 0;

        db = new DBHelper(mContext);
        ManageListModel s;
        records = db.getAllRecords();

        if(!records.isEmpty()) {
            num_records = records.size();

            for (int i=0;i <num_records;i++){
                s=new ManageListModel();
                s.setId(records.get(i).id);
                s.setName(records.get(i).name);
                s.setDob(records.get(i).dob);
                s.setPhoto(records.get(i).photo);
                s.setGender(records.get(i).gender);
                manageList.add(s);
            }
        }
        else{
            Toast.makeText(mContext,"No records found",Toast.LENGTH_SHORT).show();
        }

        return manageList;
    }
}
