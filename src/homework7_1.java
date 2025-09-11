import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class homework7_1 {
    public static void main(String[]args ) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(3);
        numbers.add(7);
        numbers.add(6);
        numbers.add(6);
        numbers.add(6);
        numbers.add(3);
        numbers.add(2);
        numbers.add(2);

        System.out.println("Список:" + numbers);
        System.out.println("Уникальные значения:" + getUniqueElements(numbers));
    }
    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        return new HashSet<>(list);
    }
}
//Задание 1:
//Реализовать метод, который на вход принимает ArrayList<T>, а возвращает набор
//уникальных элементов этого массива. Решить, используя коллекции
