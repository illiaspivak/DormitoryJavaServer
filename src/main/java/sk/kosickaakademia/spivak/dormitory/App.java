package sk.kosickaakademia.spivak.dormitory;

import sk.kosickaakademia.spivak.dormitory.database.Database;

public class App
{
    public static void main( String[] args )
    {
        Database database=new Database();
        database.getConnection();
    }
}
