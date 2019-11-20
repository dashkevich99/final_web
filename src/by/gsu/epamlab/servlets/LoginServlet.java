package by.gsu.epamlab.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.ifaces.IUserDao;
import by.gsu.epamlab.impl.DataException;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.utils.ServletHelper;

@WebServlet("/start")
public class LoginServlet extends ServletHelper {
    private static final String JSP_INDEX = "index.jsp";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String DB_TYPE = "db";
    private static final String USER = "user";
    private static final String URL_MAIN = "/main.jsp";
    private static final String ERROR_DATA_INCORRECT = "wrong login or password";
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(JSP_INDEX, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);
        try {
            IUserDao userDao = UserFactory.getUserDao(DB_TYPE);
            User user = userDao.getUser(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(USER, user);
            forward(URL_MAIN, request, response);
        } catch (DataException | IllegalArgumentException e) {
            //output logger
            forwardError(e.getMessage(), ERROR_DATA_INCORRECT, request, response);
        }
    }
}
