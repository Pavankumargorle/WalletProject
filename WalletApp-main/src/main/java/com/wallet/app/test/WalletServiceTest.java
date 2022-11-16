package com.wallet.app.test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wallet.app.dto.Wallet; 
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class WalletServiceTest {
 
    WalletService walletService = new WalletServiceImpl();
    
    @BeforeAll
    public static void setupTestData() {
        System.out.println("Create all test data");
    } 
    @Test
    public void registerWalletTest() {
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
            assertNotNull(wallet);
            assertEquals(1, wallet.getId());        
        } catch (WalletException e) {
            e.printStackTrace();
        } 
    }
    
    @Test
    public void loginWalletTest(){
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
            Assertions.assertTrue(walletService.login(1,"4545"));
            } catch (WalletException e) {
            e.printStackTrace();
            }
    }
    
    @Test
    public void loginPasswordIncorrectTest(){
    try{
    Wallet wallet = walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545""));
    WalletException exceptionThrown = assertThrows(WalletException.class,()->walletService.login(1,"4545"));
    assertEquals(exceptionThrown.getMessage(),"Password not matched");
    } catch (WalletException e) {
    e.printStackTrace();
    }
    }
    
    @Test
    public void loginFailedTest() {
    try{
    Wallet wallet = walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
    WalletException exceptionThrown = assertThrows(WalletException.class,()->walletService.login(2,"989"));
    assertEquals(exceptionThrown.getMessage(),"Wallet invalid");
    } catch (WalletException e) {
    e.printStackTrace(); 
    }
    }
    
    @Test
    public void addFundstoWalletTest() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(2, "Ravi", 8100.0, "1234"));
            Double AFTW = walletService.addFundsToWallet(2, 523.0);
            assertEquals(AFTW,9100.0+523.0);
        }
        catch(WalletException e) 
        {
            e.printStackTrace(); 
        }
    }
    
    @Test
    public void addFundsToWalletExceptionTest() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(3, "Sowmya", 9000.0, "4321"));
            WalletException exceptionThrown = assertThrows(WalletException.class,()->walletService.addFundsToWallet(8,243.0));
            assertEquals(exceptionThrown.getMessage(),"Wallet not exist");
        } catch (WalletException e) {
            
            e.printStackTrace(); 
        }
        
    }
    
    @Test
    public void showWalletBalanceTest() {
        
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
            assertEquals(10000.0, walletService.showWalletBalance(1));
        } catch (WalletException e) {
 
            e.printStackTrace();
        } 
    }
    
    @Test
    public void showWalletBalanceExceptionTest() {
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
            WalletException exceptionThrown = assertThrows(WalletException.class,()-> walletService.showWalletBalance(2));
            assertEquals(exceptionThrown.getMessage(),"Wallet does not exist for id:"+2 );
        } catch (WalletException e) {  
            e.printStackTrace();
        }    
    }
    
    @Test
    public void FundTransferTest(){
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
            Wallet wallet2 =walletService.registerWallet(new Wallet(3, "Sowmya", 9000.0, "4321"));
            assertTrue(walletService.fundTransfer(1, 3, 276.0));        
        }
        catch(WalletException e) {
            
        }
        
    }
    
    @Test
    public void FundTransferExceptionFromWalletNotFoundTest() throws WalletException {
        
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
            Wallet wallet2 =walletService.registerWallet(new Wallet(3, "Sowmya", 9000.0, "4321"));
            WalletException exceptionThrown = assertThrows(WalletException.class,()->walletService.fundTransfer(5, 3, 725.0));
            assertEquals(exceptionThrown.getMessage(),"user invalid try again with valid from id");
    }
    @Test
    public void FundTransferExceptionToWalletNotFoundTest() {
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 7800.0, "4545"));
            Wallet wallet2 =walletService.registerWallet(new Wallet(3, "Sowmya", 9000.0, "4321"));
            WalletException exceptionThrown = assertThrows(WalletException.class,()->walletService.fundTransfer(1,7, 1000.0));
            assertEquals(exceptionThrown.getMessage(),"user invalid try again with valid to id");
        }
        catch(WalletException e) {            
        }     
    }
    
    @Test
    public void FundTransferExceptionInsufficientBalanceTest() throws WalletException {
        
            Wallet wallet =walletService.registerWallet(new Wallet(1, "Pavan", 8800.0, "4545"));
            Wallet wallet2 =walletService.registerWallet(new Wallet(3, "Sowmya", 9000.0, "4321"));
            WalletException exceptionThrown = assertThrows(WalletException.class,()->walletService.fundTransfer(1, 3, 10000.0));
            assertEquals(exceptionThrown.getMessage(),"Insufficient balance: "+ 10000.0 );
                     
    }
    
    @Test
    public void UnregisterWalletSuccessfullTest() throws WalletException {
        Wallet wallet =walletService.registerWallet(new Wallet(3, "Sowmya", 9000.0, "4321"));
        Wallet deletedWallet = walletService.unRegisterWallet(3,"4321");
        assertEquals(deletedWallet.getId(),wallet.getId());
    }
     
    @Test
    public void UnregisterWalletUnSuccessfullExceptionTest() throws WalletException {
        Wallet wallet = walletService.registerWallet(new Wallet(2,"uif", 26540.0,"2345")); 
        WalletException exceptionThrown = assertThrows(WalletException.class, () -> walletService.unRegisterWallet(10, "2345"));
        assertEquals(exceptionThrown.getMessage(), "wrong password , plz try again..... ");
    }
    @AfterAll
    public static void destroyTestData() {
        System.out.println("Clear all test data");
    }
}


	


