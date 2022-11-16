package com.wallet.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletDaoImpl implements WalletDao {

    // Create collection to store the Wallet information.
    Map<Integer, Wallet> wallets = new HashMap();

    public Wallet addWallet(Wallet newWallet) throws WalletException {

                 this.wallets.put(newWallet.getId(), newWallet);
          if(this.wallets.get(newWallet.getId())==null)
          {
              throw new WalletException("New wallet was not added proprely");
          }
          else
          {
              return this.wallets.get(newWallet.getId());
          }

    }

    public Wallet getWalletById(Integer walletId) throws WalletException {
        // TODO Auto-generated method stub
        if(wallets.containsKey(walletId))
        {
            return wallets.get(walletId);
            
        }
        else
        {
            throw new WalletException("Please enter valid walletId: " + walletId);
        }
        
    }

    public Wallet updateWallet(Wallet updateWallet) throws WalletException {
        // TODO Auto-generated method stub
        Wallet w=null;
        if(wallets.containsKey(updateWallet.getId()))
        {
            w=wallets.get(updateWallet.getId());
            w.setName(updateWallet.getName());
            w.setBalance(updateWallet.getBalance());
            w.setPassword(updateWallet.getPassword());
            return w;
        }
        else
        
            throw new WalletException("Please enter valid walletId: " + updateWallet.getId());
    }

    public Wallet deleteWalletById(Integer walletID) throws WalletException {
        // TODO Auto-generated method stub
        Wallet p=null;
        if(wallets.containsKey(walletID))
          return wallets.remove(walletID);    
        else
            throw new WalletException("Please enter valid walletId: " + walletID);

    }

}