package genspark.SecureTaskAPI.SecureTaskAPI.Services;

import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();
    Task getTaskByID(long taskID);
    Task addNewTask(Task newTask);
    Task updateTask(Task task);
    String deleteTaskByID(long taskID);

}
