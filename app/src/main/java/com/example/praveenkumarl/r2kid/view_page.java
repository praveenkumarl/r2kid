package com.example.praveenkumarl.r2kid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class view_page extends AppCompatActivity  {
    private final String MALE = "Male";
    private final String FEMALE = "Female";
    Button btPrev,btNext;
    RadioButton rbMale,rbFemale;
    public TextView tvName,tvDOB,tID;
    Boolean isFirstTimeGetFocused=true;
    private int year, month, day;
    public DBHelper db;
    Calendar calendar;
    ArrayList<kid_struct> records;
    kid_struct kidStructRec;
    int array_idx = 0;
    int num_records = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        btPrev=(Button)findViewById(R.id.bPrev);
        btNext=(Button)findViewById(R.id.bNext);
        tvName = (TextView) findViewById(R.id.tvViewName);
        tvDOB = (TextView) findViewById(R.id.tvViewDOB);
        tID = (TextView) findViewById(R.id.tvId);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        db = new DBHelper(this);
        kidStructRec = new kid_struct();

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
       // showDate(year, month+1, day);

        records = db.getAllRecords();

        if(!records.isEmpty()) {
            num_records = records.size();
            tvName.setText(records.get(array_idx).name);
            tvDOB.setText(records.get(array_idx).dob);
            tID.setText(Integer.toString(records.get(array_idx).id));

            if (records.get(array_idx).gender.equals(MALE))
                rbMale.setChecked(true);
            else
                rbFemale.setChecked(false);

            if(num_records==1)
                btNext.setEnabled(false);

            btPrev.setEnabled(false);
        }
        else{
            Toast.makeText(getApplicationContext(),"No records found",Toast.LENGTH_SHORT).show();
            finish();
        }


        //InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //mgr.hideSoftInputFromWindow(etDOB.getWindowToken(), 0);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (array_idx < num_records - 1) {
                    array_idx++;
                    tvName.setText(records.get(array_idx).name);
                    tvDOB.setText(records.get(array_idx).dob);
                    tID.setText(Integer.toString(records.get(array_idx).id));

                    if(records.get(array_idx).gender.equals(MALE))
                        rbMale.setChecked(true);
                    else
                        rbFemale.setChecked(true);

                    if (array_idx == num_records - 1) {
                        btNext.setEnabled(false);
                        btPrev.setEnabled(true);
                    }
                    else{
                        btPrev.setEnabled(true);
                    }
                }
                else
                    btNext.setEnabled(false);
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(array_idx >= 0) {
                    array_idx--;

                    tvName.setText(records.get(array_idx).name);
                    tvDOB.setText(records.get(array_idx).dob);
                    tID.setText(Integer.toString(records.get(array_idx).id));

                    if(records.get(array_idx).gender.equals(MALE))
                        rbMale.setChecked(true);
                    else
                        rbFemale.setChecked(true);

                    if(array_idx==0){
                        btPrev.setEnabled(false);
                        btNext.setEnabled(true);
                    }
                    else{
                        btNext.setEnabled(true);
                    }
                }
                else
                    btPrev.setEnabled(false);
            }
        });

    } //OnCreate

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
