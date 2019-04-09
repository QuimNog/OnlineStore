
import java.util.LinkedList;
import java.util.Date;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class Administrator extends User{
    
    public Administrator(String n, String id, String p){
        super(n,id,p);
    }
    
    public boolean expel(User u){
        return true;     
    }
    
    public boolean manageAuction(AuctionItem a, Date date){
        return !a.frozen(date);   
    }
    
    public void printStock(LinkedList <AuctionItem> items){
        int i;
           for(i=0; i< items.size(); i++){
               System.out.print(items.get(i).getName());
           }
    }
    
    public void Inventory(LinkedList <Item> inventory){
        int i;
        Item aux;
        double totalPrice, totalBenefit;
        totalPrice = 0; 
        totalBenefit=0;
        for(i=0; i<inventory.size(); i++){
            aux = inventory.get(i);
            System.out.println("Object name: "+aux.getName());
            System.out.println("Price: "+aux.getPricePlusTax());
            System.out.println("Benefit of the object: "+aux.calculateProfit()+"\n");
            totalPrice += aux.getPricePlusTax();
            totalBenefit += aux.calculateProfit(); 
        }
        System.out.println("The Total Price is: "+totalPrice);
        System.out.println("The Total Benefit is: "+totalBenefit);
        
    }
}
