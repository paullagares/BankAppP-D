/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;
import core.controllers.util.Response;
import core.controllers.util.Status;
import core.models.AccountManager;
import core.models.storage.UserStorage;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author paullagares
 */
public class UserController {
    public Response createUser(String id, String firstname, String lastname, String age, AccountManager accountManager) {
        try {
            int idInt, ageInt;

            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }                
                if(idInt>=1000000000){
                return new Response("ID must be betwen 1 and 9 digits", Status.BAD_REQUEST);} 
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.isEmpty()) {
                return new Response("Firstname must not be empty", Status.BAD_REQUEST);
            }
            if (lastname.isEmpty()) {
                return new Response("Lastname must not be empty", Status.BAD_REQUEST);
            }

            try {
                ageInt = Integer.parseInt(age);
                if (ageInt < 18) {
                    return new Response("Age must be 18 or older", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }

            User newUser = new User(idInt, firstname, lastname, ageInt);

            UserStorage storage = UserStorage.getInstance();
            if (!storage.addUser(newUser)) {
                return new Response("A person with that id already exists", Status.BAD_REQUEST);
            }
            
            return new Response("Person created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    public ArrayList<Object[]> getAllUsers() {
    UserStorage storage = UserStorage.getInstance();
    ArrayList<User> users = storage.getUsers();
    users.sort((obj1, obj2) -> obj1.getId() - obj2.getId());

    ArrayList<Object[]> tableData = new ArrayList<>();
    for (User user : users) {
        tableData.add(new Object[]{user.getId(), user.getFirstname() + " " + user.getLastname(), user.getAge(), user.getNumAccounts()});
    }

    return tableData;
}

}
