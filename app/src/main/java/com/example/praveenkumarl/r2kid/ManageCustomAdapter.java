package com.example.praveenkumarl.r2kid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ManageCustomAdapter extends BaseAdapter {

    public ArrayList<ManageListModel> manageList;
    public Context c;
    public DBHelper db;
    private static int REQUEST_CODE = 1;

    public ManageCustomAdapter(Context c, ArrayList<ManageListModel> manageList) {
        this.manageList = manageList;
        this.c = c;
    }
    @Override
    public int getCount() {
        return manageList.size();
    }
    @Override
    public Object getItem(int i) {
        return manageList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    /*
    INFLATE XML LAYOUT TO VIEW
     */
    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.manage_model,viewGroup,false);
        }

        TextView nameTxt= (TextView) view.findViewById(R.id.tvName);
        TextView dobTxt= (TextView) view.findViewById(R.id.tvDOB);
        TextView genderTxt= (TextView) view.findViewById(R.id.tvGenderLabel);
        ImageView img= (ImageView) view.findViewById(R.id.imgPhoto);
        Button updButton= (Button) view.findViewById(R.id.btUpdate);
        Button delButton= (Button) view.findViewById(R.id.btDelete);

        final ManageListModel s= (ManageListModel) this.getItem(pos);

        nameTxt.setText(s.getName());
        dobTxt.setText(s.getDob());
        genderTxt.setText(s.getGender());
        //img.setImageResource(s.getImage());
        db = new DBHelper(c);

        if(s.getPhoto() != null) {
            img.setImageBitmap(BitmapFactory.decodeByteArray(s.getPhoto(), 0, s.getPhoto().length));
        }
        else{
            img.setImageResource(R.drawable.no_photo);
        }

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteRecord(s.getId());
                Toast.makeText(c, "Record deleted", Toast.LENGTH_SHORT).show();
                ((manage_page)c).recreate();
            }
        });

        updButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c.getApplicationContext(), update_page.class);
                intent.putExtra("id",s.getId());
                intent.putExtra("name", s.getName());
                intent.putExtra("dob", s.getDob());
                intent.putExtra("gender", s.getGender());
                intent.putExtra("photo",s.getPhoto() );
                c.startActivity(intent);

                ((manage_page)c).recreate();
            }
        });

        return view;
    }
}