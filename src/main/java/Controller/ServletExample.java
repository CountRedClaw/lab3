package Controller;

import Entity.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/create_student", "/update"})
public class ServletExample extends HttpServlet {

    /*@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        *//*PrintWriter out = resp.getWriter();
        out.println("Hello Java!");*//*
    }*/

    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath=request.getServletPath();
        if ("/create_student".equals(userPath)){
            ArrayList<String> groups = new ArrayList<>();
            groups = Group.getGroups();
            request.setAttribute("groups", groups);
        }

        request.getRequestDispatcher("editGroup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        *//*request.getRequestDispatcher("/test.jsp").forward(request, response);*//*
    }*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        /*String userPath=request.getServletPath();
        if ("/create_student".equals(userPath)){
            ArrayList<String> groups = new ArrayList<>();
            groups = Group.getGroups();
            request.setAttribute("groups", groups);
        }

        request.getRequestDispatcher("editGroup.jsp").forward(request, response);
        *//*request.getRequestDispatcher("/test.jsp").forward(request, response);*/
    }
}
