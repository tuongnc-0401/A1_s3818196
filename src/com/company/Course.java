package com.company;

public class Course {
    private String cID;
    private String cName;
    private int numOfCredits;

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getNumOfCredits() {
        return numOfCredits;
    }

    public void setNumOfCredits(int numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    public Course(String cID, String cName, int numOfCredits) {
        this.cID = cID;
        this.cName = cName;
        this.numOfCredits = numOfCredits;
    }

    public Course() {

    }

    @Override
    public String toString() {
        return cID + " " + cName +" " + numOfCredits;
    }
}
