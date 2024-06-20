package genspark.SecureTaskAPI.SecureTaskAPI.Controller;

import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;
import genspark.SecureTaskAPI.SecureTaskAPI.Services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTesting {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private Model model;

    @Mock
    private MockMvc mockMvc;

    @Mock
    private OAuth2User oAuth2User;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIndex() {
        System.out.println("TEST index():");
        assert("index".equals(taskController.index()));
        System.out.println("TEST index() complete!");
    }

    @Test
    public void testLogin() {
        System.out.println("TEST login():");
        assert("login".equals(taskController.login()));
        System.out.println("TEST login() complete!");
    }

    @Test
    public void testGetTasks() {
        System.out.println("TEST getTasks():");

        // Case 1:
        String view = taskController.getTasks(model);

        verify(model).addAttribute("taskList", new ArrayList<Task>(){});

        assert("tasks".equals(view));

        // Case 2:
        Task task1 = new Task(1,"task1","task1descrip",new Date(1001));
        Task task2 = new Task(2, "task2", "task2descrip", new Date(1002));

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(tasks);
        view = taskController.getTasks(model);
        verify(model, times(1)).addAttribute("taskList", tasks);
        assert("tasks".equals(view));

        // Case 3:
        Task task3 = new Task(3,"task3","task3descrip",new Date(1003));
        Task task4 = new Task(4, "task4", "task4descrip", new Date(1004));

        List<Task> tasks2 = Arrays.asList(task3, task4);

        when(taskService.getAllTasks()).thenReturn(tasks2);
        view = taskController.getTasks(model);
        verify(model, times(1)).addAttribute("taskList", tasks2);
        assert("tasks".equals(view));

        System.out.println("TEST login() complete!");
    }

    @Test
    public void testGetSingleTask() {
        System.out.println("TEST getSingleTask():");

        Task task1 = new Task(1, "title", "descrip", new Date(1000));

        given(taskService.getTaskByID(1L)).willReturn(task1);

        assert(task1.equals(taskController.getSingleTask(1L)));

        System.out.println("TEST getSingleTask() complete!");
    }

    @Test
    public void testDeleteTask() {
        System.out.println("TEST deleteTask():");

        Task task1 = new Task(1, "title", "descrip", new Date(1000));

        String view = taskController.deleteTask(1L);

        assert("tasks".equals(view));

        System.out.println("TEST deleteTask() complete!");
    }

}
