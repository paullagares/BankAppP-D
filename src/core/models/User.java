/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.List;

/**
 *
 * @author edangulo
 */
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private int age;
    private AccountManager  accountManager;

    public User(int id, String firstname, String lastname, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
         this.accountManager = new UserAccountManager();
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }


    public void addAccount(Account account) {
        this.accountManager.addAccount(account);
    }

    public List<Account> getAccounts() {
        return this.accountManager.getAccounts();
    }

public int getNumAccounts() {
    int numAccounts = this.accountManager.getNumAccounts();
    return numAccounts;
}

}
