/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locusauthbasedsystem;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Pranita
 * 
 * This class is to determine if a user has access to a resource for a particular actionType based on his role(s)
 * 
 */
public class UserManagement {
    
    //this initilization happens usually via dependency injection
    private static RoleDao roleDao = new RoleDao();
    
    public static boolean doesUserHaveAccess(User user, Resource r, ActionType at)
    {                
        HashMap<Resource,HashSet<ActionType>> userPermissions = new HashMap<>();
        
        for(Role role: user.getUserRoles())
        {
            HashMap<Resource,HashSet<ActionType>> userPermissionsForRole = roleDao.getResourcesByRole(role);
            
            for(Resource resource: userPermissionsForRole.keySet())
            {
                if(userPermissions.get(resource) == null)
                {
                    userPermissions.put(resource, userPermissionsForRole.get(resource));
                }
                else
                {
                    userPermissions.get(resource).addAll(userPermissionsForRole.get(resource));                                        
                }
            }
        }                                         
        
        if(userPermissions.get(r) == null)
        {
            return false;
        }
        else
        {
            if(userPermissions.get(r).contains(at))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
}
