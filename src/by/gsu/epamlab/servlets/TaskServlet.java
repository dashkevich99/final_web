package by.gsu.epamlab.servlets;

import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.enums.ChoiceTask;
import by.gsu.epamlab.ifaces.DatabaseTaskDao;
import by.gsu.epamlab.ifaces.ITaskDao;
import by.gsu.epamlab.utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends ServletHelper {
    private static final String SECTION = "section";
    private static final String USER = "user";
    private static final String TASKS = "tasks";
    private static final String URL_MAIN = "/main.jsp";
    private static final String JSP_MAIN = "main.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final User user = (User) request.getSession().getAttribute(USER);
        try{
            ITaskDao taskSource = new DatabaseTaskDao();
            List<Tasks> tasksList = taskSource.getTasks(user, ChoiceTask.getValueByParam(request));
            tasksList.stream().forEach(System.out::println);
            request.setAttribute(SECTION, ChoiceTask.getValueByParam(request).name());
            request.setAttribute(TASKS, tasksList);
            forward(URL_MAIN,request,response);
        } catch (ServletException | IOException e) {
            forwardError(e.getMessage(),JSP_MAIN, request, response);
        }
    }
}