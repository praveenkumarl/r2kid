package com.example.praveenkumarl.r2kid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class delete_page extends AppCompatActivity  {
    private final String KIDS_TABLE_NAME ="kids";
    Button btPrev,btNext,btDelete;
    public TextView tvName,tvDOB;
    public DBHelper db;
    ArrayList<kid_struct> records;
    kid_struct kidStructRec;
    int array_idx = 0;
    int num_records = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_page);

        btPrev=(Button)findViewById(R.id.bPrev);
        btNext=(Button)findViewById(R.id.bNext);
        btDelete=(Button)findViewById(R.id.bDelete);
        tvName = (TextView) findViewById(R.id.tvViewName);
        tvDOB = (TextView) findViewById(R.id.tvViewDOB);
        db = new DBHelper(this);
        kidStructRec = new kid_struct();

         records = db.getAllRecords();

        if(!records.isEmpty()) {
            num_records = records.size();
            tvName.setText(records.get(array_idx).name);
            tvDOB.setText(records.get(array_idx).dob);

            btPrev.setEnabled(false);

            if(num_records==1)
                btNext.setEnabled(false);
        }
        else {
            Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_SHORT).show();
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

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteRecord(KIDS_TABLE_NAME,tvDOB.getText().toString());
                Toast.makeText(getApplicationContext(),"Records deleted",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(array_idx >= 0) {
                    array_idx--;

                    tvName.setText(records.get(array_idx).name);
                    tvDOB.setText(records.get(array_idx).dob);

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
