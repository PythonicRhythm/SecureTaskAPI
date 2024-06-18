package genspark.SecureTaskAPI.SecureTaskAPI.Controller;

import genspark.SecureTaskAPI.SecureTaskAPI.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    public TaskService ts;


    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model)
    {
        model.addAttribute("taskList", this.ts.getAllTasks());
        return "tasks";
    }

    @GetMapping("/tasks/{taskID}")
    public Task getSingleTask(@PathVariable Long taskID)
    {
        return this.ts.getTaskByID(taskID);
    }

    @PostMapping(path = "/tasks", consumes = "application/json")
    public String addTask(@RequestBody List<Task> tasks, BindingResult result, Model model)
    {
        model.addAttribute("taskList", tasks);
        if(!result.hasErrors()) {
            this.ts.addNewTask(tasks.get(0));
            return "tasks";
        }
        return "tasks";
    }

    @PostMapping(path = "/tasks", consumes = "application/x-www-form-urlencoded")
    public String addTask(Task task, BindingResult result, Model model)
    {
        List<Task> allTasks = this.ts.getAllTasks();
        allTasks.add(task);
        model.addAttribute("taskList", allTasks);
        if(!result.hasErrors()) {
            this.ts.addNewTask(task);
            return "tasks";
        }
        return "tasks";
    }

    @PutMapping("/tasks")
    public String updateTask(@RequestBody Task task, BindingResult result, Model model)
    {
        model.addAttribute("taskList", task);
        if(!result.hasErrors()) {
            this.ts.updateTask(task);
            return "tasks";
        }
        return "tasks";
    }

    @DeleteMapping("/tasks/{taskID}")
    public String deleteTask(@PathVariable Long taskID)
    {
        this.ts.deleteTaskByID(taskID);
        return "tasks";
    }
}
