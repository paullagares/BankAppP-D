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
            int userIdInt;
            double initialBalanceDouble;

            try {
                initialBalanceDouble = Double.parseDouble(initialBalance);
                if (initialBalanceDouble < 0) {
                    return new Response("Initial balance cannot be negative", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Initial balance must be numeric", Status.BAD_REQUEST);
            }

            try {
                userIdInt = Integer.parseInt(userId);
                if (userIdInt < 0) {
                    return new Response("User ID must be positive", Status.BAD_REQUEST);
                }
                if (userIdInt >= 1000000000) {
                    return new Response("User ID must be between 1 and 9 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("User ID must be numeric", Status.BAD_REQUEST);
            }

            UserStorage userStorage = UserStorage.getInstance();
            User user = userStorage.getUser(userIdInt);
            if (user == null) {
                return new Response("User with that ID does not exist", Status.BAD_REQUEST);
            }

            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);
            String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

            Account account = new Account(accountId, user, initialBalanceDouble);

            user.addAccount(account);  
            AccountStorage.getInstance().addOwner(account);  

            return new Response("Account created successfully for user: " + user.getFirstname(), Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    public ArrayList<Account> getAllAccountsSorted() {
        UserStorage userStorage = UserStorage.getInstance();
        ArrayList<Account> allAccounts = new ArrayList<>();

        ArrayList<User> users = userStorage.getUsers();

        for (User user : users) {
            ArrayList<Account> userAccounts = (ArrayList<Account>) user.getAccounts();
            if (userAccounts != null) {
                allAccounts.addAll(userAccounts);
            }
        }

        allAccounts.sort((a1, a2) -> a1.getId().compareTo(a2.getId()));
        return allAccounts;
    }
}
