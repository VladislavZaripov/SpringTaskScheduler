package service;

import entity.Car;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("scheduledCarServiceImpl")
@Repository
@Transactional(readOnly = true)
public class ScheduledCarServiceImpl extends CarServiceImpl{

    @Override
    @Scheduled(fixedDelay = 5000)
    public void updateCarAgeJob() {
        System.out.println("--- Configuration_Annotation ---");
        List<Car> cars = findAll();
        DateTime currentDate = DateTime.now();
        logger.info("Car age update job started");
        cars.forEach(car -> {
            int age = car.getManufacturerDate().getYear() - currentDate.getYear();
            car.setAge(age);
            save(car);
            logger.info("Car age update -->" + car);
        });
        logger.info("Car age update job completed successfully");
        done = true;
    }
}