
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class Seller extends User{
    private String accountnumber; 
    protected LinkedList<Item> soldItems;
    protected LinkedList <Item> availableItems;
    private double money;
    
    public Seller (String n, String id, String p, String a){
        super(n,id,p);
        accountnumber=a;
        soldItems= new LinkedList<>();
        availableItems = new LinkedList<>();
        money=0;
        
    }
    
    public void sell(Item i){
         soldItems.add(i);
         availableItems.remove(i);
    }
    
    public void addAvailableItem(Item i){
        availableItems.add(i);
        
    }
    // dont think we need it
    public boolean deposit(double price){
        money+= price;
        return true;
    }
}
