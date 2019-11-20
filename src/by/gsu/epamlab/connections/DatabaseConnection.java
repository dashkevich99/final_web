package by.gsu.epamlab.connections;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DRIVER_NAME = "org.gjt.mm.mysql.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jee";
    private static final String DB_USER_NAME = "jse";
    private static final String DB_PASSWORD = "P123456p";

    static {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void closeStatements(Statement... statements) {
        for (Statement statement : statements) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResourses(ResultSet rs, PreparedStatement ps, Connection cn) {
        closeResultSet(rs);
        closePreparedStatement(ps);
        closeConnection(cn);
    }
}
