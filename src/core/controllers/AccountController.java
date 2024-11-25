/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;
import core.controllers.util.Response;
import core.controllers.util.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;
import core.models.storage.UserStorage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author paullagares
 */
public class AccountController {

public Response createAccount(String userId, String initialBalance) {
    try {
        int userIdInt = Integer.parseInt(userId);
        double initialBalanceDouble = Double.parseDouble(initialBalance);

        if (initialBalanceDouble < 0) {
            return new Response("Initial balance cannot be negative", Status.BAD_REQUEST);
        }

        User user = UserStorage.getInstance().getUser(userIdInt);
        if (user == null) {
            return new Response("User with that ID does not exist", Status.BAD_REQUEST);
        }

        Random random = new Random();
        String accountId = String.format("%03d-%06d-%02d", random.nextInt(1000), random.nextInt(1000000), random.nextInt(100));

        Account account = new Account(accountId, user, initialBalanceDouble);
        if (!AccountStorage.getInstance().addOwner(account)) {
            return new Response("Account with this ID already exists", Status.BAD_REQUEST);
        }

        return new Response("Account created successfully for user: " + user.getFirstname(), Status.CREATED);
    } catch (NumberFormatException ex) {
        return new Response("Invalid input: " + ex.getMessage(), Status.BAD_REQUEST);
    } catch (Exception ex) {
        return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}


    
    public ArrayList<Object[]> getAllAccountsSorted() {
        AccountStorage storage = AccountStorage.getInstance();
        ArrayList<Account> allAccounts = storage.getAccounts();
        allAccounts.sort((a1, a2) -> a1.getId().compareTo(a2.getId()));
        
        
        
       ArrayList<Object[]> tableData = new ArrayList<>();
       for(Account account : allAccounts){
           tableData.add(new Object[]{account.getId(), account.getOwner().getId(), account.getBalance()});
       }
       return tableData;
  
    }
    
    
    
    
   
    
    
    
    
    
}
