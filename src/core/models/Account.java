/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.Objects;

/**
 *
 * @author edangulo
 */
public class Account implements BankOperations {
    private String id;
    private User owner;
    private double balance;

   
    public Account(String id, User owner) {
    this(id, owner, 0);
    if (owner != null) {
        owner.addAccount(this);
    }
}


    public Account(String id, User owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        if (!this.owner.getAccounts().contains(this)) {
        this.owner.addAccount(this);
    }
    }

    public String getId() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  
        }
        Account account = (Account) obj;
        return Objects.equals(id, account.id); 
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);  
    }


    public User getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(Account targetAccount, double amount) {
        if (amount > 0 && this.balance >= amount && targetAccount != null) {
            this.balance -= amount;  
            targetAccount.deposit(amount);  
            return true;
        }
        return false;
    }
    
}
