/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class UnitItem extends Item {
    private double unitPrice;
    private int quantity;
    private int quantityRemaining; //????
    
    public UnitItem(String n, String t, double []s, double c, double uprice, int q){
        super(n,t,s,c);
        unitPrice=uprice;
        quantity = q;
        quantityRemaining=q;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public int getQuantityRemaining(){
        return quantityRemaining;
    }
    
    public void setQuantity(int q){
        quantity = q;
    }
    
    public void setQuantityRemaining(int qr){
        quantityRemaining = qr;
    }
    
    
    public double getPrice(){
        return unitPrice *quantity;
    }
    public double calculateProfit(){
        double profit = (unitPrice-cost)*quantity;
        return profit;
    }
    
    public double sell(int q){ // in the case you don`t want to buy all the items
        double finalPrice;
        if(quantityRemaining>=q){
           finalPrice=unitPrice*q;
            quantityRemaining-=q;
            return finalPrice; 
        }
        return 0.0;
    }
    public double getPriceOnlyTax(){
        return this.getPrice()*IVA;
    }
    
    public double getPricePlusTax(){
        return (this.getPrice()*IVA)+this.getPrice();
    }
   
    public double sumTotalTax(Taxable t){
        return (t.getPriceOnlyTax())+this.getPriceOnlyTax();
    }

    public int compareTo(Object i){
        Item other = (Item)i;
        
        double price1 = other.getPrice();
        double price2 = unitPrice*quantity;
        if(price2<price1){
            return -1; //el preu de l item de dins es mes gran que el preu de l item de fora 
        }
        if(price1<price2){
            return -1; //el preu de l'item de dins es mes petit que el preu de l item de fora
        } 
        return 0; 
    }
        
}

