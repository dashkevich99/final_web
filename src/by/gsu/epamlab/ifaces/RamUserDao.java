package by.gsu.epamlab.ifaces;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.impl.DaoException;
import by.gsu.epamlab.impl.DataException;

public class RamUserDao implements IUserDao {

    private static int userId = 1;
    private static List<User> users = new CopyOnWriteArrayList<>();

    @Override
    public User getUser(String login, String password) throws DaoException {
        users.stream().forEach(System.out::println);
        if (checkInput(login, password)) {
            return users.stream().filter(e -> e.getLogin().equalsIgnoreCase(login))
                    .findFirst().get();
        } else {
            throw new DataException(login);
        }

    }

    @Override
    public boolean setUser(User user, String password) throws DaoException {
        if (checkInput(user.getLogin())) {
            throw new DaoException(user.getLogin());
        }
        users.add(new User(userId++, user.getLogin(), user.getEmail(), user.getPassword()));
        return true;
    }

    @Override
    public boolean checkInput(String login, String password) {
        return users.stream().filter(e -> e.getLogin().equalsIgnoreCase(login)
                && e.getPassword().equals(password))
                .findFirst().isPresent();
    }

    public boolean checkInput(String login) {
        return users.stream().filter(e -> e.getLogin().equalsIgnoreCase(login))
                .findFirst().isPresent();
    }

}
