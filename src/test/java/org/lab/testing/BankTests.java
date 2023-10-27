package org.lab.testing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.lab.Bank;
import org.lab.exceptions.AccountNotFoundException;
import org.lab.exceptions.InsufficientFundsException;
import org.lab.exceptions.NegativeAmountException;

import static org.junit.jupiter.api.Assertions.*;

public class BankTests {

    Bank bank;

    @BeforeEach
    void init() {
        bank = new Bank();
    }

    @Test
    public void testCreateAccount() {
        bank.createAccount("test", 0);
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    public void testCreateAccountWithInitialDeposit() {
        bank.createAccount("test", 100);
        assertEquals(100, bank.getAccounts().get(0).getBalance());
    }

    @Test
    public void testFindAccount() throws AccountNotFoundException {
        bank.createAccount("test", 0);
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    public void testFindAccountWithInvalidAccountNumber() {
        assertThrows(AccountNotFoundException.class, () -> bank.findAccount(1));
    }

    @Test
    public void testTransferMoney() throws AccountNotFoundException, NegativeAmountException, InsufficientFundsException {
        bank.createAccount("test1", 100);
        bank.createAccount("test2", 0);
        bank.transferMoney(1, 2, 50);
        assertEquals(50, bank.getAccounts().get(0).getBalance());
        assertEquals(50, bank.getAccounts().get(1).getBalance());
    }

    @Test
    public void testTransferMoneyWithInvalidFromAccountNumber() {
        bank.createAccount("test1", 100);
        bank.createAccount("test2", 0);
        assertThrows(AccountNotFoundException.class, () -> bank.transferMoney(3, 2, 50));
    }

    @Test
    public void testTransferMoneyWithInvalidToAccountNumber() {
        bank.createAccount("test1", 100);
        bank.createAccount("test2", 0);
        assertThrows(AccountNotFoundException.class, () -> bank.transferMoney(1, 3, 50));
    }

    @Test
    public void testTransferMoneyWithNegativeAmount() {
        bank.createAccount("test1", 100);
        bank.createAccount("test2", 0);
        assertThrows(NegativeAmountException.class, () -> bank.transferMoney(1, 2, -50));
    }

    @Test
    public void testTransferMoneyWithInsufficientFunds() {
        bank.createAccount("test1", 100);
        bank.createAccount("test2", 0);
        assertThrows(InsufficientFundsException.class, () -> bank.transferMoney(1, 2, 150));
    }

}
