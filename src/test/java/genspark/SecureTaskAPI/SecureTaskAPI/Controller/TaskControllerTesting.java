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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

        String view = taskController.getTasks(model);

        verify(model).addAttribute("taskList", new ArrayList<Task>(){});

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

}
