/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locusauthbasedsystem;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Pranita
 * 
 * This class is meant for accessing resources and actionTypes allowed by a user on these resources based on the role
 * 
 * I have also initialized the static mapping between roles, resources and actionTypes here which is usually stored in a DB
 * 
 */
public class RoleDao {
    
    //public static RoleDao roles = new RoleDao();
    
    private EnumMap<Role,HashMap<Resource,HashSet<ActionType>>> rolesMap = new EnumMap<>(Role.class);
    
    /* Static data which maps "Roles" to various "Resources" available in the system and the "ActionTypes" allowed on these resources for the Role
     * This mapping is usually stored in a database
     */
    public RoleDao()
    {
        initRolesMap();
    }
    
    private void initRolesMap()
    {
        ResourceDao resourceDao = new ResourceDao();
        
        //For Admin Role - Add all resources and all provide all the actionTypes        
        
        HashMap<Resource,HashSet<ActionType>> resourcesAndActionTypesForAdmins = new HashMap<>();               
        for(Resource resource: resourceDao.getAllResources())
        {
            resourcesAndActionTypesForAdmins.put(resource,new HashSet<>(Arrays.asList(ActionType.READ, ActionType.WRITE, ActionType.AUTHORIZE, ActionType.DELETE)));
        }        
        rolesMap.put(Role.Admin, resourcesAndActionTypesForAdmins);        
        
        //For External users - Provide read and write permissions on resources 1 & 2 and provide read only permission on resource 3
        
        HashMap<Resource,HashSet<ActionType>> resourcesAndActionTypesForExternalUsers = new HashMap<>();
        
        resourcesAndActionTypesForExternalUsers.put(resourceDao.getResourceByID(1), new HashSet<>(Arrays.asList(ActionType.READ, ActionType.WRITE)));
        resourcesAndActionTypesForExternalUsers.put(resourceDao.getResourceByID(2), new HashSet<>(Arrays.asList(ActionType.READ, ActionType.WRITE)));
        resourcesAndActionTypesForExternalUsers.put(resourceDao.getResourceByID(3), new HashSet<>(Arrays.asList(ActionType.READ)));
        
        rolesMap.put(Role.ExternalUser, resourcesAndActionTypesForExternalUsers);
        
        //For Operations - Provide  read, write and authorize permissions on resources 1,2,4
        
        HashMap<Resource,HashSet<ActionType>> resourcesAndActionTypesForOperations = new HashMap<>();
        
        resourcesAndActionTypesForOperations.put(resourceDao.getResourceByID(1), new HashSet<>(Arrays.asList(ActionType.READ, ActionType.WRITE, ActionType.AUTHORIZE)));
        resourcesAndActionTypesForOperations.put(resourceDao.getResourceByID(2), new HashSet<>(Arrays.asList(ActionType.READ, ActionType.WRITE, ActionType.AUTHORIZE)));
        resourcesAndActionTypesForOperations.put(resourceDao.getResourceByID(4), new HashSet<>(Arrays.asList(ActionType.READ, ActionType.WRITE, ActionType.AUTHORIZE)));

        rolesMap.put(Role.Operations, resourcesAndActionTypesForOperations);
        
        //For Client Service Reps, provide read only permissions on r1 and r2, and all actionTypes on resource4
        
        HashMap<Resource,HashSet<ActionType>> resourcesAndActionTypesForCSRs = new HashMap<>();
        
        resourcesAndActionTypesForCSRs.put(resourceDao.getResourceByID(1), new HashSet<>(Arrays.asList(ActionType.READ)));
        resourcesAndActionTypesForCSRs.put(resourceDao.getResourceByID(2), new HashSet<>(Arrays.asList(ActionType.READ)));
        resourcesAndActionTypesForCSRs.put(resourceDao.getResourceByID(4), new HashSet<>(Arrays.asList(ActionType.READ, ActionType.WRITE, ActionType.AUTHORIZE, ActionType.DELETE)));

        rolesMap.put(Role.ClientServiceRep, resourcesAndActionTypesForCSRs);
        
    }
    
    public HashMap<Resource, HashSet<ActionType>> getResourcesByRole(Role r)
    {
        return rolesMap.get(r);
    }
}
