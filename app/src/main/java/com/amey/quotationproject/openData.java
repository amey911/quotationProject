package com.amey.quotationproject;

import androidx.recyclerview.widget.RecyclerView;

public class openData {

    String docName;
    String docLoc;
    String docRoom;


    RecyclerView dataOpen;

    public openData(String docName, String docLoc, String docRoom){
        this.setDocName(docName);
        this.setDocLoc(docLoc);
        this.setDocRoom(docRoom);
    }


    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocLoc() {
        return docLoc;
    }

    public void setDocLoc(String docLoc) {
        this.docLoc = docLoc;
    }

    public String getDocRoom() {
        return docRoom;
    }

    public void setDocRoom(String docRoom) {
        this.docRoom = docRoom;
    }


    }
