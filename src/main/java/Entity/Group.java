package Entity;

import java.util.ArrayList;

public class Group {

    private long id;
    private String name;

    private ArrayList<Student> studentList = new ArrayList<>();

    public Group() {
    }

    public Group(long id, String name, ArrayList<Student> studentList) {
        this.id = id;
        this.name = name;
        this.studentList = studentList;
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

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
}
