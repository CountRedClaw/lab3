package Controller;

import Util.Connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index"})
public class MainController extends HttpServlet {

    private Connect connect = new Connect();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("groups", connect.getAllGroups());
            System.out.println(connect.getAllGroups().get(0).getName());
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
