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

        Resident resident = new Resident("Ilya","Spivak",util.getDateMySQL("1980-12-15"),"Ukraine","Poltava","410b");
        database.insertNewResident(resident);
        List<Resident> list = database.getAllResidents();
        System.out.println(list);
    }
}
