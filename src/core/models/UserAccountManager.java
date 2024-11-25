/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp-2522-la
 */
public class UserAccountManager implements AccountManager {
    private List<Account> accounts;

    public UserAccountManager() {
        this.accounts = new ArrayList<>();
    }

    
    @Override
    public void addAccount(Account account) {
    if (account == null || this.accounts.contains(account)) {
        return; 
    }
    this.accounts.add(account);
}


    @Override
    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    @Override
    public List<Account> getAccounts() {
        return this.accounts;
    }

    @Override
    public int getNumAccounts() {
        return this.accounts.size();
    }
}