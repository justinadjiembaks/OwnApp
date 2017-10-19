package com.example.justi.ownapp;

import java.io.Serializable;

/**
 * Created by justi on 18-10-2017.
 * Creates an Object where you can store a key, the artist
 * and songtitle.
 */

public class FavouriteSongs implements Serializable {
    private String name ,title;
    public int number;

    //Default constructor for FB
    public FavouriteSongs(){}

    public FavouriteSongs(int Anumber,String Artist, String Song) {

        this.number = Anumber;
        this.name = Artist;
        this.title = Song;
    }

    //Returns number
    public int getNumber(){

        return number;
    }
    //Returns artist name
    public String getTheName(){

        return name;
    }

    //Returns song title
    public String getTheTitle(){

        return title;
    }
}
