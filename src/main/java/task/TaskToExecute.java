package task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TaskToExecute {

    private static Logger logger = LoggerFactory.getLogger(TaskToExecute.class);

    @Autowired
    private TaskExecutor taskExecutor;

    public void executeTask() {
        for (int i = 1; i < 5; i++) {
            taskExecutor.execute(()-> logger.info("Hello from thread: " + Thread.currentThread().getName()));
        }
    }
}