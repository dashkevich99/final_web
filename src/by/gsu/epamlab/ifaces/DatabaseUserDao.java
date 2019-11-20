package by.gsu.epamlab.ifaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connections.DatabaseConnection;
import by.gsu.epamlab.impl.DaoException;
import by.gsu.epamlab.impl.DataException;

public class DatabaseUserDao implements IUserDao {

    private PreparedStatement ps = null;
    private Connection db = null;
    private ResultSet rs = null;
    private static final String SELECT_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String ERROR_USER_NOT_FOUND = "not found this user";
    private static final String INSERT_LOGIN = "INSERT INTO users(login, email, password) values(?, ?, ?)";
    private static final String ERROR_USER_EXIST = "this user exists";
    private static final String SELECT_LOGIN_PASSWORD = "SELECT login, password FROM users WHERE login = ? AND password = ?";

    @Override
    public User getUser(String login, String password) {
        if (checkInput(login, password)) {
            String query = SELECT_LOGIN;

            try {
                db = new DatabaseConnection().createConnection();
                ps = db.prepareStatement(query);
                ps.setString(1, login);
                rs = ps.executeQuery();

                if (rs.next()) {
                    return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                } else {
                    throw new NullPointerException(ERROR_USER_NOT_FOUND);
                }


            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                DatabaseConnection.closeResultSet(rs);
                DatabaseConnection.closeStatements(ps);

            }
        } else {
            throw new DataException(login);
        }
    }

    @Override
    public boolean setUser(User user, String password) throws DaoException {
        String queryInsert = INSERT_LOGIN;
        if (checkInput(user.getLogin(), password)) {
            throw new DaoException(ERROR_USER_EXIST);
        }
        try {
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(queryInsert);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.setString(3, password);
            synchronized (ps) {
                ps.executeUpdate();
                return true;

            }
        } catch (SQLException e) {
            System.err.print(e);
            throw new DaoException(e);

        } finally {
            DatabaseConnection.closeStatements(ps);
            DatabaseConnection.closeConnection(db);

        }
    }


    @Override
    public boolean checkInput(String login, String password) {
        String query = SELECT_LOGIN_PASSWORD;

        try {
            System.out.println(login + " " + password);
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatements(ps);
            DatabaseConnection.closeConnection(db);
        }
    }

}
