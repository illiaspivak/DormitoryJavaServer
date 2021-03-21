package sk.kosickaakademia.spivak.dormitory;

import sk.kosickaakademia.spivak.dormitory.database.Database;
import sk.kosickaakademia.spivak.dormitory.entity.User;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        Database database=new Database();
        List<User> list = database.getAllResidents();
        System.out.println(list);
    }
}
