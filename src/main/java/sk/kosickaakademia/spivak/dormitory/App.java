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

        database.deleteResident("Vlad", "Skobley");

        List<Resident> list = database.getAllResidents();
        System.out.println(list);
    }
}
