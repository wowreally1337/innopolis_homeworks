import java.util.Arrays;
import java.util.Scanner;

public class homework5_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().toLowerCase().split(" ");

        for (String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            System.out.print(new String(letters) + " ");
        }
    }
}
