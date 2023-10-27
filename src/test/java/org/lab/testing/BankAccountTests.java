package org.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lab.BankAccount;
import org.lab.exceptions.InsufficientFundsException;
import org.lab.exceptions.NegativeAmountException;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTests {
    BankAccount account;

    @BeforeEach
    void init () {
        account = new BankAccount(1, "test", 0);
    }


    @Test
    public void testDebitWithPositiveAmount() throws NegativeAmountException {

        double amount = 5;
        account.deposit(amount);
        assertEquals(5.0, account.getBalance());
    }

    @Test
    public void testDebitWithNegativeAmount() {
        double amount = -5;
        assertThrows(NegativeAmountException.class, () -> account.deposit(amount));
    }

    @Test
    public void testDebitWithZeroAmount() throws NegativeAmountException {
        double amount = 0;
        account.deposit(amount);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testWithdrawWithPositiveAmount() throws InsufficientFundsException, NegativeAmountException {
        double amount = 5;
        account.deposit(amount);
        account.withdraw(amount);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testWithdrawWithInsufficientFunds() {
        double amount = 5;
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(amount));
    }

    @Test
    public void testWithdrawWithNegativeAmount() {
        double amount = -5;
        assertThrows(NegativeAmountException.class, () -> account.withdraw(amount));
    }


}
