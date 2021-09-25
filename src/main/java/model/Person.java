package model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Person {
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;
    private Timestamp created;
    private Timestamp edited;
    private String url;
}