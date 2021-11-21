import configuration.Configuration_Annotation;
import configuration.Configuration_TaskExecutor;
import configuration.Configuration_XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import service.AsyncService;
import service.CarService;
import task.TaskToExecute;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Executor {

    private static Logger logger = LoggerFactory.getLogger(Executor.class);
    public static GenericApplicationContext ctx;

    public static void main(String[] args) throws InterruptedException {

        configuration_XML();
        configuration_annotation();
        configuration_annotation_async();
        configuration_annotation_async();
        taskExecutor();

    }

    private static void configuration_XML() throws InterruptedException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Configuration_XML.class);
//        CarService carService = ctx.getBean("carService", CarService.class);

//        while (!carService.isDone()) {
//            logger.info("Waiting for scheduled job to end ... ");
            Thread.sleep(6000);
//        }

        ctx.close();
    }

    private static void configuration_annotation() throws InterruptedException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Configuration_Annotation.class);
//        CarService carService = ctx.getBean("carService", CarService.class);

//        while (!carService.isDone()) {
//            logger.info("Waiting for scheduled job to end ... ");
            Thread.sleep(6000);
//        }

        ctx.close();
    }

    private static void configuration_annotation_async() throws InterruptedException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Configuration_Annotation.class);
        AsyncService asyncService = ctx.getBean("asyncServiceImpl", AsyncService.class);
        for (int i = 0; i < 2; i++) {
            asyncService.asyncTask();
        }
        Thread.sleep(6000);
        ctx.close();

        ctx = new AnnotationConfigApplicationContext(Configuration_Annotation.class);
        Future<String> result1 = asyncService.asyncWithReturn("John Mayer");
        Future<String> result2 = asyncService.asyncWithReturn("Eric Clapton");
        try {
            logger.info("Result1: " + result1.get());
            logger.info("Result2: " + result2.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Thread.sleep(6000);
        ctx.close();
    }

    private static void taskExecutor() throws InterruptedException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Configuration_TaskExecutor.class);

        TaskToExecute taskToExecute = ctx.getBean(TaskToExecute.class);
        taskToExecute.executeTask();

        Thread.sleep(6000);
        ctx.close();
    }
}