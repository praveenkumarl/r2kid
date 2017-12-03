package com.example.praveenkumarl.r2kid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class settings_page extends AppCompatActivity {

    Button bClearDB;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        bClearDB = (Button) findViewById(R.id.btClearDB);
        db = new DBHelper(this);

        bClearDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearDB();
                db.close();

                Toast.makeText(getApplicationContext(),"DB cleared",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
