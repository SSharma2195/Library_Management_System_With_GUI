package oop;

import java.io.Serializable;

public class Person implements Serializable {
    private int person_id;
    private String first_name;
    private String last_name;
    private int libraryCard;

    public Person(int person_id, String first_name, String last_name, int libraryCard) {
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.libraryCard = libraryCard;
    }

    public int getPerson_id() {
        return person_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getLibraryCard() {
        return libraryCard;
    }
}

