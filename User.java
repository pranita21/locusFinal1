/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locusauthbasedsystem;

//import locusauthbasedsystem.LocusAuthBasedSystem.Role;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Pranita
 * 
 * User Object - with it's properties
 * 
 */
public class User {
    
    private int id;
    
    private Set<Role> userRoles = new HashSet<>();
    
    //private Role r;
    
    public User(int id)
    {
        this.id = id;
    }
    
    public void setId(int value)
    {
        this.id = value;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public void addRole(Role r)
    {
        userRoles.add(r);
    }
    
    public void removeRole(Role r)
    {
        userRoles.remove(r);       
    }
    
    public Set<Role> getUserRoles()
    {
        return this.userRoles;
    }
    
    public void printUserRoles()
    {
        System.out.println("User " + id + " is assigned these roles: ");
        for(Role r:userRoles)
        {
            System.out.println("=>" + r);
        }
    }
    
    @Override
    public boolean equals(Object o)
    {
        if((o instanceof User) && (((User) o).getId() == this.id))
        {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return id*2;
    }  
    
    /*public boolean doesUserHaveAccess(Resource r, ActionType at)
    {
        RoleDao roleDao = new RoleDao();
        
        HashMap<Resource,HashSet<ActionType>> userPermissions = new HashMap<>();
        
        for(Role role: userRoles)
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
    }*/
    
}
