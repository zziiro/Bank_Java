import java.io.*;
import java.util.Scanner;

public class BankInformation{
    // class globals
    private String bankID;
    private String userID;
    private String routingNumber;
    private String accountNumber;
    private int amount;
    private final String ACCOUNT_FILE_PATH = "Account.txt";
    private final String BANKINFO_FILE_PATH = "BankInfo.txt";

    // default constructor
    BankInformation(){}

    // bank information constructor
    BankInformation(String bankID, String userID,
                    String routingNumber, String accountNumber){
        this.bankID = bankID;
        this.userID = userID;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
    }

    // Deposit / Withdrawal Constructor
    BankInformation(int amount){
        this.amount = amount;
    }

    /* METHODS */

    public void Deposit(){
        // Add a deposit to account
        boolean hasNext;
        String updatedAmount_toString;
        String amountInAccount;
        int amountInAccount_int;
        int updatedAmount;
        try {
            File accountFile = new File(ACCOUNT_FILE_PATH);
            Scanner fileReader = new Scanner(accountFile);

            // check if file has a balance
            hasNext = fileReader.hasNext();
            if(!hasNext){
                // Set the depo amount to the string variable
                amountInAccount = Integer.toString(this.amount);
                UpdateAccountBalance(amountInAccount);
                return;
            }

            // read from file
            amountInAccount = fileReader.nextLine();

            // if the funds are 0
            if(amountInAccount.equals("0")){
                amountInAccount = Integer.toString(this.amount);
                UpdateAccountBalance(amountInAccount);
            } else {
                // if funds are not 0 -> calculate the new account balance
                amountInAccount_int = Integer.parseInt(amountInAccount);
                updatedAmount = amountInAccount_int + this.amount;
                updatedAmount_toString = Integer.toString(updatedAmount);

                // call method to update the account
                UpdateAccountBalance(updatedAmount_toString);
            }
        } catch(IOException e){
            System.out.println("[ERROR]");
            e.printStackTrace();
        }
    }

    public void Withdrawal(){
        boolean hasNext;
        String updatedBalance_toString;
        String String_currentBalance;
        int int_currentBalance;
        int updatedBalance;

        try {
            File accountFile = new File(ACCOUNT_FILE_PATH);
            Scanner fileReader = new Scanner(accountFile);

            // check if Account is empty
            hasNext = fileReader.hasNext();
            if(!hasNext){
                System.out.println("[ERROR] No Funds in Account");
                UserInformation.AccountActions();
            }

            // if the account is not empty
            String_currentBalance = fileReader.nextLine();

            // calculate new balance
            int_currentBalance = Integer.parseInt(String_currentBalance);
            updatedBalance = int_currentBalance - this.amount;
            updatedBalance_toString = Integer.toString(updatedBalance);

            // check if there is enough funds after calculation
            if(updatedBalance < 0){
                System.out.println("[ERROR] Not Enough Funds");
                UserInformation.AccountActions();
            }

            UpdateAccountBalance(updatedBalance_toString);

        } catch (IOException e) {
            System.out.println("[ERROR]");
        }
    }

    public void BankInformation_UpdateBankInformation(){
        // Bring in BufferedWriter
        try{
            BufferedWriter writer = new BufferedWriter (new FileWriter(BANKINFO_FILE_PATH));

            // write new information to file
            writer.write("Bank ID: " + this.bankID +
                    "\nUser ID: " + this.userID +
                    "\nRouting Number: " + this.routingNumber +
                    "\nAccountNumber: " + this.accountNumber);

            // close the file
            writer.close();

            // confirm the account has been updated
            System.out.println("Bank Information has been updated successfully");

        } catch(IOException e){
            System.out.println("[ERROR]");
            e.printStackTrace();
        }
    }

    // writing a new balance to the account file
    public void UpdateAccountBalance(String newBalance){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE_PATH));

            // write the new updated balance to the file
            writer.write(newBalance);

            // close the file
            writer.close();

            // confirm that the balance has been updated
            System.out.println("[Success] Balance has been updated..");
            System.out.println("New Account Balance: " + newBalance);
        } catch(IOException e){
            System.out.println("[ERROR]");
            e.printStackTrace();
        }
    }
}

