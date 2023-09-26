import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        // User login
        System.out.println("USERNAME: ");
        username = scan.nextLine();
        System.out.println("PASSWORD: ");
        password = scan.nextLine();

        UserInformation userInfo = new UserInformation(username, password);
        userInfo.LogIn();
    }
}
