package com.example.praveenkumarl.r2kid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RateCustomAdapter extends BaseAdapter {

    private ArrayList<RateListModel> ratelist;
    private Context c;

    public RateCustomAdapter(Context c,ArrayList<RateListModel> ratelist) {
        this.ratelist = ratelist;
        this.c = c;
    }
    @Override
    public int getCount() {
        return ratelist.size();
    }
    @Override
    public Object getItem(int i) {
        return ratelist.get(i);
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
            view= LayoutInflater.from(c).inflate(R.layout.rate_model,viewGroup,false);
        }

        TextView nameTxt= (TextView) view.findViewById(R.id.tvTitle);
        ImageView img= (ImageView) view.findViewById(R.id.list_image);
        RatingBar ratingBar= (RatingBar) view.findViewById(R.id.ratingBar);

        final RateListModel s= (RateListModel) this.getItem(pos);

        nameTxt.setText(s.getTitle());
        ratingBar.setRating(s.getRating());
        img.setImageResource(s.getImage());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, s.getTitle()+ " Rating : "+String.valueOf(s.getRating()), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}