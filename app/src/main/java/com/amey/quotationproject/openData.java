package com.amey.quotationproject;

import androidx.recyclerview.widget.RecyclerView;

public class openData {

    String docName;
    String docLoc;
    String docRoom;
    String docContact;
    String docEmail;
    String docSubject;


    RecyclerView dataOpen;

    public openData(String docName, String docLoc, String docRoom, String docContact, String docEmail, String docSubject ){
        this.setDocName(docName);
        this.setDocLoc(docLoc);
        this.setDocRoom(docRoom);
        this.setDocContact(docContact);
        this.setDocEmail(docEmail);
        this.setDocSubject(docSubject);
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

    public String getDocContact() {
        return docContact;
    }

    public void setDocContact(String docContact) {
        this.docContact = docContact;
    }

    public String getDocEmail() {
        return docEmail;
    }

    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }

    public String getDocSubject() {
        return docSubject;
    }

    public void setDocSubject(String docSubject) {
        this.docSubject = docSubject;
    }
}
