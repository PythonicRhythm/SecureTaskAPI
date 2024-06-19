package genspark.SecureTaskAPI.SecureTaskAPI.Services;

import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;
import genspark.SecureTaskAPI.SecureTaskAPI.Repository.TaskDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTesting {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskDAO taskDao;

    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_add_new_task() {

        System.out.println("TEST add_new_task():");

        Task task1 = new Task(1,"task1","task1descrip",new Date(1001));
        Task task2 = new Task(2, "task2", "task2descrip", new Date(1002));

        taskService.addNewTask(task1);

        verify(taskDao, times(1)).save(argThat(t -> t.getTaskID() == 1));

        taskService.addNewTask(task2);

        verify(taskDao, times(1)).save(argThat(t -> t.getTaskID() == 2));

        System.out.println("TEST add_new_task() complete!");

    }

    @Test
    public void test_get_all_tasks() {

        System.out.println("TEST get_all_tasks():");

        Task task1 = new Task(1,"task1","task1descrip",new Date(1001));
        Task task2 = new Task(2, "task2", "task2descrip", new Date(1002));

        given(taskDao.findAll()).willReturn(asList(task1, task2));

        //When
        List<Task> allBooks = taskService.getAllTasks();

        //Then
        assertThat(allBooks).containsExactly(task1, task2);

        System.out.println("TEST get_all_tasks() complete!");

    }

    @Test
    public void test_get_task_by_id() {

        System.out.println("TEST get_task_by_id:");

        Task task1 = new Task(1,"task1","task1descrip",new Date(1001));
        Task task2 = new Task(2, "task2", "task2descrip", new Date(1002));

        given(taskDao.findById(1L)).willReturn(Optional.of(task1));

        Task taskRet = taskService.getTaskByID(1L);
        assertThat(taskRet).isEqualTo(task1);

        given(taskDao.findById(2L)).willReturn(Optional.of(task2));

        Task taskRet2 = taskService.getTaskByID(2L);
        assertThat(taskRet2).isEqualTo(task2);

        System.out.println("TEST get_task_by_id() complete!");

    }


}
