package a;


class BankAccount{
  int balance;
  public BankAccount() {
    balance = 0;
  }
  public synchronized void deposite(int deposite) {
    balance += deposite;
  }
  public synchronized void withdraw(int withdraw) {
    if(balance >= withdraw) {
      balance -=withdraw;
    }
    else {
      System.out.println("Insufficient balance!");
    }
   
  }
  public int getBalance() {
    return balance;
  }
}

class Depositor extends Thread{
  private BankAccount account;
  int depositeNumber;
  int deposite1;
  public Depositor(BankAccount account, int deposite1, int depositeNumber) {
    this.account = account;
    this.depositeNumber = depositeNumber;
    this.deposite1 = deposite1; 
  }
  public void run() {
    for(int i = 0; i <depositeNumber; i++) {
      account.deposite(deposite1);
      System.out.println("Deposite amount is: "+deposite1);
    }
  }
  
}
class Withdraw extends Thread{
  BankAccount account;
  int withdrawEverytime;
  int withdrawNumber;
  
  public Withdraw(BankAccount account, int withdrawEverytime, int withdrawNumber) {
    this.account = account;
    this.withdrawEverytime = withdrawEverytime;
    this.withdrawNumber = withdrawNumber;
  }
  public void run() {
    for(int i = 0; i < withdrawNumber; i++) {
      account.withdraw(withdrawEverytime);
      System.out.println("Withdraw amount as: "+withdrawEverytime);
    }
  }
  
}


public class BankAccountExcercise {

  public static void main(String[] args) {
    
    BankAccount bk = new BankAccount();
    
    Depositor dp = new Depositor(bk, 200, 5);
    Withdraw wd = new Withdraw(bk, 80, 4);
    
    
    dp.start();
    wd.start();
    try {
      dp.join();
      wd.join();
    }catch(Exception e) {
      System.out.println(e);
    }
    int finalAccount = bk.getBalance();
    System.out.println("Final account is: "+finalAccount);

  }

}