package sk.kosickaakademia.spivak.dormitory;

import sk.kosickaakademia.spivak.dormitory.database.Database;
import sk.kosickaakademia.spivak.dormitory.entity.Resident;
import sk.kosickaakademia.spivak.dormitory.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        Database database = new Database();
        Util util = new Util();

        database.changeRoom("Ilya", "Spivak", "405a");

        List<Resident> list = database.getResidentByRoom("405a");

        for(Resident c : list){
            System.out.println("   - "+c.getFname() + " " + c.getLname());
        }

    }
}
