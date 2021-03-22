package sk.kosickaakademia.spivak.dormitory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sk.kosickaakademia.spivak.dormitory.database.Database;
import sk.kosickaakademia.spivak.dormitory.entity.Resident;
import sk.kosickaakademia.spivak.dormitory.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class App
{
    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }
}
