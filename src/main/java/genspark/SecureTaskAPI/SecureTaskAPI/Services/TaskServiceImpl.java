package genspark.SecureTaskAPI.SecureTaskAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genspark.SecureTaskAPI.SecureTaskAPI.Repository.TaskDAO;
import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    public TaskDAO taskDAO;

    @Override
    public List<Task> getAllTasks() {
        return this.taskDAO.findAll();
    }

    @Override
    public Task getTaskByID(long taskID) {

        Optional<Task> t = this.taskDAO.findById(taskID);
        Task task = null;
        if(t.isPresent())
            task=t.get();
        else
            throw new RuntimeException("Task Not Found: "+taskID);

        return task;
    }

    @Override
    public Task addNewTask(Task newTask) {
        return this.taskDAO.save(newTask);
    }

    @Override
    public Task updateTask(Task task) {
        return this.taskDAO.save(task);
    }

    @Override
    public String deleteTaskByID(long taskID) {
        this.taskDAO.deleteById(taskID);
        return "Task deleted successfully!";
    }
}
