package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.enums.ChoiceTask;
import by.gsu.epamlab.impl.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface ITaskDao {
     List<Tasks> getTasks(User user, ChoiceTask choiceTask) throws DaoException;

     void fixedTasks(HttpServletRequest request);

     default List<Integer> getTaskId(HttpServletRequest request){
        List<Integer> ids = new ArrayList<>();
        Collections.list(request.getParameterNames()).stream().filter(e -> e.startsWith("task-"))
                .forEach(e -> ids.add(Integer.valueOf(e.split("-")[1])));
        return ids;
    }

     void deleteTask(HttpServletRequest request);

     void restoreTask(HttpServletRequest request);

     void deleteTaskFromRecycle(HttpServletRequest request);

     void deleteAllTasksFromRecycle();

     void setFileURLForTask(Tasks tasks);

     void addTask(Tasks tasks);

}
