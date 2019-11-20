package by.gsu.epamlab.servlets;

import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.impl.DaoException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/done")
public class FixedTaskServlet extends HttpServlet {
    private static final String DB_TYPE = "db";
    private static final String URL_MAIN = "/main.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        config.getServletContext().setAttribute("name", "name00");
        init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            TaskFactory.getTaskDao(DB_TYPE).fixedTasks(request);
            response.sendRedirect(URL_MAIN);
        }catch (DaoException e ){
            e.getMessage();
            response.sendRedirect(URL_MAIN);
        }
    }
}
