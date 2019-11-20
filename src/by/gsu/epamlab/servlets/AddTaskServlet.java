package by.gsu.epamlab.servlets;

import by.gsu.epamlab.enums.ChoiceDate;

import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.impl.DaoException;
import by.gsu.epamlab.utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;


@WebServlet("/addTask")
public class AddTaskServlet extends ServletHelper {
    private static final String SECTION = "section";
    private static final String DATE_TASK = "dateTask";
    private static final String URL_ADD_TASK = "/addTask.jsp";
    private static final String JSP_MAIN = "main.jsp";
    private static final String DB_TYPE = "db";
    private static final String URL_MAIN = "/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String str = req.getParameter(SECTION);
            req.setAttribute(SECTION, str);
            req.setAttribute(DATE_TASK, ChoiceDate.getValueByParam(req).getDate());
            forward(URL_ADD_TASK, req, resp);
        }catch (NullPointerException | IllegalArgumentException e){
            e.printStackTrace();
            forwardError(e.getMessage(), JSP_MAIN, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            TaskFactory.getTaskDao(DB_TYPE).addTask(TaskFactory.getNewTasks(req));
            resp.sendRedirect(URL_MAIN);

        }catch (DaoException | ParseException e){
            e.printStackTrace();
            forwardError(e.getMessage(), JSP_MAIN, req, resp);
        }
    }
}