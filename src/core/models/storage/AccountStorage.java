/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;
import core.models.Account;
import java.util.ArrayList;
/**
 *
 * @author paullagares
 */
public class AccountStorage {

    private static AccountStorage instance;
    private ArrayList<Account> accounts;

    private AccountStorage() {
        this.accounts = new ArrayList<>();
    }

    public static AccountStorage getInstance() {
        if (instance == null) {
            instance = new AccountStorage();
        }
        return instance;
    }

public boolean addOwner(Account account) {
   
    for (Account a : this.accounts) {
        if (a.getId().equals(account.getId())) {
            System.out.println("La cuenta con ID " + account.getId() + " ya está registrada.");
            return false; 
        }
    }

    
    System.out.println("Agregando cuenta con ID: " + account.getId());
    this.accounts.add(account);
    System.out.println("Tamaño actual de la lista de cuentas: " + this.accounts.size());
    return true;
}


    public ArrayList<Account> getAccounts() {
        if (this.accounts == null) {
            this.accounts = new ArrayList<>();
        }
        return this.accounts;
    }

    public Account getAccountById(String id) {
        for (Account account : this.accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }
}

