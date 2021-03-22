package sk.kosickaakademia.spivak.dormitory.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sk.kosickaakademia.spivak.dormitory.entity.Resident;
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

    /**
     * Creating the Json about a resident
     * @param resident
     * @return Json
     */
    public String getJson (Resident resident){
        log.info("Creating the json about a resident");
        if (resident==null){
            return "{}";
        }
        JSONObject object = new JSONObject();
        object.put("datetime",getCurrentTime());
        object.put("size",1);
        JSONArray jsonArray = new JSONArray();
        JSONObject userJson = new JSONObject();
        userJson.put("fname",resident.getFname());
        userJson.put("lname",resident.getLname());
        userJson.put("dob",resident.getDob());
        userJson.put("country ",resident.getCountry());
        userJson.put("city ",resident.getCity());
        userJson.put("room ",resident.getRoom());
        object.put("residents",jsonArray);
        return object.toJSONString();
    }
}
