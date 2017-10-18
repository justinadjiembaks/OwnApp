package com.example.justi.ownapp;

import java.io.Serializable;

/**
 * Created by justi on 15-10-2017.
 */

public class FoundLyrics implements Serializable {
    private String full_title;

    public FoundLyrics(String TheFullTitle) {
        this.full_title = TheFullTitle;
    }

    // Returns the Track name
    public String getFull_title() {

        return full_title;
    }

    //Returns lyrics
    public String getlyrics(){

        return getlyrics();
    }
}
