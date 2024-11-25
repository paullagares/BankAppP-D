/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;
import core.models.Transaction;
import java.util.ArrayList;

/**
 *
 * @author paullagares
 */

public class TransactionStorage {
    private static TransactionStorage instance;
    private ArrayList<Transaction> transactions;

    private TransactionStorage() {
        this.transactions = new ArrayList<>();
    }

    public static TransactionStorage getInstance() {
        if (instance == null) {
            instance = new TransactionStorage();
        }
        return instance;
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction != null) {
            this.transactions.add(transaction);
            System.out.println("Transacción registrada: " + transaction);
            return true;
        }
        return false;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return new ArrayList<>(this.transactions);
    }
}


  

