import java.util.Random;

public class homework3_2 {
    public static void main(String[] args) {
        String a = "камень";
        String b = "ножницы";
        String c = "бумага";
        Random d = new Random();
        int i = d.nextInt(3);
        int f = d.nextInt(3);
        String iSet;
        String fSet;
        // Вася
        if (i == 0) {
            iSet = a;
        } else if (i == 1) {
            iSet = b;
        } else {
            iSet = c;
        }
        // Петя
        if (f == 0) {
            fSet = a;
        } else if (f == 1) {
            fSet = b;
        } else {
            fSet = c;
        }
        System.out.println("Вася - " +iSet+ "");
        System.out.println("Петя - " +fSet+ "");
        // результат игры}
        if (i == f) {
            System.out.println("Ничья з:");
        } else if ((i==0 & f==1)||
                (i==1 & f==2)||
                (i==2 & f==0)) {
            System.out.println("Вася победитель!!");
        } else {
            System.out.println("Петя победитель!!");
        }
    }
}
