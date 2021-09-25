package model;

import lombok.Data;

import java.util.List;
@Data
public class Persons {
    private int count;
    private String next;
    private String previous;
    private List<Person> results;
}
