import java.util.Scanner;

public final class ConsoleUtils {
    private ConsoleUtils() {}

    public static int lerInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Digite um número inteiro: ");
            sc.next();
        }
        int v = sc.nextInt();
        sc.nextLine(); // consome \n
        return v;
    }

    public static double lerDouble(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.print("Digite um número válido: ");
            sc.next();
        }
        double v = sc.nextDouble();
        sc.nextLine();
        return v;
    }

    public static String lerStr(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static boolean lerBool(Scanner sc, String prompt) {
        System.out.print(prompt + " (s/n): ");
        String s = sc.nextLine().trim().toLowerCase();
        return s.startsWith("s");
    }

    public static void printlnTitulo(String t) {
        System.out.println("=== " + t + " ===");
    }
}
