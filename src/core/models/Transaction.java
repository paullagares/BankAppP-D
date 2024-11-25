/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.time.LocalDateTime;

/**
 *
 * @author edangulo
 */
public class Transaction {
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    private LocalDateTime timestamp;

    
    public Transaction(TransactionType type, Account sourceAccount, Account destinationAccount, double amount) {
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();  
    }

    
    public TransactionType getType() {
        return type;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "type=" + type +
               ", sourceAccount=" + (sourceAccount != null ? sourceAccount.getId() : "N/A")+ 
               ", destinationAccount=" + (destinationAccount != null ? destinationAccount.getId() : "N/A") +
               ", amount=" + amount +
               ", timestamp=" + timestamp +
               '}';
    }
}

