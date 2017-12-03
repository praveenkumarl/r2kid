package com.example.praveenkumarl.r2kid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class landing_page extends AppCompatActivity {
    // Array of strings...
    String[] optionArray = {"Add","Delete","View","Rate","Manage","View score","Dashboard","Settings"};
    ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        simpleList = (ListView)findViewById(R.id.option_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview,
                //R.id.textView,
                 optionArray);

        simpleList.setAdapter(arrayAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view).getText().toString();
                if(item.equals("View score")){
                    Intent i = new Intent(landing_page.this, score_page.class);
                    startActivity(i);
                    //setContentView(R.layout.activity_score);
                }
                else if(item.equals("Add")){
                    Intent i = new Intent(landing_page.this, add_page.class);
                    startActivity(i);
                    //setContentView(R.layout.activity_score);
                }
                else if(item.equals("Delete")){
                    Intent i = new Intent(landing_page.this, delete_page.class);
                    startActivity(i);
                }
                else if(item.equals("View")){
                    Intent i = new Intent(landing_page.this, view_page.class);
                    startActivity(i);
                }
                else if(item.equals("Manage")){
                    Intent i = new Intent(landing_page.this, manage_page.class);
                    startActivity(i);
                }
                else if(item.equals("Rate")){
                    Intent i = new Intent(landing_page.this, rate_page.class);
                    startActivity(i);
                }
                else if(item.equals("Settings")){
                    Intent i = new Intent(landing_page.this, settings_page.class);
                    startActivity(i);
                }
            }
        });

    }
}
