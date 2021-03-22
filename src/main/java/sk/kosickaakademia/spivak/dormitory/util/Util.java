package sk.kosickaakademia.spivak.dormitory.util;

import sk.kosickaakademia.spivak.dormitory.log.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    Log log = new Log();

    /**
     * Converting a date to write to a database
     * @param dateString
     * @return sqlDate
     */
    public java.sql.Date getDateMySQL (String dateString){
        if(dateString == null || dateString.isEmpty()){
            log.error("Missing input data");
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        log.info("Converting a date to write to a database");
        try {
            Date convertedDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate;
            sqlDate = new java.sql.Date(convertedDate.getTime());
            log.print("Date converted");
            return  sqlDate;
        } catch (ParseException e) {
            log.error("Date not converted");
            log.error(e.toString());
        }
        return null;
    }

    /**
     * Current date and time
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getCurrentTime(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatForDateNow.format(dateNow);
    }
}
