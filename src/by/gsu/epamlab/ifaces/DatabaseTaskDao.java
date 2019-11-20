package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connections.DatabaseConnection;
import by.gsu.epamlab.enums.ChoiceTask;
import by.gsu.epamlab.impl.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTaskDao implements ITaskDao {
    private static final String UPDATE_FIXED = "UPDATE tasks SET Fixed = 1 WHERE idTask = ?";
    private static final String DELETE_RECYCLE = "UPDATE tasks SET RecycleBin = 1 WHERE idTask = ?";
    private static final String RESTORE_RECYCLE = "UPDATE tasks SET RecycleBin = 0 WHERE idTask = ?";
    private static final String DELETE_FROM_RECYCLE = "DELETE FROM tasks WHERE RecycleBin = 1 AND idTask = ? ";
    private static final String DELETE_ALL_FROM_RECYCLE = "DELETE FROM tasks WHERE RecycleBin = 1";
    private static final String SET_URL_FILE = "UPDATE tasks SET url = ? WHERE idTask = ?";
    private static final String ADD_TASK = "INSERT INTO tasks(idUser, CreateDate, Title, Body) values(?, ?, ?, ?)";
    private PreparedStatement ps = null;
    private Connection db = null;
    private ResultSet rs = null;

    @Override
    public List<Tasks> getTasks(User user, ChoiceTask choiceTask) throws DaoException {
        List<Tasks> tasks = new ArrayList<>();

        try {
            db = new DatabaseConnection().createConnection();
            rs= db.createStatement().executeQuery(choiceTask.getQuery(user));
            while(rs.next()){
                tasks.add(new Tasks(user, rs.getInt("idTask"), rs.getDate("CreateDate").toLocalDate(),
                        rs.getString("Title"), rs.getString("Body"),
                        rs.getInt("Fixed"), rs.getInt("RecycleBin"),rs.getString("url")));
            }
            return tasks;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void fixedTasks(HttpServletRequest request) {
        try{

            List<Integer> ids = this.getTaskId(request);
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(UPDATE_FIXED);
            for (int id:ids) {
                ps.setInt(1,id);
                synchronized (DatabaseTaskDao.class){
                    ps.execute();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closePreparedStatement(ps);
            DatabaseConnection.closeConnection(db);
        }
    }

    @Override
    public void deleteTask(HttpServletRequest request) {
        try{
            List<Integer> ids = this.getTaskId(request);
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(DELETE_RECYCLE);
            for (int id:ids) {
                ps.setInt(1,id);
                synchronized (DatabaseTaskDao.class){
                    ps.execute();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closePreparedStatement(ps);
            DatabaseConnection.closeConnection(db);
        }
    }

    @Override
    public void restoreTask(HttpServletRequest request) {
        try{
            List<Integer> ids = this.getTaskId(request);
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(RESTORE_RECYCLE);
            for (int id:ids) {
                ps.setInt(1,id);
                synchronized (DatabaseTaskDao.class){
                    ps.execute();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closePreparedStatement(ps);
            DatabaseConnection.closeConnection(db);
        }
    }

    @Override
    public void deleteTaskFromRecycle(HttpServletRequest request) {
        try{
            List<Integer> ids = this.getTaskId(request);
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(DELETE_FROM_RECYCLE);
            for (int id:ids) {
                ps.setInt(1,id);
                synchronized (DatabaseTaskDao.class){
                    ps.execute();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closePreparedStatement(ps);
            DatabaseConnection.closeConnection(db);
        }
    }

    @Override
    public void deleteAllTasksFromRecycle() {
        try{
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(DELETE_ALL_FROM_RECYCLE);

                synchronized (DatabaseTaskDao.class){
                    ps.execute();
                }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closePreparedStatement(ps);
            DatabaseConnection.closeConnection(db);
        }
    }

    @Override
    public void setFileURLForTask(Tasks task) {
        try{
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(SET_URL_FILE);
            ps.setString(1, task.getUrl());
            ps.setInt(2, task.getIdTask());
            synchronized (DatabaseTaskDao.class){
                ps.execute();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closePreparedStatement(ps);
            DatabaseConnection.closeConnection(db);
        }
    }

    @Override
    public void addTask(Tasks task) {
        try{
            db = new DatabaseConnection().createConnection();
            ps = db.prepareStatement(ADD_TASK);
            ps.setInt(1,task.getUser().getUserId());
            ps.setDate(2, Date.valueOf(task.getDate()));
            ps.setString(3, task.getTitle());
            ps.setString(4, task.getBody());
                synchronized (DatabaseTaskDao.class){
                    ps.execute();
                }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseConnection.closeStatements(ps);
            DatabaseConnection.closeConnection(db);
        }
    }
}
