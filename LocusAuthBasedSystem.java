/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locusauthbasedsystem;

/**
 *
 * @author Pranita
 */
public class LocusAuthBasedSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        System.out.println("START");
        
        Boolean doesUserHavePermission;
        
        UserDao userDao = new UserDao();        
        User testUser1 = userDao.getUserByID(1);        
        testUser1.addRole(Role.Operations);        
        testUser1.addRole(Role.Admin);
        testUser1.printUserRoles();
        
        ResourceDao resourceDao = new ResourceDao();        
        Resource testResource1 = resourceDao.getResourceByID(4);        
        
        ActionType testActionType1 = ActionType.READ;
        ActionType testActionType2 = ActionType.WRITE;
        
        doesUserHavePermission = UserManagement.doesUserHaveAccess(testUser1, testResource1, testActionType1);
        System.out.println("User with id " + testUser1.getId() + " has " + doesUserHavePermission + " " + testActionType1  + " permission on resource with id " + testResource1.getId());
        
        doesUserHavePermission = UserManagement.doesUserHaveAccess(testUser1, testResource1, testActionType2);
        System.out.println("User with id " + testUser1.getId() + " has " + doesUserHavePermission + " " + testActionType2  + " permission on resource with id " + testResource1.getId());
        
        System.out.println("END");
    }    
}
