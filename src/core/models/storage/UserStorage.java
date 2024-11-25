/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author paullagares
 */
public class UserStorage {
   
    private static UserStorage instance;
    

    private ArrayList<User> users;
    
    private UserStorage() {
        this.users = new ArrayList<>();
    }
    
    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }
    
    public boolean addUser(User user) {
        for (User p : this.users) {
            if (p.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }
public ArrayList<User> getUsers() {
    if (this.users == null) {
        this.users = new ArrayList<>();
    }
    return this.users;
}


    public User getUser(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
}

