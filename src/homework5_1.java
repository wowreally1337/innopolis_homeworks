import java.util.Scanner;

public class homework5_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Поздравляю, вы инглишмен. Введите маленькую букву ангельской мовы: ");
        char input = scanner.nextLine().charAt(0);

        String keyboard = "qwertyuiopasdfghjklzxcvbnm";
        int index = keyboard.indexOf(input);
        int leftIndex = (index - 1 + keyboard.length()) % keyboard.length();

        System.out.println("Твоя буква это - " + keyboard.charAt(leftIndex));
    }
}
