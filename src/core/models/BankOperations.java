/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models;

/**
 *
 * @author hp-2522-la
 */
public interface BankOperations {
    boolean deposit(double amount);
    boolean withdraw(double amount);
    boolean transfer(Account targetAccount, double amount);  
}
