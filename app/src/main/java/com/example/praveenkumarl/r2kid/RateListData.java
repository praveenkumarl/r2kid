package com.example.praveenkumarl.r2kid;

import java.util.ArrayList;

/**
 * Created by PraveenKumarL on 11/28/2017.
 */

public class RateListData {
    /*
    1.CREATE SPACESHIP OBJECTS AND ASSIGN THEM PROPERTIES
    2.RETURN LIST OF THESE SPACESHIP OBJECTS
     */

    public static ArrayList<RateListModel> getRateListData()
    {
        ArrayList<RateListModel> ratelist=new ArrayList<>();

        RateListModel s=new RateListModel();
        s.setTitle("Studies");
        s.setRating(0);
        s.setImage(R.mipmap.ic_edu);
        ratelist.add(s);

        s=new RateListModel();
        s.setTitle("Sports");
        s.setRating(0);
        s.setImage(R.mipmap.sports_icon);
        ratelist.add(s);

        s=new RateListModel();
        s.setTitle("Hobbies");
        s.setRating(0);
        s.setImage(R.mipmap.hobbies_icon);
        ratelist.add(s);

        s=new RateListModel();
        s.setTitle("Discipline");
        s.setRating(0);
        s.setImage(R.mipmap.discipline_icon);
        ratelist.add(s);

        s=new RateListModel();
        s.setTitle("Helping tendency");
        s.setRating(0);
        s.setImage(R.mipmap.help_icon);
        ratelist.add(s);

        s=new RateListModel();
        s.setTitle("Socializing");
        s.setRating(0);
        s.setImage(R.mipmap.social_icon);
        ratelist.add(s);

        return ratelist;
    }
}
