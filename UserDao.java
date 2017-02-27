/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locusauthbasedsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pranita
 * 
 * User Data Access Object - used to access user objects from user Data
 * 
 */
public class UserDao {
    
    private UsersDB uDB = UsersDB.getInstance();
    
    private Map<Integer, User> userMap = new HashMap<>();
    
    private List<User> userList = new ArrayList<User>();
    
    public UserDao()
    {
        init();
    }
    
    private void init()
    {
        userList = uDB.getAllUsers();
        
        for(User user: userList)
        {
            userMap.put(user.getId(), user);
        }
    }
    
    public List<User> getAllUsers()
    {
        return userList;
    }
    
    public User getUserByID(int id)
    {
        return userMap.get(id);
    }
    
}
