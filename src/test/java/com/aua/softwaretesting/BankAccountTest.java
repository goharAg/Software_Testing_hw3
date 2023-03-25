package com.aua.softwaretesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    private BankAccount bankAccount;

    @BeforeEach
    void setUP(){
        bankAccount = new BankAccount("TestName", "TestID");
    }

    @Test
    @DisplayName("Should add to balance if amount is not 0")
    void depositTest(){
        bankAccount.bal = 10.0;
        double atm = 3.0;
        
        double expectedResult = 13.0;

        double result = bankAccount.deposit(atm);

        assertEquals(result, expectedResult);
        assertEquals(bankAccount.prevTrans, atm);

    }

    @Test
    @DisplayName("Should return balance unchanged if amount is 0")
    void depositTestAmountZero(){
        bankAccount.bal = 10.0;
        double atm = 0.0;
        
        double expectedResult = 10.0;

        double result = bankAccount.deposit(atm);

        assertEquals(result, expectedResult);

    }

    @Test
    @DisplayName("Should successfully withdraw money")
    void withdrawTestSuccess() throws Exception{
        bankAccount.bal = 10.0;
        bankAccount.prevTrans = 10.0;
        double atm = 2.0;
        
        double expectedResult = 8.0;

        double result = bankAccount.withdraw(atm);

        assertEquals(result, expectedResult);
        assertEquals(bankAccount.prevTrans,-2.0);

    }
    @Test
    @DisplayName("Should return balance if input is 0")
    void withdrawTestZero() throws Exception {
        bankAccount.bal = 10.0;
        double atm = 0.0;
        
        double expectedResult = 10.0;

        double result = bankAccount.withdraw(atm);

        assertEquals(result, expectedResult);

    }

    @Test
    @DisplayName("Should sthrow error when not enough funds to withdraw money")
    void withdrawTestError(){
        bankAccount.bal = 2.0;

        double atm = 10.0;
        String expectedMessage = "Bank balance insufficient";

        Exception exception = assertThrows(Exception.class, () -> {
            bankAccount.withdraw(atm);
        });

        assertEquals(exception.getMessage(), expectedMessage);


    }

    @Test
    @DisplayName("Should return previous transaction")
    void prevTransTestSuccess() throws Exception{
        bankAccount.prevTrans = 5.0;
    
        double result = bankAccount.getPreviousTrans();
    
        double expectedResult = 5.0;
        assertEquals(result, expectedResult);
    }

    @Test
    @DisplayName("Should return absolute value of previous transaction")
    void prevTransTestAbs() throws Exception{
        bankAccount.prevTrans = -5.0;
    
        double result = bankAccount.getPreviousTrans();
    
        double expectedResult = 5.0;
        assertEquals(result, expectedResult);
    }

    @Test
    @DisplayName("Should throw exception when no prev transaction occured")
    void prevTransTestError(){
        bankAccount.prevTrans = 0.0;

        String expectedMessage = "No transaction occured";

        Exception exception = assertThrows(Exception.class, () -> {
            bankAccount.getPreviousTrans();
        });

        assertEquals(exception.getMessage(), expectedMessage);


    }

    @Test
    @DisplayName("Should throw exception when blockedUser is logged in")
    void menuTestError(){
        bankAccount = new BankAccount("TestName", "12341234");

        String expectedMessage = "Your user is Block.";

        Exception exception = assertThrows(Exception.class, () -> {
            bankAccount.menu();
        });

        assertEquals(exception.getMessage(), expectedMessage);


    }

    @Test
    @DisplayName("Should return welcome text")
    void menuTestSuccess() throws Exception{
        String expectedMessage = "Welcome to our Bank application";

        String result = bankAccount.menu();
       

        assertEquals(result, expectedMessage);


    }

}