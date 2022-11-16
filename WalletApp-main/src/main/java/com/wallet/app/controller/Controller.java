package com.wallet.app.controller;

import java.util.Scanner;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class Controller {

	public static void main(String[] args) {
		
		WalletService walletService = new WalletServiceImpl();

		try {
			Wallet wallet = walletService.registerWallet(new Wallet(1, "Ford", 1000.0, "123"));
			System.out.println(wallet);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		int k;
		do
		{
		System.out.println(" 1.register\n 2.login\n 3.add amount\n 4.show wallet\n 5.transfer\n 6.unregister" );
		Scanner sc=new Scanner(System.in);
		
		k=sc.nextInt();
        switch(k)
        {
        //to register 
        case 1:
            System.out.println("Enter ID:");
            Integer id=sc.nextInt();
            System.out.println("Enter Name:");
            String name=sc.next();
            System.out.println("Enter amount:");
            Double amount=sc.nextDouble();
            System.out.println("Password");
            String password=sc.next();
            try 
            {
            Wallet wallet = walletService.registerWallet(new Wallet(id, name, amount, password));
            if(id==wallet.getId())
            System.out.println("Successfully registered\n");
            }
            catch (WalletException e)
            {
            
                  System.out.println(e.getMessage());
            }
            break;
            
            //to login/siginin
        case 2:
            System.out.println("Enter ID:");
            Integer id_=sc.nextInt();
            System.out.println("Password");
            String pswd=sc.next();
            
            try {
                boolean wallet = walletService.login(id_, pswd);
                if(wallet)
                {
                    System.out.println("login successfull.......\n");
                }
            } 
        
            catch (WalletException e)
            {
                
                System.out.println(e.getMessage());
            
            }
            break;
            
            // to add amount
        case 3:
            System.out.println("Enter ID:");
            Integer id1=sc.nextInt();
            System.out.println("amount");
            Double amount1=sc.nextDouble();
            try {
                Double k1=walletService.addFundsToWallet(id1,amount1);
                System.out.println(" your wallet balance is: "+k1+"\n");
            } catch (WalletException e) {
                
                System.out.println(e.getMessage());
            }
            break;
            
            // to show wallet balance
        case 4:
            System.out.println("Enter wallet ID");
            Integer id2=sc.nextInt();
            try {
                Double wallet = walletService.showWalletBalance(id2);
                System.out.println("wallet balance : "+wallet);
            } catch (WalletException e) {
              
                System.out.println(e.getMessage());
            }
            break;
            // to transfer the amount
        case 5:
            System.out.println("Enter from wallet ID");
            Integer fromaccount=sc.nextInt();
            System.out.println("Enter to wallet ID");
            Integer toaccount=sc.nextInt();
            System.out.println("Enter amount to transfer");
            Double d=sc.nextDouble();
            
            
            try {
                boolean wallet = walletService.fundTransfer(fromaccount, toaccount, d);
                if(wallet)
                {
                    System.out.println("money Successfully Transfered:"+d+"\n");
                }
                
            } catch (WalletException e) {
              
                System.out.println(e.getMessage());
            }
            break;
            
            // to delete account
        case 6:
            System.out.println("Enter ID:");
            Integer id3=sc.nextInt();
            System.out.println("Password");
            String password3=sc.next();
            
            
                try {
                    Wallet wallet = walletService.unRegisterWallet(id3, password3);
                    System.out.println("unregistered wallet id is "+wallet.getId()+"\n");
                } catch (WalletException e) {
                    
                    System.out.println(e.getMessage());
                }
                break;
        
        }
       
	}while(k!=0);
		
	}
	

}

