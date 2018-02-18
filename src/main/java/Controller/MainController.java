package Controller;

import Util.Connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private Connect connect = new Connect();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("listUser")) {
            request.setAttribute("groups", connect.getAllGroups());
            System.out.println(connect.getAllGroups().get(0).getName());
        }
        try {
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
