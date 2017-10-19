package com.example.justi.ownapp;

import java.io.Serializable;

/**
 * Created by justi on 19-10-2017.
 * Makes is able to put a number in an Object.
 */

public class Counter implements Serializable{

    public int counter;

    //Default constructor for FB
    public Counter(){}

    public Counter(int Acounter)
    {
        this.counter = Acounter;
    }

    //Returns number
    public int getNumber(){

        return counter;
    }
}
