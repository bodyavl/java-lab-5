package org.lab;

import org.lab.exceptions.AccountNotFoundException;
import org.lab.exceptions.InsufficientFundsException;
import org.lab.exceptions.NegativeAmountException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        try {
            System.out.println("Scenario 1: Attempt to withdraw a negative amount");
            bank.createAccount("John Doe", 1000);
            BankAccount johnAccount = bank.findAccount(1);
            johnAccount.withdraw(-200);
        } catch (NegativeAmountException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        try {
            System.out.println("\nScenario 2: Attempt to withdraw more than the balance");
            bank.createAccount("Jane Doe", 500);
            BankAccount janeAccount = bank.findAccount(2);
            janeAccount.withdraw(700);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        try {
            System.out.println("\nScenario 3: Attempt to find a non-existent account");
            bank.createAccount("Jim Doe", 200);
            bank.findAccount(4);
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        try {
            System.out.println("\nScenario 4: Transfer money between accounts");
            bank.createAccount("Jim Doe", 200);
            BankAccount jimAccount = bank.findAccount(4);
            bank.createAccount("Jessie Doe", 300);
            BankAccount jessieAccount = bank.findAccount(5);
            bank.transferMoney(4, 5, 150);
            System.out.println("New balance for Jim Doe: " + jimAccount.getBalance());
            System.out.println("New balance for Jessie Doe: " + jessieAccount.getBalance());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

}