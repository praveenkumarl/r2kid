package com.example.praveenkumarl.r2kid;

/**
 * Created by PraveenKumarL on 11/27/2017.
 */

public class RateListModel {
    private  int image=0;
    private  String title="";
    private  int rating=0;

    /*********** Set Methods ******************/

    public void setTitle(String inTitle)
    {
        this.title = inTitle;
    }

    public void setImage(int inImage)
    {
        this.image = inImage;
    }

    public void setRating(int inRating)
    {
        this.rating = inRating;
    }

    /*********** Get Methods ****************/

    public String getTitle()
    {
        return this.title;
    }

    public int getImage()
    {
        return this.image;
    }

    public int getRating()
    {
        return this.rating;
    }
}
