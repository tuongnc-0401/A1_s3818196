package com.company;

import java.util.Date;

public class Student {
    private String sID;
    private String sName;
    private Date sDOB;

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Date getsDOB() {
        return sDOB;
    }

    public void setsDOB(Date sDOB) {
        this.sDOB = sDOB;
    }

    public Student(String sID, String sName, Date sDOB) {
        this.sID = sID;
        this.sName = sName;
        this.sDOB = sDOB;
    }

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "sID='" + sID + '\'' +
                ", sName='" + sName + '\'' +
                ", sDOB=" + sDOB +
                '}';
    }
}
