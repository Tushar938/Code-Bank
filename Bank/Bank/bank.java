import java.util.*;

public class bank {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> name = new ArrayList<String>();
    static ArrayList<String> contact_number = new ArrayList<String>();
    static ArrayList<String> aadhar_number = new ArrayList<String>();
    static ArrayList<Integer> account_number = new ArrayList<Integer>();
    static ArrayList<Integer> account_balance = new ArrayList<Integer>();
    static int acc_num = 0;

    public static void start(){

        System.out.println("What do you want to do?(Please enter the serial number)");
        System.out.println("1. Create a new account");
        System.out.println("2. Check bank balance");
        System.out.println("3. Withdraw");
        System.out.println("4. Deposit");
        System.out.println("5. Account Information");
        System.out.println("6. Transfer money to another account");
        System.out.println("7. Delete account");
        int input = sc.nextInt();
        operation(input);

    }

    public static void operation(int input){

        switch(input){

            case 1:
                CreateAccount();
                break;
            case 2:
                CheckBankBalance();
                break;
            case 3:
                Withdraw();
                break;
            case 4:
                Deposit();
                break;
            case 5:
                AccountInfo();
                break;
            case 6:
                TransferMoney();
                break;
            case 7:
                DeleteAccount();
                break;
            default:
                System.out.println("Please enter a valid input!!!!");
                start();
                break;

        }

    }

    public static void maintainance(int i) {
                
        if(account_balance.get(i)<500){
            account_balance.set(i,account_balance.get(i)-100);
            System.out.println("A maintainance of 100$ has been charged from the account with account number: "+account_number.get(i)+"for not being able to maintain 500$");
        }

    }

    public static void CreateAccount(){

        System.out.println("Enter 1 to go ahead or 0 to go to start menu...");
        int confirm = sc.nextInt();

        switch(confirm){

            case 1:
                System.out.println("So lets get started...");
                System.out.println("Please enter your first name...");
                String first_name = sc.next();
                System.out.println("Please enter your last name...");
                String last_name = sc.next();
                String name_ = first_name+" "+last_name;
                System.out.println("Please enter your contact number...");
                String contact_number_ = sc.next();
                System.out.println("Please enter your Aadhaar number...");
                String aadhar_number_ = sc.next();
                System.out.println("Congratulations!! Your account has been created...");
                acc_num++;
                name.add(name_);
                contact_number.add(contact_number_);
                aadhar_number.add(aadhar_number_);
                account_number.add(acc_num);
                account_balance.add(1000);
                System.out.println("Your account number is "+acc_num);
                System.out.println("Your account balance is "+account_balance.get(acc_num-1)+"$");
                confirm=0;
                start();
                break;
            case 0:
                start();
                break;
            default:
                System.out.println("Please enter a valid confirmation!!!!");
                confirm=0;
                CreateAccount();
                break;

        }

    }

    public static void CheckBankBalance() {

        System.out.println("Enter 1 to go ahead or 0 to go to start menu...");
        int confirm = sc.nextInt();

        switch(confirm){

            case 1:
                System.out.println("Please enter your account number...");
                int account_num = sc.nextInt();
                if(account_num>acc_num){
                    System.out.println("Sorry the account does not exist!!!!");
                    start();
                    break;
                }
                int temp=0;
                for(int i=0; i<=acc_num; i++){
                    if(account_number.get(i) == account_num){
                        temp++;
                        System.out.println("Your account currently has " + account_balance.get(i)+"$");
                        break;
                    }
                }
                if(temp==0){
                    System.out.println("Sorry the account number is invalid!!!!");
                    start();
                }
                start();
                break;
            case 0:
                start();
                break;
            default:
                System.out.println("Please enter a valid confirmation!!!!");
                CheckBankBalance();
                break;

        }

    }

    public static void Withdraw() {

        System.out.println("Enter 1 to go ahead or 0 to go to start menu...");
        int confirm = sc.nextInt();

        switch(confirm){

            case 1:
                System.out.println("Please enter the account number...");
                int account_num = sc.nextInt();
                if(account_num>acc_num){
                    System.out.println("Sorry the account does not exist!!!!");
                    start();
                    break;
                }
                int temp=0;
                for(int i=0; i<=acc_num; i++){
                    if(account_number.get(i)==account_num){
                        temp++;
                        System.out.println("Please enter the amount to withdraw...");
                        int amount = sc.nextInt();
                        if(account_balance.get(i)+100>=amount){
                            account_balance.set(i, account_balance.get(i)-amount);
                            maintainance(i);
                            System.out.println("Your new account balance: " + account_balance.get(i)+"$");
                            break;
                        }
                        else{
                            System.out.println("Insufficient funds!!!!!");
                            start();
                            break;
                        }
                    }
                }
                if(temp==0){
                    System.out.println("Sorry the account number is invalid!!!!");
                }
                start();
                break;
            case 0:
                start();
                break;
            default:
                System.out.println("Please enter a valid confirmation!!!!");
                Withdraw();
                break;

        }

    }

    public static void Deposit() {

        System.out.println("Enter 1 to go ahead or 0 to go to start menu...");
        int confirm = sc.nextInt();

        switch(confirm){

            case 1:
                System.out.println("Please enter the account number...");
                int account_num = sc.nextInt();
                if(account_num>acc_num){
                    System.out.println("Sorry the account does not exist!!!!");
                    start();
                    break;
                }
                int temp=0;
                for(int i=0; i<acc_num; i++){
                    if(account_number.get(i)==account_num){
                        temp++;
                        System.out.println("Please enter the amount to deposit...");
                        int amount = sc.nextInt();
                        account_balance.set(i, account_balance.get(i)+amount);
                        maintainance(i);
                        System.out.println("Your new account balance: " + account_balance.get(i)+"$");
                        break;
                    }
                }
                if(temp==0){
                    System.out.println("Sorry the account number is invalid!!!!");
                }
                start();
                break;
            case 0:
                start();
                break;
            default:
                System.out.println("Please enter a valid confirmation!!!!");
                Deposit();
                break;
            
        }

    }

    public static void AccountInfo() {

        System.out.println("Enter 1 to go ahead or 0 to go to start menu...");
        int confirm = sc.nextInt();

        switch(confirm){

            case 1:
                System.out.println("Please enter your account number:");
                int account_num = sc.nextInt();
                int temp=0;
                if(account_num>acc_num){
                    System.out.println("Sorry the account does not exist!!!!");
                    start();
                    break;
                }
                for(int i=0; i<=acc_num; i++){
                    if(account_number.get(i)==account_num){
                        temp++;
                        System.out.println("1. Account number: " + account_number.get(i));
                        System.out.println("2. Account holder name: " + name.get(i));
                        System.out.println("3. Account holder contact number: " + contact_number.get(i));
                        System.out.println("4. Account holder aadhar number: " + aadhar_number.get(i));
                        System.out.println("5. Account balance: " + account_balance.get(i)+"$");
                        break;
                    }
                }
                if(temp==0){
                    System.out.println("Sorry the account does not exist!!!!");
                }
                start();
                break;
            case 0:
                start();
                break;
            default:
                System.out.println("Please enter a valid confirmation!!!!");
                AccountInfo();
                break;

        }

    }

    public static void TransferMoney() {

        System.out.println("Enter 1 to go ahead or 0 to go to start menu...");
        int confirm = sc.nextInt();

        switch(confirm){

            case 1:
                System.out.println("Please enter your account number...");
                int acccount_num1 = sc.nextInt();
                int temp=0;
                for(int i=0; i<acc_num; i++){
                    if(account_number.get(i)==acccount_num1){
                        temp++;
                        System.out.println("Please enter the account number where you want to transfer the money...");
                        int acccount_num2 = sc.nextInt();
                        for(int j=0; j<acc_num; j++){
                            if(account_number.get(i)==account_number.get(j)){
                                continue;
                            }
                            if(account_number.get(j)==acccount_num2){
                                temp++;
                                System.out.println("The amount to be transferred must be atlease 100$");
                                System.out.println("Please enter the amount to be transferred...");
                                int amount = sc.nextInt();
                                if(account_balance.get(i)>=amount && amount>=100){
                                    account_balance.set(i, account_balance.get(i)-amount);
                                    account_balance.set(j, account_balance.get(j)+amount);
                                    System.out.println("Money has been transferred successfully...");
                                    maintainance(i);
                                    maintainance(j);
                                    System.out.println("Your new account balance: " + account_balance.get(i)+"$");
                                    System.out.println("Receivers new account balance: " + account_balance.get(j)+"$");
                                    start();
                                }
                                else{
                                    System.out.println("Inssufficient funds...");
                                    start();
                                }
                            }
                        }
                    }
                }
                if(temp==0){
                    System.out.println("Sorry the account number you entered does not exist...");
                    System.out.println("Please re-enter your account number...");
                    TransferMoney();
                    break;
                }
                else if(temp==1){
                    System.out.println("Sorry the receivers number you entered does not exist...");
                    System.out.println("Please re-enter your receivers number...");
                    TransferMoney();
                    break;
                }
                break;
            case 0:
                start();
                break;
            default:
                System.out.println("Please enter a valid confirmation!!!!");
                TransferMoney();
                break;

        }

    }

    public static void DeleteAccount() {

        System.out.println("Enter 1 to go ahead or 0 to go to start menu...");
        int confirm = sc.nextInt();
        int temp=0;

        switch(confirm){

            case 1:
                System.out.println("Please enter your account number: ");
                int account_num = sc.nextInt();
                if(account_num>acc_num){
                    System.out.println("Sorry the account does not exist!!!!");
                    start();
                    break;
                }
                for(int i = 0; i < acc_num; i++){
                    if(account_number.get(i)==account_num){
                        temp++;
                        name.remove(i);
                        account_number.remove(i);
                        contact_number.remove(i);
                        aadhar_number.remove(i);
                        account_balance.remove(i);
                        acc_num--;
                        System.out.println("Your account has been deleted...");
                        break;
                    }
                }
                if(temp==0){
                    System.out.println("Sorry the account does not exist!!!!");
                }
                start();
                break;
            case 0:
                start();
                break;
            default:
                System.out.println("Please enter a valid confirmation!!!!");
                DeleteAccount();
                break;

        }
    }

    public static void main(String[] args) {
        start();
    }

}