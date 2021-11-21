package service;

import entity.Car;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service("carService")
@Repository
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    public boolean done;

    public static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        carRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void updateCarAgeJob() {
        System.out.println("--- Configuration_XML ---");
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

    @Override
    public boolean isDone() {
        return done;
    }
}