package genspark.SecureTaskAPI.SecureTaskAPI;

import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;
import genspark.SecureTaskAPI.SecureTaskAPI.Services.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

// JUNIT SPECIFIC TESTING

@SpringBootTest
class SecureTaskApiApplicationTests {

	@Autowired
	private TaskServiceImpl taskService;

	@Test
	void contextLoads() {
		assertThat(taskService).isNotNull();
	}

	@Test
	public void testing_add_new_task() {
		Task t1 = taskService.addNewTask(new Task(1,"t1","det1",new Date(1999)));
		Task t2 = taskService.addNewTask(new Task(2,"t2","det2",new Date(2000)));
		Task t3 = taskService.addNewTask(new Task(3,"t3","det3",new Date(2001)));

		//assert(t1.equals(taskService.getTaskByID(1)));

		assert(t1.getTaskID() == 1);
		assert(t1.getTitle().equals("t1"));
		assert(t1.getDescription().equals("det1"));
		assert(t1.getDueDate().equals(new Date(1999)));

		assert(t2.getTaskID() == 2);
		assert(t2.getTitle().equals("t2"));
		assert(t2.getDescription().equals("det2"));
		assert(t2.getDueDate().equals(new Date(2000)));

		assert(t3.getTaskID() == 3);
		assert(t3.getTitle().equals("t3"));
		assert(t3.getDescription().equals("det3"));
		assert(t3.getDueDate().equals(new Date(2001)));

	}

	@Test
	public void testing_get_all_tasks() {
		Task t1 = taskService.addNewTask(new Task(1,"t1","det1",new Date(1999)));
		Task t2 = taskService.addNewTask(new Task(2,"t2","det2",new Date(2000)));
		Task t3 = taskService.addNewTask(new Task(3,"t3","det3",new Date(2001)));

		List<Task> tasks = Arrays.asList(t1, t2, t3);
		assertEquals(tasks.toString(), taskService.getAllTasks().toString());
	}


}
