package sk.kosickaakademia.spivak.dormitory.contoller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.spivak.dormitory.database.Database;
import sk.kosickaakademia.spivak.dormitory.entity.Resident;
import sk.kosickaakademia.spivak.dormitory.log.Log;
import sk.kosickaakademia.spivak.dormitory.util.Util;

import java.util.List;

@RestController
public class Controller {

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
}
