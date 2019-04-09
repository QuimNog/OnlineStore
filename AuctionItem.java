/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
import java.util.Date;

public class AuctionItem extends Item{
    private double currentPrice;
    private Buyer bidder;
    private Date deadline;
    private final double fee = 5;
    private final double percentage = 0.05;
    
    public AuctionItem(String n, String t, double []s, double c, double startingPrice, Date d){
        super(n,t,s,0);
        currentPrice=startingPrice;
        deadline= d;
    }
    
    //HEM DE MIRAR QUAN S'APLICA LA FEE I QUAN NO
    
    public double getPrice(){
        return currentPrice;
    }
    
    public double calculateProfit(){
        double profit;
        profit= ((currentPrice*percentage)+fee);
        return profit;
    }
    
    public void makeBid(Buyer b, double p){
        
            bidder = b;
            currentPrice = p;        
    }
    
    public boolean frozen(Date d){ //?? que fa la funcio i comparar dates
        if(d.compareTo(deadline)<0){
            return false;
        }
        else{
            return true;
        }
        
    }
    
    public Buyer getBuyer(){
        return bidder;
    }
    
    public Date getDeadline(){
        return deadline;
    }
    
    public double getPriceOnlyTax(){
       return currentPrice * IVA; 
    }
    
    public double getPricePlusTax(){
        return currentPrice +(currentPrice * IVA);
    }
   
    public double sumTotalTax(Taxable t){
        return t.getPriceOnlyTax() + currentPrice*IVA;
    }
    
   public int compareTo(Object i){
        Item other = (Item)i;
        
        double price1 = other.getPrice();
        double price2 = currentPrice;
        if(price2<price1){
            return -1; //el preu de l item de dins es mes gran que el preu de l item de fora 
        }
        if(price1<price2){
            return -1; //el preu de l'item de dins es mes petit que el preu de l item de fora
        } 
        return 0; 
    }
    
    
    
}
