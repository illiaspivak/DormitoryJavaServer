package sk.kosickaakademia.spivak.dormitory.contoller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.spivak.dormitory.database.Database;
import sk.kosickaakademia.spivak.dormitory.entity.Resident;
import sk.kosickaakademia.spivak.dormitory.log.Log;
import sk.kosickaakademia.spivak.dormitory.util.Util;

import java.util.Date;
import java.util.List;

@RestController
public class Controller {
    Log log = new Log();

    /**
     * Method GET: Show all residents
     * @return
     */
    @GetMapping("/residents")
    public ResponseEntity<String> getAllResidents(){
        List<Resident> list = new Database().getAllResidents();
        String json = new Util().getJson(list);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(json);
    }

    @PostMapping("/resident/new")
    public ResponseEntity<String> insertNewResident(@RequestBody String data){
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(data);
            String fname = ((String)object.get("fname"));
            String lname = ((String)object.get("lname"));
            log.info("Converting a date from String to Date");
            Date dob = new Util().getDateMySQL(((String)object.get("dob")));
            System.out.println(dob);
            String country = (String) object.get("country");
            String city = (String) object.get("city");
            String room = (String) object.get("room");


            log.info("Checking if there are any input data");
            if(fname==null || lname==null || city==null || country==null || room==null || dob==null){
                log.error("Not enough data");
                JSONObject objectError = new JSONObject();
                objectError.put("error", "Not enough data");
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(objectError.toJSONString());
            }

            log.info("Adding a new resident to the database");
            Resident resident = new Resident(fname, lname, dob, country, city, room);

            new Database().insertNewResident(resident);

        } catch (Exception e) {
            JSONObject obj = new JSONObject();
            log.error("Cannot process input data ");
            obj.put("error","Cannot process input data ");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return null;
    }
}