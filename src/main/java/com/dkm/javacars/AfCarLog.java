package com.dkm.javacars;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AfCarLog implements Serializable
{
    private final String message;
    private final String formattedDate;

    public AfCarLog(String message)
    {
        this.message = message;
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        formattedDate = dateFormat.format(date);
    }

    @Override
    public String toString()
    {
        return "AfCarLog{" +
                "message='" + message + '\'' +
                ", formattedDate='" + formattedDate + '\'' +
                '}';
    }
}
