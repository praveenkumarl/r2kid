package com.example.praveenkumarl.r2kid;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.praveenkumarl.r2kid.R;

public class rate_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_page);

        ListView lv= (ListView) findViewById(R.id.lv);

        lv.setAdapter(new RateCustomAdapter(this, RateListData.getRateListData()));

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
