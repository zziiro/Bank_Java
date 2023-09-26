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

/*
* I am aware with this login system, the user can enter the password as the username (and visa versa)
* and still be able to log in because I am just searching the entire file to see if that particular
* string exists. However, I am also aware this is not a very practical nor efficient way of doing
* a user login page so that's why I didn't feel the need to fix it.
* - This is just to show my understanding of working with files and the simple structure of log in
* and other forms of data saving.
* */

/*
* As well as updating the BankInformation method, I am aware that it would never be done this way.
* Just to show working with files and overwriting existing data\g
* I'm sure there is a replace method that can replace a certain string in a file with user inputted data
* But it's an unnecessary work-around for an unrealistic technique.
* */