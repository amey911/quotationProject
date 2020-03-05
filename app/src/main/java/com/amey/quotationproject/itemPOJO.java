package com.amey.quotationproject;

import android.app.SearchableInfo;

import java.io.Serializable;

public class itemPOJO implements Serializable {
    String title;
    String desc;
    String amount;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc =  desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) { this.amount = amount; }




}


