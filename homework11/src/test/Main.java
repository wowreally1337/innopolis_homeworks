package test;

import model.Car;
import repository.CarsRepository;
import repository.CarsRepositoryImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        CarsRepository repository = new CarsRepositoryImpl();
        //Список авто
        List<Car> cars = List.of(
                new Car ("a123me", "Mercedes", "White", 0, 8300000),
                new Car("b873of", "Volga", "Black", 0, 673000),
                new Car("w487mn", "Lexus", "Grey", 76000, 900000),
                new Car("p987hj", "Volga", "Red", 610, 704340),
                new Car("c987ss", "Toyota", "White", 254000, 761000),
                new Car("o983op", "Toyota", "Black", 698000, 740000),
                new Car("p146op", "BMW", "White", 271000, 850000),
                new Car("u893ii", "Toyota", "Purple", 210900, 440000),
                new Car("l097df", "Toyota", "Black", 108000, 780000),
                new Car("y876wd", "Toyota", "Black", 160000, 1000000)
        );
        repository.saveCars(cars);
        List<Car> loadedCars = repository.loadCars();

        System.out.println("Автомобили по базе: ");
        System.out.println("Number Model Color Mileage Price");
        loadedCars.forEach(System.out::println);
        System.out.println();
//Пробег или Цвет
        String colorToFind = "Black";
        int mileageToFind = 0;

        List<String> numbersByColorOrMileage = loadedCars.stream()
                .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());
        System.out.println("Номера автомобилей по цвету или пробегу: " +
                String.join(" ", numbersByColorOrMileage));
        System.out.println();
        //ценовой диапазон
        int minPrice = 700000;
        int maxPrice = 800000;

        int uniqueModelsCount = (int) loadedCars.stream()
                .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили в диапазоне " + minPrice + "-" + maxPrice + ": " + uniqueModelsCount + " шт.");
        System.out.println();
        //Цвет авто с самой низкой стоимостью
        String minPriceColor = loadedCars.stream()
                .min((c1, c2) -> Integer.compare(c1.getPrice(), c2.getPrice()))
                .map(Car::getColor)
                .orElse("Не найден автомобиль.");
        System.out.println("Цвет авто с минимальной стоимостью: " + minPriceColor);
        System.out.println();
        //Средняя стоимость Тойот и Вольво
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        double avgPriceToyota = loadedCars.stream()
                .filter(car -> car.getModel().equals(modelToFind1))
                .mapToInt(Car::getPrice)
                .average()
                .orElse(0.0);
        double avgPriceVolvo = loadedCars.stream()
                .filter(car -> car.getModel().equals(modelToFind2))
                .mapToInt(Car::getPrice)
                .average()
                .orElse(0.0);
        System.out.println("Средняя стоимость модели Toyota: " + (int) avgPriceToyota);
        System.out.println("Средняя стоимость модели Volvo: " + (int) avgPriceVolvo);
        saveResultsToFile(loadedCars);
    }
    private static void saveResultsToFile(List<Car> cars) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:/Users/mrcro/IdeaProjects/innopolis_homeworks/homework11/data/cars.txt"))) {
            writer.println("Результаты анализа автомобилей:");
            writer.println("=================================");
            //Цвет и пробег
            String colorToFind = "Black";
            int mileageToFind = 0;
            List<String> numbers = cars.stream()
                    .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                    .map(Car::getNumber)
                    .collect(Collectors.toList());
            writer.println("Номера автомобилей по цвету или пробегу: " +
                    String.join(" ", numbers));
            //Уникальные модели в ценовом диапазоне
            int minPrice = 700000;
            int maxPrice = 800000;
            int count = Math.toIntExact(cars.stream()
                    .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                    .map(Car::getModel)
                    .distinct()
                    .count());
            writer.println("Уникальные модели в диапазоне " + minPrice + "-" + maxPrice + ": " + count + " шт.");
            //Цвет с мин.стоимостью
            String minColor = cars.stream()
                    .min((c1, c2) -> Integer.compare(c1.getPrice(), c2.getPrice()))
                    .map(Car::getColor)
                    .orElse("Не найден");
            writer.println("Цвет автомобиля с минимальной стоимостью: " + minColor);
            //Средняя стоимость Toyota и Volvo
            String model1 = "Toyota";
            String model2 = "Volvo";
            double avg1 = cars.stream()
                    .filter(car -> car.getModel().equals(model1))
                    .mapToInt(Car::getPrice)
                    .average()
                    .orElse(0.0);
            double avg2 = cars.stream()
                    .filter(car -> car.getModel().equals(model2))
                    .mapToInt(Car::getPrice)
                    .average()
                    .orElse(0.0);
            writer.printf("Средняя стоимость %s: %.2f%n", model1, avg1);
            writer.printf("Средняя стоимость %s: %.2f%n", model2, avg2);

            System.out.println("Результаты сохранены в файл: data/cars.txt");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении результатов: " + e.getMessage());
        }
    }
}

//[НОМЕР_АВТОМОБИЛЯ][МОДЕЛЬ][ЦВЕТ][ПРОБЕГ][ЦЕНА]
//a123me|Mercedes|White|0|8300000
//b873of|Volga|Black|0|673000
//w487mn|Lexus|Grey|76000|900000
//p987hj|Volga|Red|610|704340
//c987ss|Toyota|White|254000|761000
//o983op|Toyota|Black|698000|740000
//p146op|BMW|White|271000|850000
//u893ii|Toyota|Purple|210900|440000
//l097df|Toyota|Black|108000|780000
//y876wd|Toyota|Black|160000|1000000
//Black, 0L
//700_000L, 800_000L
//Toyota
//Volvo