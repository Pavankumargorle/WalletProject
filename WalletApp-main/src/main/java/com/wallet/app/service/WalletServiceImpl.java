package com.wallet.app.service;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletServiceImpl implements WalletService {

    private WalletDao walletRepository = new WalletDaoImpl();
    
    
    public Wallet registerWallet(Wallet newWallet) throws WalletException {
        //System.out.println(this.walletRepository.getWalletById(1));
        if(newWallet==null)
            throw new WalletException("Invalid wallet\n");
        return walletRepository.addWallet(newWallet);
        
    }

    public Boolean login(Integer walletId, String password) throws WalletException {
        // TODO Auto-generated method stub
        if(walletRepository.getWalletById(walletId)==null)
        {
            throw new WalletException("Wallet invalid : "+walletId+"\n");
        }
        
        else if(walletRepository.getWalletById(walletId).getPassword().equals(password))
        {
            return true;
        }
        else
        {
            throw new WalletException("Password is not matched\n");
        }
    }

    public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException {
        // TODO Auto-generated method stub
        if(walletRepository.getWalletById(walletId)==null)
        {
            throw new WalletException("Wallet not exist: "+walletId+"\n");
        }
        else if(amount<0.0)
        {
            throw new WalletException("Enter amount you want to add.\n");
        }
        else
        {
        
        Wallet w=walletRepository.getWalletById(walletId);
        w.setBalance(w.getBalance()+amount);
        return w.getBalance();
        }
    }

    public Double showWalletBalance(Integer walletId) throws WalletException {
        // TODO Auto-generated method stub
        Wallet w=walletRepository.getWalletById(walletId);
        if(w==null)
            throw new WalletException("Wallet does not exist for id: "+walletId+"\n");
        return w.getBalance();
    }

    public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {
        // TODO Auto-generated method stub
        Wallet w1=walletRepository.getWalletById(fromId);
        Wallet w2=walletRepository.getWalletById(toId);
        if(w1==null)
        {
            throw new WalletException("user invalid try again with valid from id\n");
        }
        else if(w2==null)
        {
            throw new WalletException("user invalid try again with valid to id\n");
        }
        if(w1.getBalance()>amount)
        {
        w1.setBalance(w1.getBalance()-amount);
        w2.setBalance(w2.getBalance()+amount);
         return true;
        }
        else
        {
            throw new WalletException("Insufficient balance: "+ w1.getBalance()+"\n");
            
        }
    
    }

    public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException {
        // TODO Auto-generated method stub
        Wallet foundWallet=walletRepository.getWalletById(walletId);
        if(foundWallet==null)
        {
            throw new WalletException("Wallet invalid: "+walletId+"\n");
        }
        else if(foundWallet.getPassword().equals(password))
        {
        return walletRepository.deleteWalletById(walletId);
        }
        else
        {
            throw new WalletException("wrong password , plz try again..... " + walletId+"\n");
        }
    
    }
    

}