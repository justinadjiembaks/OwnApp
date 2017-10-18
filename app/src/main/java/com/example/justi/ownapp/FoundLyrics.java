package com.example.justi.ownapp;

import java.io.Serializable;

/**
 * Created by justi on 15-10-2017.
 */

public class FoundLyrics implements Serializable {
    private String full_title, name ,title;

    public FoundLyrics(String TheFullTitle,String TheName, String TheTitle) {
        this.full_title = TheFullTitle;
        this.name = TheName;
        this.title = TheTitle;
    }

    // Returns the Track name
    public String getFull_title() {

        return full_title;
    }
    //Returns lyrics
    public String getTheName(){

        return name;
    }

    //Returns lyrics
    public String getTheTitle(){

        return title;
    }
}
