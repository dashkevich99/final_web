package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.impl.DaoException;

public interface IUserDao {
    User getUser(String login, String password);

    boolean setUser(User user, String password) throws DaoException;

    boolean checkInput(String login, String password);
}
