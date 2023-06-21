import java.util.Scanner;

public class Leer {

    private static Scanner scanner = new Scanner(System.in);

    public Leer() {
    }

    public static String leerString() {
            return scanner.nextLine();
        }

    public static int leerInt() {
            return scanner.nextInt();
        }

    public static double leerDouble() {
            return scanner.nextDouble();
        }

    public static boolean leerHashNextDouble(){
        return scanner.hasNextDouble();
            }

    public static boolean leerBoolean() {
            return scanner.nextBoolean();
        }

    public static boolean leerHasNextInt() {
        return scanner.hasNextInt();
        }

    public static String leerNext() {
        return scanner.next();
    }

    public static String leerNextLine() {return scanner.nextLine(); }

    public static void leerClose() {
        scanner.close();
    }


}
