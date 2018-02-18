package Entity;

import Util.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Group {

    private long id;
    private String name;

    private ArrayList<Student> studentList = new ArrayList<>();

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

    /*private ArrayList<Student> getStudents(String str) {

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Connect.getConnection();

            stmt = conn.createStatement();
            System.out.println(str);
            rs = stmt.executeQuery(str);
            while (rs.next()) {
                Student book = new Student();
                book.setId(rs.getLong("st_id"));
                book.setName(rs.getString("st_name"));
                book.setSurname(rs.getString("st_surname"));
                book.setGroup(rs.getString("st_group"));
                studentList.add(book);
            }

        } catch (Exception ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(studentList);
        return studentList;
    }

    public ArrayList<Student> getAllStudents() {
        if (!studentList.isEmpty()) {
            return studentList;
        } else {
            return getStudents("select * from students order by st_name");
        }
    }

    public static ArrayList<String> getGroups() {
        ArrayList<String> groups = new ArrayList<String>();

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Connect.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from groups order by gr_name");
            while (rs.next()) {
                String gr = rs.getString("gr_name");
                groups.add(gr);
            }

        } catch (Exception ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;
    }*/
}
