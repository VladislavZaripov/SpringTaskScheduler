package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service("asyncServiceImpl")
public class AsyncServiceImpl implements AsyncService{

    public static Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public void asyncTask() {
        System.out.println("--- AsyncServiceImpl asyncTask ---");
        logger.info("Start execution of async task");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            logger.error("Task interruption: " + e);
        }
        logger.info("Complete execution of async task");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        System.out.println("--- AsyncServiceImpl asyncWithReturn ---");
        logger.info("Start execution of async task with return for " + name);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            logger.error("Task interruption: " + e);
        }
        logger.info("Complete execution of async task with return for " + name);
        return new AsyncResult<>("Hello: " + name);
    }
}