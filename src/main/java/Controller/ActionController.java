package Controller;

import Entity.Group;
import Entity.Student;
import Util.Connect;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/editStudent", "/deleteStudent", "/editGroup", "/deleteGroup", "/findGroup"})
public class ActionController extends HttpServlet {
    private Connect connect = new Connect();

    private String id = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String userPath = request.getServletPath();
        String forward="";
        id = request.getParameter("id");

        switch (userPath) {
            case "/editStudent":
                if (!id.equals("-1")) {
                    request.setAttribute("student", connect.getStudent(Integer.parseInt(id)));
                }
                request.setAttribute("groups", connect.getAllGroups(null));
                forward = "/editStudent.jsp";
                break;

            case "/deleteStudent":
                connect.deleteStudent(Integer.parseInt(id));
                forward = "/index";
                break;

            case "/editGroup":
                if (!id.equals("-1")) {
                    request.setAttribute("group", connect.getGroup(Integer.parseInt(id)));
                }
                forward = "/editGroup.jsp";
                break;

            case "/deleteGroup":
                connect.deleteGroup(Integer.parseInt(id));
                forward = "/index";
                break;

            case "/findGroup":
                request.setAttribute("groups", connect.getAllGroups(id));
                forward = "/index.jsp";
        }
        try {
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String userPath=request.getServletPath();

        switch (userPath) {
            case "/editGroup":
                Group group = connect.getGroup(Integer.parseInt(id));
                if (group.getName() == null) {
                    group.setName(request.getParameter("group"));
                    connect.createGroup(group);
                } else {
                    group.setName(request.getParameter("group"));
                    connect.updateGroup(group);
                }
                break;
            case "/editStudent":
                Student student = connect.getStudent(Integer.parseInt(id));

                if (student.getName() == null) {
                    student.setName(request.getParameter("name"));
                    student.setSurname(request.getParameter("surname"));
                    student.setGroup(Long.parseLong(request.getParameter("st_group")));
                    connect.createStudent(student);
                } else {
                    student.setName(request.getParameter("name"));
                    student.setSurname(request.getParameter("surname"));
                    student.setGroup(Long.parseLong(request.getParameter("st_group")));
                    connect.updateStudent(student);
                }
                break;
        }

        try {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
