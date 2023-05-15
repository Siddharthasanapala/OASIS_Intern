import java.util.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
class ATM_Interface{
    public static int Balance=0,PIN;
    public static boolean Registered = false; 
    public static String name,AccountNo;
    public static ArrayList<String> Tranhistory = new ArrayList<String>();
    public static void ExitDisplay(){
        System.out.println("You can take your card now. \nThankyou for using our ATM");
        System.out.println("Have A nice day!");
        System.exit(0);
    }
    public static void Homepage(){
        Scanner input=new Scanner(System.in);
        System.out.println("1 -> Register \n2 -> Change Pin");
        System.out.println("3 -> Withdraw \n4 -> Deposit");
        System.out.println("5 -> Transfer Money \n6 -> Transaction History/Mini statment");
        System.out.println("7 -> Check Balance \n8 -> Exit");
        System.out.println("Enter Your choice :");
        int choice = input.nextInt();
        switch (choice){
            case 1:
                Register_ChangePin.RegisterACC();
            case 2:
                if(Registered){
                    Register_ChangePin.ChangePin();
                    }
                else{
                    System.out.println("Please get registered before accessing \n");
                    Homepage();
                    }
            case 3:
                if(Registered){
                    Transactions.Withdraw();
                    }
                else{
                    System.out.println("Please get registered before accessing \n");
                    Homepage();
                    }
            case 4:
                if(Registered){
                    Transactions.Deposit();
                    }
                else{
                    System.out.println("Please get registered before accessing \n");
                    Homepage();
                    }
            case 5:
                if(Registered){
                    Transactions.Transfer();
                    }
                else{
                    System.out.println("Please get registered before accessing \n");
                    Homepage();
                    }
            case 6:
                if(Registered){
                    Statment.TransactionHistory();
                    }
                else{
                    System.out.println("Please get registered before accessing \n");
                    Homepage();
                    }
            case 7:
                if(Registered){
                    Statment.BalanceEnquery();
                    }
                else{
                    System.out.println("Please get registered before accessing \n");
                    Homepage();
                    }
            case 8:
                System.out.println("<<<---  EXITING  --->>>");
                ExitDisplay();
            default :
                System.out.println("Enter A valid Input \n");
                Homepage();
        }

    }
    public static void main(String[] args){
        System.out.println("Welcome to our ATM");
        System.out.println("Please don't remove your card till the transaction is Completed.");
        System.out.println("Please get registered before accessing  \n");
        Homepage();
    }
}
class Statment{
    static void BalanceEnquery(){
        System.out.println("Your Current Balance is "+ATM_Interface.Balance);
        System.out.println("-----------------------------------");
        ATM_Interface.Homepage();
    }
    static void TransactionHistory(){
        System.out.println("Transaction History/Mini Statment :");
        System.out.println("-----------------------------------");
        System.out.println("Amount \t     Transaction");
        int history=0;
        if(ATM_Interface.Tranhistory.size() != 0){
            for(int i=0;i<(ATM_Interface.Tranhistory.size()/2);i++){
                for(int j=0;j<2;j++){
                    System.out.print(ATM_Interface.Tranhistory.get(history)+"\t");
                    history++;
                }
                System.out.println("");
            }
        }
        else{
            System.out.println("No Transactions Recorded Yet \n");
        }
        System.out.println("--------------------------------");
        ATM_Interface.Homepage();
    }
}

class Register_ChangePin{
    static void RegisterACC(){
        Scanner INP=new Scanner(System.in);
        System.out.println("Please Enter your Account Number :");
        ATM_Interface.AccountNo = INP.nextLine();
        System.out.println("Please Enter your Mobile Number :");
        String MobileNo = INP.nextLine();
        System.out.println("Please Enter OTP sent to your Mobile Number :");
        int OTP = INP.nextInt();
        System.out.println("Enter your 4 digit PIN :");
        ATM_Interface.PIN=INP.nextInt();
        System.out.println("Conform Your 4 digit pin :");
        ATM_Interface.PIN=INP.nextInt();
        System.out.println("Your Registration is Successful!. Login to Access your Account");
        ATM_Interface.Registered = true;
        System.out.println("-----------------------------------");
        ATM_Interface.Homepage();
    }
    static void ChangePin(){
        Scanner Change=new Scanner(System.in);
        System.out.println("Enter your 4 digit PIN :");
        int pin=Change.nextInt();
        if(pin == ATM_Interface.PIN){
            System.out.println("Enter your New 4 digit PIN :");
            ATM_Interface.PIN=Change.nextInt();
            System.out.println("Conform Your 4 digit pin :");
            ATM_Interface.PIN=Change.nextInt();
            System.out.println("Your 4 digit pin has been changed successfully!");
        }
        else{
            System.out.println("Wrong pin Entered! Try again later.");
        }
        System.out.println("-----------------------------------");
        ATM_Interface.Homepage();
    }

}
class Transactions{
    static void Withdraw(){
        Scanner s1=new Scanner(System.in);
        System.out.println("Enter the Amount to Withdraw :");
        int amt=s1.nextInt();
        System.out.println("Enter your 4 digit PIN :");
        int pin1=s1.nextInt();
        if(pin1 == ATM_Interface.PIN){
            if(amt > ATM_Interface.Balance || ATM_Interface.Balance == 0){
                System.out.println("No Sufficient Balance to do Transaction \nTransaction Failed");
            }
            else{
                ATM_Interface.Balance -= amt;
                ATM_Interface.Tranhistory.add(Integer.toString(amt));
                ATM_Interface.Tranhistory.add("Withdraw");
                System.out.println("Your Transaction is Successful");
            }
        }
        else{
            System.out.println("Wrong pin Entered! \nTransaction Failed");
        }
        System.out.println("-----------------------------------");
        ATM_Interface.Homepage();
    }
    static void Deposit(){
        Scanner s2=new Scanner(System.in);
        System.out.println("Enter the Amount to Deposit :");
        int amt1=s2.nextInt();
        ATM_Interface.Balance += amt1;
        ATM_Interface.Tranhistory.add(Integer.toString(amt1));
        ATM_Interface.Tranhistory.add("Deposit");
        System.out.println("Your Transaction is Successful");
        System.out.println("--------------------------------");
        ATM_Interface.Homepage();
    }
    static void Transfer(){
        Scanner s3=new Scanner(System.in);
        System.out.println("Please Enter Account number you want to transfer :");
        String ACCno = s3.nextLine();
        System.out.println("Please Enter IFSC code of Bank account :");
        String IFSC = s3.nextLine();
        System.out.println("Enter the Amount to Transfer :");
        int amt3=s3.nextInt();
        System.out.println("Enter your 4 digit PIN :");
        int pin2=s3.nextInt();
        if(pin2 == ATM_Interface.PIN){
            if(amt3 > ATM_Interface.Balance){
                System.out.println("No Sufficient Balance to do Transaction \nTransaction Failed");
            }
            else{
                ATM_Interface.Balance -= amt3;
                ATM_Interface.Tranhistory.add(Integer.toString(amt3));
                ATM_Interface.Tranhistory.add("Transfer");
                System.out.println("Your Transaction is Successful");
            }
        }
        else{
            System.out.println("Wrong pin Entered! \nTransaction Failed");
        }
        System.out.println("-----------------------------------");
        ATM_Interface.Homepage();
    }
}


