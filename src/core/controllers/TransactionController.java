/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;
import core.controllers.util.Response;
import core.models.Transaction;
import core.controllers.util.Status;
import core.models.Account;
import core.models.TransactionType;
import static core.models.TransactionType.DEPOSIT;
import static core.models.TransactionType.TRANSFER;
import static core.models.TransactionType.WITHDRAW;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;
/**
 *
 * @author paullagares
 */

import java.util.ArrayList;

public class TransactionController {

    public Response handleTransaction(TransactionType type, String sourceAccountId, String destinationAccountId, String amountStr) {
        try {
           
            double amount;
            try {
                amount = Double.parseDouble(amountStr.trim());
                if (amount <= 0) {
                    return new Response("Amount must be positive.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Invalid amount. Please enter a valid numeric value.", Status.BAD_REQUEST);
            }

            
            AccountStorage accountStorage = AccountStorage.getInstance();
            TransactionStorage transactionStorage = TransactionStorage.getInstance();

            
            switch (type) {
                case DEPOSIT: {
                    Account destinationAccount = accountStorage.getAccountById(destinationAccountId);
                    if (destinationAccount != null) {
                        destinationAccount.deposit(amount);

                        
                        Transaction transaction = new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amount);
                        transactionStorage.addTransaction(transaction);

                        return new Response("Deposit successful.", Status.OK);
                    }
                    return new Response("Destination account not found.", Status.BAD_REQUEST);
                }
                case WITHDRAW: {
                    Account sourceAccount = accountStorage.getAccountById(sourceAccountId);
                    if (sourceAccount != null && sourceAccount.withdraw(amount)) {
                       
                        Transaction transaction = new Transaction(TransactionType.WITHDRAW, sourceAccount, null, amount);
                        transactionStorage.addTransaction(transaction);

                        return new Response("Withdrawal successful.", Status.OK);
                    }
                    return new Response("Insufficient funds or account not found.", Status.BAD_REQUEST);
                }
                case TRANSFER: {
                    Account sourceAccount = accountStorage.getAccountById(sourceAccountId);
                    Account destinationAccount = accountStorage.getAccountById(destinationAccountId);
                    if (sourceAccount != null && destinationAccount != null && sourceAccount.withdraw(amount)) {
                        destinationAccount.deposit(amount);

                       
                        Transaction transaction = new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount, amount);
                        transactionStorage.addTransaction(transaction);

                        return new Response("Transfer successful.", Status.OK);
                    }
                    return new Response("Transfer failed. Check account IDs and balances.", Status.BAD_REQUEST);
                }
                default:
                    return new Response("Invalid transaction type.", Status.BAD_REQUEST);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
        
        
    }
    
    
    public ArrayList<Transaction> getAllTransactionsSorted() {
    TransactionStorage transactionStorage = TransactionStorage.getInstance();
    ArrayList<Transaction> allTransactions = transactionStorage.getAllTransactions();

    allTransactions.sort((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()));

    return allTransactions;
}


}




