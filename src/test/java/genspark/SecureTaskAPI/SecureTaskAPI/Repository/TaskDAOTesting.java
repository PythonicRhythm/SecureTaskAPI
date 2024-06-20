package genspark.SecureTaskAPI.SecureTaskAPI.Repository;

import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import java.sql.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskDAOTesting {

    @Mock
    private TaskDAO taskDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_add_task() {

        Task task1 = new Task(1, "title", "descrip", new Date(1000));
        Task task2 = new Task(2, "title2", "descrip2", new Date(1002));

        taskDAO.save(task1);
        verify(taskDAO, times(1)).save(argThat(t -> t.getTaskID() == 1));

        taskDAO.save(task1);
        verify(taskDAO, times(2)).save(argThat(t -> t.getTaskID() == 1));

        taskDAO.save(task1);
        taskDAO.save(task1);
        verify(taskDAO, times(4)).save(argThat(t -> t.getTaskID() == 1));

        taskDAO.save(task2);
        taskDAO.save(task2);
        verify(taskDAO, times(4)).save(argThat(t -> t.getTaskID() == 1));
        verify(taskDAO, times(2)).save(argThat(t -> t.getTaskID() == 2));
    }

    @Test
    public void test_get_all_tasks() {

        taskDAO.findAll();
        verify(taskDAO, times(1)).findAll();

        taskDAO.findAll();
        verify(taskDAO, times(2)).findAll();

    }

    @Test
    public void test_delete_by_id() {

        Task task1 = new Task(1, "title", "descrip", new Date(1000));

        // JPA returns void for deletions.

        taskDAO.deleteById(1L);
        verify(taskDAO, times(1)).deleteById(1L);

        taskDAO.deleteById(2L);
        verify(taskDAO, times(1)).deleteById(2L);

        taskDAO.deleteById(1L);
        verify(taskDAO, times(2)).deleteById(1L);
    }

    //@Test

}
