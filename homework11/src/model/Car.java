package model;

public class Car {
    private String number;
    private String model;
    private String color;
    private int mileage;
    private int price;

    public Car(String number, String model, String color, Integer mileage, Integer price) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
    }
//Геттеры
    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public int getPrice() {
        return price;
    }
//Сеттеры
    public void setNumber(String number) {
        this.number = number;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return number + " " + model + " " + color + " " + mileage + " " + price + " ";
    }
}
