package com.example.praveenkumarl.r2kid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class manage_page extends AppCompatActivity {
    public ManageListData mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_page);

        mData = new ManageListData(this);
        ListView lv= (ListView) findViewById(R.id.mlv);

        lv.setAdapter(new ManageCustomAdapter(this, mData.getManageListData()));

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
