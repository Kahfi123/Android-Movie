package com.example.movie.helper;

import android.util.Log;

public class HelperUtil {
    private static final String TAG = HelperUtil.class.getSimpleName();
    public static String convertToYear(String date){
        return date.split("-")[0];
    }
    public static String convertToNormalDate(String date){
        String[] dateArray = date.split("-");
        String dateString = "";
        try{
            dateString = dateArray[2]+" ";
            switch (dateArray[1]){
                case "01":
                    dateString += "January";
                    break;
                case "02":
                    dateString += "February";
                    break;
                case "03":
                    dateString += "March";
                    break;
                case "04":
                    dateString += "April";
                    break;
                case "05":
                    dateString += "May";
                    break;
                case "06":
                    dateString += "June";
                    break;
                case "07":
                    dateString += "July";
                    break;
                case "08":
                    dateString += "August";
                    break;
                case "09":
                    dateString += "September";
                    break;
                case "10":
                    dateString += "October";
                    break;
                case "11":
                    dateString += "November";
                    break;
                case "12":
                    dateString += "December";
                    break;
            }
            dateString += " "+dateArray[0];
        }catch (IndexOutOfBoundsException e){
            dateString = "-";
            Log.d(TAG, e.getMessage());
        }
        return dateString;
    }
}
