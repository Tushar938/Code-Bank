import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

//Main class

public class Bank {

    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        start();

    }

    static void start(){
        
        Actions.delay(1000);
        System.out.println("Setting up bank....");
        home_Page();

    }

    static void home_Page(){

        System.out.println();
        System.out.println("=============================================================================");
        System.out.println();
        Actions.delay(500);
        System.out.println("What do you want to do?(Please enter the serial number)");
        Actions.delay(500);
        System.out.println("1. Create a new account");
        Actions.delay(500);
        System.out.println("2. Check bank balance");
        Actions.delay(500);
        System.out.println("3. Withdraw");
        Actions.delay(500);
        System.out.println("4. Deposit");
        Actions.delay(500);
        System.out.println("5. Account Information");
        Actions.delay(500);
        System.out.println("6. Transfer money to another account");
        Actions.delay(500);
        System.out.println("7. Delete account");
        Actions.delay(500);
        System.out.println("8. Check account status");
        Actions.delay(500);
        System.out.println("9. Unfroze account");
        System.out.println();
        System.out.println("=============================================================================");
        System.out.println();

        //operation to perform

        int operation = sc.nextInt();

        Actions.operations(operation);

        

    }

}

//Actions Class - all operations and functions are in this class

class Actions{

    static Scanner sc = new Scanner(System.in);

    //function for the operation to be performed

    static void operations(int operation) {

        int confirm = Actions.confirmation();

        //confirmation

        switch (confirm) {
            case 1:

                //if user confirmed

                switch(operation)
                {

                case 1:

                    create_account();
                   break;

                case 2:

                    check_bank_balance();
                    break;

                case 3:

                    withdraw();
                    break;

                case 4:

                    deposit();
                    break;

                case 5:

                    account_info();
                    break;

               case 6:

                    transfer_money();
                    break;

                case 7:

                    delete_account();
                    break;

                case 8:
                
                    Actions.check_account_status();
                    break;

                case 9:

                    Actions.unfroze_account();
                    break;

                default:

                    System.out.println("Please enter a valid input!!!!");
                    Bank.start();
                    break;

            }
                
                break;

            case 0:

                //if user didn't confirm and wants to go back

                Bank.home_Page();
                break;

            case -1:

                //user entered something invalid

                System.out.println("Sorry the value you entered is invalid!!!!");
                System.out.println("Please try again....");
                Bank.home_Page();
                break;

            default:

                //if something else happened 

                System.out.println("Sorry there has been an error!!!!");
                System.out.println("Please try again....");
                Bank.home_Page();
                break;

        }

    }

    //function to create a delay

    static void delay(int del){

        try {
            Thread.sleep(del);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //function for confirmation

    static int confirmation(){
        
        System.out.println("Please enter 1 for confirmation or press 0 to go back....");
        int num = sc.nextInt();
        int temp;

        switch(num){
            case 1:

                temp = 1;
                break;

            case 0:

                temp = 0;
                break;

            default:

                temp = -1;
                break;

        }

        return temp;

    }

    //function to check if phone number entered is valid

    static void contact_number_confirmation(String contact_number){

        //checking if the contact number is a 10 digit number or not

        if(contact_number.length()!=10){

            System.out.println("Sorry the contact nunmber you entered is not valid!!!!");
            System.out.println("Please try again....");
            create_account();

        }

        //checking if the number entered already exists or not

        else{

            if(sql.has_contact_number(contact_number)==true){

                System.out.println("Sorry the contact number you entered already exists in our database!!!!");
                System.out.println("Please try again....");
                create_account();

            }

        }

    }

    //function to confirm pin

    static void pin_confirmation(int pin){

        String str = Integer.toString(pin);

        //checking if the pin entered is 4 digit or not

        if(str.length()!=4){

            System.out.println("Sorry the pin you entered is not valid!!!!");
            System.out.println("Please try again....");
            create_account();

        }

        //confirming pin

        else{

            System.out.println("Please confirm your pin....");
            int temp = sc.nextInt();

            if(temp!=pin){

                System.out.println("Sorry both the pins don't match!!!!");
                System.out.println("Please try again....");
                create_account();

            }

        }

    }

    //function to create a new account

    static void create_account(){
        
        //asking for name
        
        System.out.println("Please enter your first name...");
        String first_name = sc.next();
        System.out.println("Please enter your last name...");
        String last_name = sc.next();
        String name = first_name+" "+last_name;

        //asking for contact number

        System.out.println("Please enter your 10 digit contact number...");
        String contact_number = sc.next();
        System.out.println("Verifying contact number....");
        delay(2000);
        contact_number_confirmation(contact_number);
        System.out.println("Contact number verified successfully....");
        delay(2000);

        //asking for pin

        System.out.println("Please enter a 4 digit pin for your bank account");
        int pin = sc.nextInt();
        pin_confirmation(pin);
        System.out.println("Creating pin....");
        delay(1000);
        System.out.println("Pin created successfuly....");

        //asking for payment

        delay(2000);
        System.out.println("Please pay 1100$ at the reception(1000$ for your balance and 100$ as a convenience fee)....");
        delay(1000);
        System.out.println("Press 1 if you have paid the amount....");
        int temp = sc.nextInt();
        if(temp != 1){

            System.out.println("Sorry invalid input!!!!");
            System.out.println("Please try again....");
            create_account();

        }

        delay(1000);
        System.out.println("Processing data....");
        delay(2000);

        if(sql.create_account(name, contact_number, pin, 1000) == true){

            //creating account

            System.out.println("Congratulations!! Your account has been created...");
            Bank.home_Page();

        }
        else{

            //can't create account due to some reasons

            System.out.println("Sorry there has been an error!!!!");
            System.out.println("Please try again....");
            create_account();

        }

    }
    
    //function to chech bank balance

    static void check_bank_balance(){

        //asking for acocunt number

        System.out.println("Please enter your account number....");
        int acc_num = sc.nextInt();
        
        //asking if the acocunt number exists
        
        if(sql.has_account_number(acc_num) == true){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num, pin) == true){

                int balance = sql.bank_balance(acc_num);
                delay(2000);
                sql.maintainance(acc_num);
                System.out.println("You currently have "+Integer.toString(balance)+"$ in you account");
                Bank.home_Page();

            }
            else{

                if(count<3){

                    //if the pin entered is incorrect

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    check_bank_balance();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }

        }
        else{

            //if the account does not exist

            System.out.println("Sorry the account number your entered does not exist in our database!!!!");
            System.out.println("Please try again....");
            check_bank_balance();

        }

    }

    //function to withdraw money

    static void withdraw(){

        //asking for acocunt number

        System.out.println("Please enter your account number....");
        int acc_num = sc.nextInt();
        
        //asking if the acocunt number exists
        
        if(sql.has_account_number(acc_num) == true){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num, pin) == true){

                //if the pin entered is correct

                int balance = sql.bank_balance(acc_num);
                delay(2000);

                //adking the amount to be withdrawn

                System.out.println("How much money do you want to withdraw: ");
                int amount = sc.nextInt();

                if(balance >= amount+200){

                    //if the balance is sufficient

                    balance -= amount;

                    //checking is the account is frozen or not

                    if(sql.check_account_status(acc_num).equalsIgnoreCase("not frozen")==false){

                        System.out.println("Sorry this account is currently frozen....");
                        System.out.println("Please unfreeze you account....");
                        Bank.home_Page();

                    }

                    if(sql.update_balance(balance, acc_num) == true){

                        System.out.println("Money withdrawed successfully....");
                        sql.maintainance(acc_num);
                        System.out.println("Current Balance: "+Integer.toString(balance));

                    }

                    else{

                        System.out.println("Sorry there has been an error!!!!");
                        System.out.println("Please try again....");
                        withdraw();

                    }

                }
                else{

                    //if the balance is insufficient

                    System.out.println("Sorry you account does not have sufficient amount to withdraw!!!!");
                    System.out.println("Your current balance is: "+Integer.toString(balance));

                }
                Bank.home_Page();

            }
            else{

                //if the pin entered is incorrect

                if(count<3){

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    withdraw();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }

        }

        else{

            //if the account does not exist

            System.out.println("Sorry the account number your entered does not exist in our database!!!!");
            System.out.println("Please try again....");
            withdraw();

        }

    }

    //function to deposit money

    static void deposit(){

        //asking for acocunt number

        System.out.println("Please enter your account number....");
        int acc_num = sc.nextInt();
        
        //asking if the acocunt number exists
        
        if(sql.has_account_number(acc_num) == true){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num, pin) == true){

                //if the pin entered is correct

                int balance = sql.bank_balance(acc_num);
                delay(2000);

                //adking the amount to be withdrawn

                System.out.println("How much money do you want to deposit: ");
                int amount = sc.nextInt();

                balance += amount;

                //checking is the account is frozen or not

                if(sql.check_account_status(acc_num).equalsIgnoreCase("not frozen")==false){

                        System.out.println("Sorry this account is currently frozen....");
                        System.out.println("Please unfreeze you account....");
                        Bank.home_Page();

                }

                if(sql.update_balance(balance, acc_num) == true){

                    System.out.println("Money deposited successfully....");
                    sql.maintainance(acc_num);
                    System.out.println("Current Balance: "+Integer.toString(balance));

                }

                else{

                    System.out.println("Sorry there has been an error!!!!");
                    System.out.println("Please try again....");
                    deposit();

                }

                Bank.home_Page();

            }
            else{

                //if the pin entered is incorrect

                if(count<3){

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    deposit();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }

        }
        else{

            //if the account does not exist

            System.out.println("Sorry the account number your entered does not exist in our database!!!!");
            System.out.println("Please try again....");
            deposit();

        }

    }

    //function to get account info

    static void account_info(){

        //asking for acocunt number

        System.out.println("Please enter your account number....");
        int acc_num = sc.nextInt();
        
        //asking if the acocunt number exists
        
        if(sql.has_account_number(acc_num) == true){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num, pin) == true){

                //if the pin entered is correct

                //checking is the account is frozen or not

                if(sql.check_account_status(acc_num).equalsIgnoreCase("not frozen")==false){

                        System.out.println("Sorry this account is currently frozen....");
                        System.out.println("Please unfreeze you account....");
                        Bank.home_Page();

                }

                sql.maintainance(acc_num);
                sql.account_info(acc_num);

                Bank.home_Page();    
            
            }
            else{

                //if the pin entered is incorrect

                if(count<3){

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    account_info();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }

        }     
        else{

            //if the account does not exist

            System.out.println("Sorry the account number your entered does not exist in our database!!!!");
            System.out.println("Please try again....");
            account_info();

        }  

    }

    //function to transfer money

    static void transfer_money(){

        //asking for acocunt number

        System.out.println("Please enter your account number....");
        int acc_num1 = sc.nextInt();
        System.out.println("Please enter the recipent's account number....");
        int acc_num2 = sc.nextInt();
        
        //asking if the acocunt number exists
        
        if(sql.has_account_number(acc_num1) == true && sql.has_account_number(acc_num2)){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num1, pin) == true){

                //if the pin entered is correct

                int balance1 = sql.bank_balance(acc_num1);
                int balance2 = sql.bank_balance(acc_num2);
                delay(2000);

                //adking the amount to be withdrawn

                System.out.println("How much money do you want to transfer: ");
                int amount = sc.nextInt();

                if(balance1 >= amount+200){

                    //if the balance is sufficient

                    balance1 = balance1 - amount;
                    balance2 = balance2 + amount;

                    //checking is any of the account is frozen or not

                    if(sql.check_account_status(acc_num1).equalsIgnoreCase("not frozen")==false || sql.check_account_status(acc_num2).equalsIgnoreCase("not frozen")==false){

                        System.out.println("Sorry this account is currently frozen....");
                        System.out.println("Please unfreeze you account....");
                        Bank.home_Page();

                    }

                    if(sql.update_balance(balance1, acc_num1) == true && sql.update_balance(balance2, acc_num2)==true){

                        System.out.println("Money transfered successfully....");
                        sql.maintainance(acc_num1);
                        System.out.println("Current Balance: "+Integer.toString(balance1));

                    }

                    else{

                        System.out.println("Sorry there has been an error!!!!");
                        System.out.println("Please try again....");
                        transfer_money();

                    }

                }
                else{

                    //if the balance is insufficient

                    System.out.println("Sorry you account does not have sufficient amount to transfer the amount!!!!");
                    System.out.println("Your current balance is: "+Integer.toString(balance1));

                }
                Bank.home_Page();

            }
            else{

                //if the pin entered is incorrect

                if(count<3){

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    transfer_money();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }

        }

        else{

            //if the account does not exist

            System.out.println("Sorry the account number your entered does not exist in our database!!!!");
            System.out.println("Please try again....");
            transfer_money();

        }

    }

    //function to delete an account

    static void delete_account(){

        //asking the account number

        System.out.println("Please enter your account number: ");
        int acc_num = sc.nextInt();
        if(sql.has_account_number(acc_num) == true){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num, pin) == true){

                if(sql.delete_account(acc_num) == true){

                    System.out.println("Account Deleted Successfully....");
                    Bank.home_Page();

                }
                else{

                    System.out.println("Sorry there has been an error!!!!");
                    System.out.println("Please try again....");
                    delete_account();

                }

            }
            else{

                //if the pin entered is incorrect

                if(count<3){

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    delete_account();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }


        }
        else{

            System.out.println("Sorry the account does not exists!!!!");
            System.out.println("Please try again....");
            delete_account();      

        }

    }

    static void check_account_status(){

        //asking the account number

        System.out.println("Please enter your account number: ");
        int acc_num = sc.nextInt();
        if(sql.has_account_number(acc_num) == true){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num, pin) == true){

                System.out.println("Account status: " + sql.check_account_status(acc_num));
                sql.maintainance(acc_num);
                Bank.home_Page();

            }
            else{

                //if the pin entered is incorrect

                if(count<3){

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    check_account_status();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }

        }
        else{

            System.out.println("Sorry the account does not exists!!!!");
            System.out.println("Please try again....");
            check_account_status();               

        }

    }

    static void unfroze_account(){

        //asking the account number

        System.out.println("Please enter your account number: ");
        int acc_num = sc.nextInt();
        if(sql.has_account_number(acc_num) == true){

            int count = 0;

            //asking for pin

            System.out.println("Please enter you pin....");
            int pin = sc.nextInt();

            //checking if the pin entered is correct

            if(sql.correct_pin(acc_num, pin) == true){

                String str = sql.check_account_status(acc_num);
                
                if(str == "Frozen"){

                    sql.unfroze_account(acc_num);
                    System.out.println("Your account is Unfrozen....");
                    sql.maintainance(acc_num);
                    Bank.home_Page();

                }
                else{

                    System.out.println("Your account is already Unfrozen");
                    System.out.println("Account status: " + str);
                    Bank.home_Page();

                }

            }
            else{

                //if the pin entered is incorrect

                if(count<3){

                    count++;
                    System.out.println("Sorry the pin is incorrect!!!!");
                    System.out.println("Please try again....");
                    check_account_status();

                }
                else{

                    //if the pin is entered incorrectly for the 3rd time

                    System.out.println("Sorry you entered incorrect pin 3 times!!!!");
                    System.out.println("Please try again....");
                    Bank.home_Page();

                }

            }

        }
        else{

            System.out.println("Sorry the account does not exists!!!!");
            System.out.println("Please try again....");
            check_account_status();               

        }

    }

}

// SQL class - all backend connections are in this class

class sql{

    static String url = "jdbc:mysql://localhost:3306/Bank";
    static String username = "Tushar";
    static String password = "Tushar@123";

    //function to return total records

    static int count() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM accounts;";

            ResultSet resultSet = statement.executeQuery(query);

            int count=0;

            while(resultSet.next()){
                
                count++;

            }

            connection.close();

            return count;

        } 

        catch (SQLException e) 
        {

            e.printStackTrace();

        }

        catch(ClassNotFoundException e){

            e.printStackTrace();

        }

        return -1;

    }

    //function to check if the contact number already exists

    static boolean has_contact_number(String contact_number){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM ACCOUNTS WHERE CONTACT = " + contact_number + ";";
            int count=0;

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                count++;

            }

            connection.close();

            return count==0 ? false : true;

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return false;

    }

    //function to check if the account number already exists

    static boolean has_account_number(int account_number){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM ACCOUNTS WHERE Account_Number = " + Integer.toString(account_number) + ";";
            int count=0;

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                count++;

            }

            connection.close();

            return count==0 ? false : true;

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return false;

    }

    //function to check if the pin entered is correct or not

    static boolean correct_pin(int contact_number, int pin){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM ACCOUNTS WHERE Account_Number = " + Integer.toString(contact_number) + ";";
            boolean bool = false;

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                if(resultSet.getInt("pin") == pin){

                    bool = true;

                }

            }

            connection.close();

            return bool;

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return false;

    }

    //function to create account

    static boolean create_account(String name, String contact, int pin, int balance){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM accounts ORDER BY Account_Number DESC;";
            int acc_num=1;

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                acc_num = resultSet.getInt("Account_Number");

                acc_num++;

                break;

            }

            query = "INSERT INTO accounts VALUES ("+Integer.toString(acc_num)+",'"+name+"',"+contact+","+Integer.toString(pin)+","+Integer.toString(balance)+","+Boolean.toString(false)+");";

            statement.executeUpdate(query);

            connection.close();

            return true;

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return false;

    }

    //function to check bank balance

    static int bank_balance(int account_number){

        int num=0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM accounts WHERE Account_Number = '"+Integer.toString(account_number)+"';";
            num = 0;

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                num = resultSet.getInt("balance");
                break;

            }

            connection.close();

            return num;

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return num;

    }

    //function to update balance of an account

    static boolean update_balance(int balance, int account_number){

        boolean bool = false;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "UPDATE accounts SET balance = "+Integer.toString(balance)+" WHERE Account_Number = "+Integer.toString(account_number)+";";

            statement.executeUpdate(query);

            connection.close();

            return true;

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return bool;

    }

    //function to delete an account

    static boolean delete_account(int account_number){

        boolean bool = false;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "DELETE FROM accounts WHERE Account_Number = " + Integer. toString(account_number) + ";";

            statement.executeUpdate(query);

            connection.close();

            bool = true;

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return bool;

    }

    //function to get account information

    static void account_info(int account_number){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM accounts WHERE Account_Number = '"+Integer.toString(account_number)+"';";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                String name = resultSet.getString("Name");
                String contact = resultSet.getString("contact");
                int balance = resultSet.getInt("balance");
                boolean frozen = resultSet.getBoolean("frozen");
                String status = frozen == true ? "frozen" : "not frozen";

                System.out.println("Account Number: " + Integer.toString(account_number));
                System.out.println("Name: " + name);
                System.out.println("Contact Number: " + contact);
                System.out.println("Account Balance: " + Integer.toString(balance));
                System.out.println("Account status: " + status);
                break;

            }

            connection.close();

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    //function to check account status

    static String check_account_status(int account_number){

        String str = "Not Frozen";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM accounts WHERE Account_Number = " + Integer.toString(account_number) + ";";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                str = resultSet.getBoolean("frozen") == true ? "Frozen" : "Not Frozen";

            }

            connection.close();

        } 

        catch (SQLException e) 
        {

            e.printStackTrace();

        }
        catch(ClassNotFoundException e){

            e.printStackTrace();

        }

        return str;

    }

    //function to unfreeze acocunt

    static void unfroze_account(int account_number){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "UPDATE accounts SET frozen = 0 WHERE Account_Number = " + Integer.toString(account_number) + ";";

            statement.executeUpdate(query);

            connection.close();

        } 

        catch (SQLException e) 
        {

            e.printStackTrace();

        }
        catch(ClassNotFoundException e){

            e.printStackTrace();

        }

    }

    static void maintainance(int account_number){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM accounts WHERE Account_Number = " + Integer.toString(account_number);

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                int balance = resultSet.getInt("balance");

                if(balance < 200){

                    update_balance(balance-100, account_number);
                    System.out.println("A sum of 100$ has been taken form your account fornot being able to maintain your account....");

                }

            }

            connection.close();

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }

}
