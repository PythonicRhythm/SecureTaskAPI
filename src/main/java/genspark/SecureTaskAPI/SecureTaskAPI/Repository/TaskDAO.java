package genspark.SecureTaskAPI.SecureTaskAPI.Repository;

import genspark.SecureTaskAPI.SecureTaskAPI.Entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskDAO extends JpaRepository<Task, Long>{

}
