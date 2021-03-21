package sk.kosickaakademia.spivak.dormitory.entity;

import java.util.Date;

public class User {
    private int id;
    private String fname ;
    private String lname ;
    private Date dob;
    private String country;
    private String city;
    private String room;

    public User(int id, String fname, String lname, Date dob, String country, String city, String room) {
        this(fname, lname, dob, country, city, room);
        this.id = id;
    }

    public User(String fname, String lname, Date dob, String country, String city, String room) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.country = country;
        this.city = city;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "[" + id + " " + fname + " " + lname + " " + dob + " " + country + " " + city + " " + room + ']';
    }
}
