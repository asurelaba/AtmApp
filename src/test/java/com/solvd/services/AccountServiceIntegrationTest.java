package com.solvd.services;

import com.solvd.db.model.Account;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class AccountServiceIntegrationTest {

    private AccountService accountService;
    private Account testAccount;
    private UserService userService;

    @BeforeMethod
    public void setup() {
        accountService = new AccountService();
        userService = new UserService();

        testAccount = new Account();
        testAccount.setRoutingNumber(123456789);
        testAccount.setBalance(10000.0);
        testAccount.setUser(userService.getById(1));

        accountService.insert(testAccount);
    }

    @Test
    public void testInsertAccount() {
        Account newAccount = new Account();
        newAccount.setRoutingNumber(123456780);
        newAccount.setBalance(5000.0);
        newAccount.setUser(userService.getById(2));

        accountService.insert(newAccount);
        Account insertedAccount = accountService.getById(newAccount.getAccountId());

        assertEquals(newAccount, insertedAccount);
    }

    @Test
    public void testUpdateAccount() {
        testAccount.setBalance(20000.0);
        accountService.update(testAccount);

        Account updatedAccount = accountService.getById(testAccount.getAccountId());

        assertEquals(testAccount, updatedAccount);
    }

    @Test
    public void testDeleteAccount() {
        accountService.delete(testAccount.getAccountId());

        Account deletedAccount = accountService.getById(testAccount.getAccountId());

        assertNull(deletedAccount);
    }

    @Test
    public void testGetById() {
        Account retrievedAccount = accountService.getById(testAccount.getAccountId());

        assertEquals(testAccount, retrievedAccount);
    }

    @Test
    public void testGetAll() {
        List<Account> accounts = accountService.getAll();

        assertFalse(accounts.isEmpty());
        assertTrue(accounts.contains(testAccount));
    }

    @Test
    public void testGetAccountByUserId() {
        Account retrievedAccount = accountService.getAccountByUserId(testAccount.getUser().getUserId());

        assertEquals(testAccount, retrievedAccount);
    }

    @AfterMethod
    public void cleanup() {
        if (accountService.getById(testAccount.getAccountId()) != null) {
            accountService.delete(testAccount.getAccountId());
        }
    }
}

