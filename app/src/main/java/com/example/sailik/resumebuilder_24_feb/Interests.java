package com.example.sailik.resumebuilder_24_feb;

/**
 * Created by saili.k on 03-03-2017.
 */

public class Interests {
    private String interestOne;
    private String interestTwo;
    private String interestThree;

    public Interests(){

    }
    public Interests(String text1,String text2,String text3){
        this.interestOne=text1;
        this.interestTwo = text2;
        this.interestThree = text3;
    }

    public void setInterestOne(String text){
        this.interestOne=text;

    }
    public void setInterestTwo(String text){
        this.interestTwo=text;
    }
    public void setInterestThree(String text){
        this.interestThree=text;
    }

    public String getInterestOne(){
        return interestOne;
    }

    public String getInterestTwo() {
        return interestTwo;
    }

    public String getInterestThree() {
        return interestThree;
    }
}
