/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models;

import java.util.List;

/**
 *
 * @author hp-2522-la
 */
public interface AccountManager {
    void addAccount(Account account);
    void removeAccount(Account account);
    List<Account> getAccounts();
    int getNumAccounts();
}
