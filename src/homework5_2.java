import java.util.Scanner;

public class homework5_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введи стрелки и тире: ");
        String arrows = scanner.nextLine();

        if (arrows.length() > 106) {
            System.out.println("Ой, слишком много стрелок з: Надо не больше 106!");
            return;
        }
        int count = 0;

        for (int a = 0; a <= arrows.length() - 5; a++) {
            String substring = arrows.substring(a, a + 5);
            if (substring.equals(">>-->")||substring.equals("<--<<")) {
                count++;
            }
        }
        System.out.println("Твое количество стрелок - " + count);
    }
}
