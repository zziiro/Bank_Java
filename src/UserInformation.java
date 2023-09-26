import java.io.*;
import java.util.Scanner;


public class UserInformation{

    // class globals
    private String username;
    private String password;
    public static Scanner SCAN = new Scanner(System.in);

    // default constructor
    public UserInformation(){}

    // LogIn constructor
    public UserInformation(String username, String password){
        this.username = username;
        this.password = password;
    }

    // Methods
    public void LogIn(){

        final String LOGIN_FILE_PATH = "LogIn.txt";
        int count = 0;

        File logInFile = new File(LOGIN_FILE_PATH);
        try {
            if(!logInFile.exists()){
                // make a new login file if file doesn't already exist
                BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_FILE_PATH));
                writer.write("USERNAME: " + this.username);
                writer.write("PASSWORD" + this.password);
                writer.close();
            } else {
                // check if user already exists in file
                try{
                    String contents;
                    BufferedReader reader = new BufferedReader(new FileReader("LogIn.txt"));
                    // check if username and password is correct
                    while((contents=reader.readLine()) != null){
                        // if username is correct -> count = 1
                        if(contents.trim().contains(this.username)){
                            count++;
                        }
                        // if password is correct count = 2
                        if(contents.trim().contains(this.password)){
                            count++;
                        }
                        // if count does not equal two then something went wrong
                        if(count!=2){
                            System.out.println("Username or Password is incorrect...");
                        } else {
                            AccountActions();
                        }
                    }
                } catch(IOException e){
                    System.out.print("[ERROR] ");
                    e.printStackTrace();
                }
            }
        }catch(IOException e){
            System.out.print("[ERROR] ");
            e.printStackTrace();
        }
    }

    public static void AccountActions(){
        String action;
        int amount;

        System.out.println("""
                Deposit: A
                Withdrawal: B
                Update Banking Information: C
                """);
        action = SCAN.nextLine().toLowerCase();

        // switch statement for input validation
        switch (action) {
            case "a" -> {
                System.out.println("Amount to Deposit: ");
                amount = SCAN.nextInt();
                BankInformation bankInformation = new BankInformation(amount);
                bankInformation.Deposit();
            }
            case "b" -> {
                System.out.println("Amount to Withdrawal: ");
                amount = SCAN.nextInt();
                BankInformation bankInformation = new BankInformation(amount);
                bankInformation.Withdrawal();
            }
            case "c" -> {
                UserInformation_UpdateBankInformation();
            }
            default -> {
                System.out.println("Please enter a valid response...");
                AccountActions();
            }
        }
    }

    public static void UserInformation_UpdateBankInformation(){
        String bankID;
        String userID;
        String routingNumber;
        String accountNumber;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter bank ID: ");
        bankID = scan.nextLine();
        System.out.println("Enter user ID: ");
        userID = scan.nextLine();
        System.out.println("Enter Routing Number: ");
        routingNumber = scan.nextLine();
        System.out.println("Enter Account Number: ");
        accountNumber = scan.nextLine();

        BankInformation bankinfo = new BankInformation(bankID, userID,
                routingNumber, accountNumber);

        bankinfo.BankInformation_UpdateBankInformation();
    }


}
