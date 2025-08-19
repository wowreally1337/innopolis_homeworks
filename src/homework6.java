import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class homework6 {
    //Класс Product
    static class Product {
        private String name;
        private int price;

        public Product(String name, int price) {
            validateName(name);
            validatePrice(price);
            this.name = name;
            this.price = price;
        }
        private void validateName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Название продукта не может быть пустым.");
            }
            if (name.length() < 3) {
                throw new IllegalArgumentException("Название продукта не может быть короче 3 символов.");
            }
            if (name.matches("\\d+")) {
                throw new IllegalArgumentException("Название продукта не может состоять только из цифр.");
            }
        }
        private void validatePrice(int price) {
            if (price <= 0) {
                throw new IllegalArgumentException("Цена не может быть отрицательной.");
            }
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    //Класс DiscountProduct
    static class DiscountProduct extends Product {
        private int discount;
        private LocalDate discountEndDate;
        public DiscountProduct(String name, int price, int discount, String discountEndDate) {
            super(name, price);
            validateDiscount(discount);
            validateDiscountDate(discountEndDate);
            this.discount = discount;
            this.discountEndDate = LocalDate.parse(discountEndDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        private void validateDiscount(int discount) {
            if (discount <= 0) {
                throw new IllegalArgumentException("Скидка должна быть положительной.");
            }
            if (discount >= super.getPrice()) {
                throw new IllegalArgumentException("Скидка не может быть больше цены продукта или равна ей.");
            }
        }
        private void validateDiscountDate(String date) {
            try {
                LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                if (parsedDate.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("Скидка действует только в настоящем времени.");
                }
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Неверный формат даты. Введите дату в формате день.месяц.год.");
            }
        }
        public int getDiscount() {
            return discount;
        }
        public LocalDate getDiscountEndDate() {
            return discountEndDate;
        }
        @Override
        public int getPrice() {
            if (isDiscountValid()) {
                return super.getPrice() - discount;
            }
            return super.getPrice();
        }
        public boolean isDiscountValid() {
            return LocalDate.now().isBefore(discountEndDate)||
                    LocalDate.now().isEqual(discountEndDate);
        }
        @Override
        public String toString() {
            String discountInfo = isDiscountValid() ?
                    " (скидка: " + discount + " руб., действует до: " + discountEndDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ")" + : "(скидка недействительна).";
            return super.getName() + discountInfo;
        }
    }

    //Класс Person
    static class Person {
        private String name;
        private int money;
        private List<Product> bag;

        public Person(String name, int money) {
            this.name = name;
            this.money = money;
            this.bag = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public int getMoney() {
            return money;
        }

        public List<Product> getBag() {
            return bag;
        }

        public boolean canAfford(Product product) {
            return money >= product.getPrice();
        }

        public void buyProduct(Product product) {
            bag.add(product);
            money -= product.getPrice();
        }

        @Override
        public String toString() {
            if (bag.isEmpty()) {
                return name + " - Ничего не куплено";
            }
            StringBuilder sb = new StringBuilder(name + " - ");
            for (int i = 0; i < bag.size(); i++) {
                Product product = bag.get(i);
                if (product instanceof DiscountProduct) {
                    DiscountProduct dp = (DiscountProduct) product;
                    sb.append(product.getName())
                            .append(" (")
                            .append(dp.isDiscountValid() ? "со скидкой" : "без скидки.")
                            .append(") .");
                } else {
                    sb.append(bag.get(i).getName());
                }
                if (i < bag.size() - 1) sb.append(", ");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод покупателей
        List<Person> people = new ArrayList<>();
        System.out.println("Введите покупателей в формате: Имя = Сумма");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;

            try {
                String[] parts = input.split("=");
                if (parts.length != 2) {
                    System.out.println("Ошибка формата! Используйте: Имя = Сумма");
                    continue;
                }

                String name = parts[0].trim();
                String moneyStr = parts[1].trim();

                // Валидация имени
                if (name.isEmpty()) {
                    System.out.println("Имя не может быть пустым");
                    continue;
                }
                if (name.length() < 3) {
                    System.out.println("Имя не может быть короче 3 символов");
                    continue;
                }

                int money = Integer.parseInt(moneyStr);
                if (money < 0) {
                    System.out.println("Деньги не могут быть отрицательными");
                    continue;
                }

                people.add(new Person(name, money));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Сумма должна быть числом");
            }
        }

        // Ввод продуктов
        List<Product> products = new ArrayList<>();
        System.out.println("Введите продукты в формате: Название = Цена");


        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;

            try {
                String[] parts = input.split("=");
                if (parts.length != 2) {
                    System.out.println("Ошибка формата! Используйте: Название = Цена");
                    continue;
                }

                String name = parts[0].trim();
                String priceStr = parts[1].trim();

                // Валидация названия
                if (name.isEmpty()) {
                    System.out.println("Название продукта не может быть пустым");
                    continue;
                }

                int price = Integer.parseInt(priceStr);
                if (price < 0) {
                    System.out.println("Стоимость продукта не может быть отрицательной");
                    continue;
                }

                products.add(new Product(name, price));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Цена должна быть числом");
            }
        }

        // Обработка покупок
        System.out.println("Введите покупки в формате: Имя - Продукт (END для завершения)");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("END")) break;

            String[] parts = input.split("-");
            if (parts.length != 2) {
                System.out.println("Ошибка формата! Используйте: Имя - Продукт");
                continue;
            }

            String personName = parts[0].trim();
            String productName = parts[1].trim();

            // Поиск покупателя
            Person person = null;
            for (Person p : people) {
                if (p.getName().equals(personName)) {
                    person = p;
                    break;
                }
            }
            if (person == null) {
                System.out.println("Покупатель '" + personName + "' не найден");
                continue;
            }

            // Поиск продукта
            Product product = null;
            for (Product p : products) {
                if (p.getName().equals(productName)) {
                    product = p;
                    break;
                }
            }
            if (product == null) {
                System.out.println("Продукт '" + productName + "' не найден");
                continue;
            }

            // Обработка покупки
            if (person.canAfford(product)) {
                person.buyProduct(product);
                System.out.println(person.getName() + " купил " + product.getName());
            } else {
                System.out.println(person.getName() + " не может позволить себе " + product.getName());
            }
        }

        // Вывод результатов
        System.out.println("Результаты:");
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
