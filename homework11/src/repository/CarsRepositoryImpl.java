package repository;


import model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarsRepositoryImpl implements CarsRepository {
    private static final String FILE_PATH = "C:/Users/mrcro/IdeaProjects/innopolis_homeworks/homework11/data/cars.txt";

    @Override
    public List<Car> getAllCars() {
        return loadCars();
    }

    @Override
    public void saveCars(List<Car> cars) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Car car : cars) {
                writer.println(car.getNumber() + "|" + car.getModel() + "|" +
                        car.getColor() + "|" + car.getMileage() + "|" + car.getPrice());
            }
            System.out.println("Данные сохранены в файл: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    @Override
    public List<Car> loadCars() {
        List<Car> cars = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Файл " + FILE_PATH + " не существует. Будет создан новый файл.");
            return cars;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    Car car = new Car(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            Integer.parseInt(parts[3].trim()),
                            Integer.parseInt(parts[4].trim())
                    );
                    cars.add(car);
                }
            }
            System.out.println("Данные загружены из файла: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка формата чисел: " + e.getMessage());
        }
        return cars;
    }
}
