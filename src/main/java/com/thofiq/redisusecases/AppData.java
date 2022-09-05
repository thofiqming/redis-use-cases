package com.thofiq.redisusecases;

import java.io.Serializable;

public class AppData implements Serializable {

    public AppData(){

    }

    private String details;

    public String getDetails(){
        return this.details;
    }

    public void setDetails(String details){
        this.details = details;
    }

    @Override
    public String toString() {
        return "AppData {" +
                "details='" + details + '\'' +
                '}';
    }
}
