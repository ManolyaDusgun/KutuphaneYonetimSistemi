import java.util.Scanner;

public class LoginSystem {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    public static boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        return USERNAME.equals(user) && PASSWORD.equals(pass);
    }
}
