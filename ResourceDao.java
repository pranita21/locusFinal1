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
 */
public class ResourceDao {
    
    private ResourcesDB rDB = ResourcesDB.getInstance();
    
    private Map<Integer,Resource> resourceMap= new HashMap<>();
    
    private List<Resource> resourceList = new ArrayList<>();
    
    public ResourceDao()
    {
        init();
    }
    
    private void init()
    {
        resourceList = rDB.getResources();
        
        for(Resource r: resourceList)
        {
            resourceMap.put(r.getId(),r);
        }
    }
    
    public List<Resource> getAllResources()
    {
        return resourceList;
    }
    
    public Resource getResourceByID(int id)
    {                
        return resourceMap.get(id);
    }        
    
}
