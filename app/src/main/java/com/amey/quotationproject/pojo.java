package com.amey.quotationproject;

import java.io.Serializable;

class pojo implements Serializable {

    String projName;
    String projLoc;
    String projRooms;
    String projSubject;


    public String getprojName() {
        return projName;
    }

    public void setprojName(String projName) {
        this.projName = projName;
    }

    public String getprojLoc() {
        return projLoc;
    }

    public void setprojLoc(String projLoc) {
        this.projName =  projLoc;
    }

    public String getProjSubject() {
        return projSubject;
    }

    public void setprojDesc(String  projSubject) {
        this.projSubject = projSubject;

    }

    public String getprojRooms() {
        return projRooms;
    }

    public void setprojRooms(String projRooms) {
        this.projRooms = projRooms;

    }



}
