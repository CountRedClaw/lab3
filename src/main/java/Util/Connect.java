package Util;

import Entity.Group;
import Entity.Student;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {

    private static Connection conn;

    public Connect() {
        getConnection();
    }

    public Connection getConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/MySqlDS1");
            conn = ds.getConnection();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public List<Group> getAllGroups(String groupName) {
        List<Group> groups = new ArrayList<>();
        PreparedStatement statement;
        try {
            ResultSet resultSet;

            if (groupName == null || groupName.equals("")) {
                statement = conn.prepareStatement("SELECT g.gr_id, g.gr_name, s.st_id, s.st_name, s.st_surname, s.st_group " +
                        "FROM groups g " +
                        "LEFT JOIN students s ON (s.st_group = g.gr_id) " +
                        "ORDER BY g.gr_id"
                );
                resultSet = statement.executeQuery();
            } else {
                statement = conn.prepareStatement("SELECT g.gr_id, g.gr_name, s.st_id, s.st_name, s.st_surname, s.st_group " +
                        "FROM groups g " +
                        "LEFT JOIN students s ON (s.st_group = g.gr_id) " +
                        "WHERE g.gr_name = (?) " +
                        "ORDER BY g.gr_id"
                );
                statement.setString(1, groupName);
                resultSet = statement.executeQuery();
            }
            while (resultSet.next()) {                                                          // Берём записи из селекта по очереди
                if (resultSet.getInt("st_id") == 0) {                               // Если Группа пустая, то добавляем её как есть
                    Group group = new Group(resultSet.getInt("gr_id"),
                                            resultSet.getString("gr_name"),
                                            new ArrayList<Student>());
                    groups.add(group);
                } else if ((groups.size() != 0) && (resultSet.getInt("gr_id") == groups.get(groups.size() - 1).getId())) {  // Если такая группа уже есть, то добавляем в неё студентов
                    groups.get(groups.size() - 1).getStudentList().add(new Student(resultSet.getInt("st_id"),
                                                                                   resultSet.getString("st_name"),
                                                                                   resultSet.getString("st_surname"),
                                                                                   resultSet.getInt("st_group")));
                } else {                                                                        // Иначе создаём группу и добавляем в неё студента
                    ArrayList<Student> list = new ArrayList<>();
                    list.add(new Student(resultSet.getInt("st_id"),
                                         resultSet.getString("st_name"),
                                         resultSet.getString("st_surname"),
                                         resultSet.getInt("st_group")));

                    Group group = new Group(resultSet.getInt("gr_id"),
                                            resultSet.getString("gr_name"),
                                            list);
                    groups.add(group);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public Group getGroup(int groupId) {
        Group group = new Group();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("SELECT g.gr_id, g.gr_name, s.st_id, s.st_name, s.st_surname, s.st_group " +
                                                    "FROM groups g " +
                                                    "LEFT JOIN students s ON (s.st_group = g.gr_id) " +
                                                    "WHERE g.gr_id = (?)");
            statement.setString(1, String.valueOf(groupId));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {                                                          // Берём записи из селекта по очереди
                if (resultSet.isFirst()) {
                    ArrayList<Student> list = new ArrayList<>();
                    list.add(new Student(resultSet.getInt("st_id"),
                            resultSet.getString("st_name"),
                            resultSet.getString("st_surname"),
                            resultSet.getInt("st_group")));

                    group.setId(resultSet.getInt("gr_id"));
                    group.setName(resultSet.getString("gr_name"));
                    group.setStudentList(list);
                } else {
                    group.getStudentList().add(new Student(resultSet.getInt("st_id"),
                                                resultSet.getString("st_name"),
                                                resultSet.getString("st_surname"),
                                                resultSet.getInt("st_group")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public Student getStudent(int studentId) {
        Student student = new Student();
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select * " +
                                                    "from students " +
                                                    "where st_id = " + studentId);

            if (resultSet.next()) {
                student.setId(resultSet.getInt("st_id"));
                student.setName(resultSet.getString("st_name"));
                student.setSurname(resultSet.getString("st_surname"));
                student.setGroup(resultSet.getInt("st_group"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    public void createStudent(Student student) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("INSERT INTO students(st_name, st_surname, st_group) " +
                                                    "VALUES ((?), (?), (?))");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, String.valueOf(student.getGroup()));
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("UPDATE students " +
                                                    "SET st_name = (?), st_surname = (?), st_group = (?) " +
                                                    "WHERE st_id = (?)");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, String.valueOf(student.getGroup()));
            statement.setString(4, String.valueOf(student.getId()));
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("DELETE FROM students " +
                                                    "WHERE st_id = (?)");
            statement.setString(1, String.valueOf(studentId));
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createGroup(Group group) {
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO groups(gr_name) " +
                                                    "VALUES ((?))");
            statement.setString(1, group.getName());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateGroup(Group group) {
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("UPDATE groups " +
                                                    "SET gr_name = (?) " +
                                                    "WHERE gr_id = (?)");
            statement.setString(1, group.getName());
            statement.setString(2, String.valueOf(group.getId()));
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteGroup(int groupId) {
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("DELETE FROM groups " +
                                                    "WHERE groups.gr_id = (?);");
            statement.setString(1, String.valueOf(groupId));
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
