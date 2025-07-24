import java.util.Scanner;

public class homework4 {
    static class TV {
            private String brand;
            private String colour;
            private int price;

        public TV(String самсунг, String розовый, String number) {

        }

        public TV(String brand, String colour, int price) {

        }

        void TV(String brand, String colour, int price) {
                this.brand = brand;
                this.colour = colour;
                this.price = price;
            }

            public String getBrand() {
                return brand;
            }

            public String getColour() {
                return colour;
            }

            public int getPrice() {
                return price;
            }

            public void setBrand(String сяоме) {
                this.brand = brand;
            }

            public void setColour(String colour) {
                this.colour = colour;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public void displayInfo() {
                System.out.println("Телевизор " + brand + "Цвет: " + colour + "Цена:" + price + "грошей.");
            }
        }
    public class App {
        public static void main(String[] args) {
            TV tv1 = new TV("Самсунг", "Розовый", "1337");
            TV tv2 = new TV("Сяоми", "Белый", "999");
            TV tv3 = new TV("Тувио", "Черный", "666");

            tv1.displayInfo();
            tv2.displayInfo();
            tv3.displayInfo();

            tv1.setColour("Фиолетовый");
            tv1.setBrand("Сяоме");
            tv1.setPrice(Integer.parseInt("7331"));

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nДелаем ваш телик....");

            System.out.print("Введите бренд: ");
            String brand = scanner.nextLine();

            System.out.print("Введите цвет: ");
            String colour = scanner.nextLine();

            System.out.print("Введите цену: ");
            int price = scanner.nextInt();

            TV tv4 = new TV(brand, colour, price);
            tv4.displayInfo();

            scanner.close();

        }
    }
}
