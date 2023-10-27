package org.lab;
import org.lab.exceptions.AccountNotFoundException;
import org.lab.exceptions.InsufficientFundsException;
import org.lab.exceptions.NegativeAmountException;

import java.util.ArrayList;
import java.util.Collection;

public class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public void createAccount(String accountName, double initialDeposit) {
        int accountNumber = accounts.size() + 1;
        BankAccount account = new BankAccount(accountNumber, accountName, initialDeposit);
        accounts.add(account);
    }

    public BankAccount findAccount(int accountNumber) throws AccountNotFoundException {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException, NegativeAmountException {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
}

