package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.ifaces.DatabaseTaskDao;
import by.gsu.epamlab.ifaces.ITaskDao;
import by.gsu.epamlab.impl.DaoException;


import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDate;

public class TaskFactory {
    private static final String DB_TYPE = "db";
    private static final String ERROR_CONNECTION = "connection error";
    private static final String USER = "user";
    private static final String CREATE_DATE = "CreateDate";
    private static final String TITLE = "title";
    private static final String BODY = "body";

    public static ITaskDao getTaskDao(String type) throws DaoException {
        if (type.equals(DB_TYPE)) {
            return new DatabaseTaskDao();
        } else {
            throw new DaoException(ERROR_CONNECTION);
        }
    }

    public static Tasks getTaskFromFactory(String id, String url) {
        Tasks task = new Tasks();
        task.setIdTask(Integer.valueOf(id));
        task.setUrl(url);
        return task;
    }

    public static Tasks getNewTasks(HttpServletRequest request) throws ParseException {
        Tasks task = new Tasks();
        task.setUser((User) request.getSession(false).getAttribute(USER));
        task.setDate(LocalDate.parse(request.getParameter(CREATE_DATE)));
        task.setTitle(request.getParameter(TITLE));
        task.setBody(request.getParameter(BODY));
        return task;
    }
}
