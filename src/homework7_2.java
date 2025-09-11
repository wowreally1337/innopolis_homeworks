import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class homework7_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи первую строку: ");
        String s = scanner.nextLine().toLowerCase();

        System.out.print("Введи вторую строку: ");
        String t = scanner.nextLine().toLowerCase();

        boolean result = isAnagram(s, t);
        System.out.println(result);

        scanner.close();
    }
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;
            }
            charCount.put(c, charCount.get(c) - 1);
            if (charCount.get(c) == 0) {
                charCount.remove(c);
            }
        }
        return charCount.isEmpty();
    }
}

//С консоли на вход подается две строки s и t. Необходимо вывести true, если одна
//строка является валидной анаграммой другой строки, и false – если это не так.
//Анаграмма – это слово, или фраза, образованная путем перестановки букв другого
//слова или фразы, обычно с использованием всех исходных букв ровно один раз.
//Для проверки:
//● Бейсбол – бобслей
//● Героин – регион
//● Клоака – околка
