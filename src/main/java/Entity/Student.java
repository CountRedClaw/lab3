package Entity;

import java.io.Serializable;

public class Student implements Serializable {

    private long id;
    private String name;
    private String surname;
    private long group;

    public Student(long id, String name, String surname, long group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getGroup() {
        return group;
    }

    public void setGroup(long group) {
        this.group = group;
    }
}
