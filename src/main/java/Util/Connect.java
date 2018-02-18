package Util;

import Entity.Group;
import Entity.Student;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {

    private static Connection conn;
    private static InitialContext ic;
    private static DataSource ds;

    public Connect() {
        getConnection();
    }

    public Connection getConnection() {
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/MySqlDS1");
            conn = ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select s.st_id, s.st_name, s.st_surname, s.st_group, g.gr_id, g.gr_name " +
                                                    "from groups g " +
                                                    "left join students s on (s.st_group = g.gr_id)"
            );
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
        System.out.println(groups.get(0).getId() + " " + groups.get(0).getName());
        return groups;
    }

    /*public void check() {
        try {

            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/MySqlDS1");
            //if (ds != null) {
            java.sql.Connection connection = ds.getConnection();
            //}`
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from students");

            while (rs.next()) {
                System.out.println(rs.getString("st_surname"));
            }

            //statement.executeUpdate("declare c int; begin select count(*) into c from ALL_TABLES where table_name='USERGROUP'; if c = 0 then execute immediate 'CREATE TABLE usergroup ( id NUMBER(10) NOT NULL, name VARCHAR2(100) NOT NULL, CONSTRAINT usergroup_pk PRIMARY KEY (id))'; end if; end;");
            //statement.executeUpdate("declare c int; begin select count(*) into c from ALL_TABLES where table_name='USERS'; if c = 0 then execute immediate 'CREATE TABLE users ( id NUMBER(10) NOT NULL, first_name VARCHAR2(100), last_name VARCHAR2(100), usergroup_id NUMBER(10), CONSTRAINT user_pk PRIMARY KEY (id), CONSTRAINT fk_usergroup FOREIGN KEY (usergroup_id) REFERENCES usergroup(id))'; end if; end;");
        }
        catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }*/
}
