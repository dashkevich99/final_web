package by.gsu.epamlab.servlets;

import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.impl.DaoException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAll")
public class DeleteAllTasksFromRecycle extends HttpServlet {
    private static final String DB_TYPE = "db";
    private static final String URL_MAIN = "/main.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            TaskFactory.getTaskDao(DB_TYPE).deleteAllTasksFromRecycle();
            response.sendRedirect(URL_MAIN);
        }catch (DaoException e ){
            e.getMessage();
            response.sendRedirect(URL_MAIN);
        }
    }
}
