/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locusauthbasedsystem;

/**
 *
 * @author Pranita
 */
public class Resource {
    
    private int id;
    
    public Resource(int id)
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
    
    @Override
    public boolean equals(Object o)
    {
        if((o instanceof Resource) && (((Resource) o).getId() == this.id))
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
}
