package by.gsu.epamlab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.ifaces.IUserDao;
import by.gsu.epamlab.impl.DaoException;
import by.gsu.epamlab.utils.ServletHelper;

@WebServlet("/registrate")
public class RegistrateServlet extends ServletHelper {
    private static final String DB_TYPE = "db";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final long serialVersionUID = 1L;
    private static final String PASSWORD = "password";
    private static final String JSP_LOGIN = "login.jsp";
    private static final String ERROR_USER_EXIST = "this user exists";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            IUserDao userDao = UserFactory.getUserDao(DB_TYPE);
            User newUser = UserFactory.getUserFromFactory(request.getParameter(NAME), request.getParameter(EMAIL));
            userDao.setUser(newUser, request.getParameter(PASSWORD));
            response.sendRedirect(JSP_LOGIN);
        } catch (DaoException e) {
            forwardError(e.getMessage(),ERROR_USER_EXIST, request, response);
        }
    }
}