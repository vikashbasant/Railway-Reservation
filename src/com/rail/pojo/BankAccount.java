package com.rail.pojo;

public class BankAccount {
    private int accountNumber;

    // Default Balance is present in every account is:
    private int accountBalance = 100000;

    BankAccount(){
        // default constructor:
    }

    BankAccount(int accountNumber, int accountBalance){
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void deposit(int balance){
        this.accountBalance += balance;
    }

    public void withdrawl(int balance){
        if(this.accountBalance >= accountBalance){
            this.accountBalance -= balance;
        }
    }
}
