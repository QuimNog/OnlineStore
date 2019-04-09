/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class WeightedItem extends Item{
    private double pricePerWeight;
    private double weight;
    private double weightRemaining;
    
    public WeightedItem(String n, String t, double []s, double c, double wPrice, double w){
        super(n,t,s,c);
        pricePerWeight =wPrice;
        weightRemaining =w;
        weight= w;
    }
    public double getWeight(){
        return weight;
    }
    
    public double getWeightRemaining(){
        return weightRemaining;
    }
    
    public void setWeight(double w){
        weight = w;
    }
    
    public void setWeightRemaining(double wr){
        weightRemaining = wr;
    }
    
    
    public double getPrice(){ 
        return pricePerWeight * weight;
    }
    public double calculateProfit(){ //calculates the profit for ALL THE UNITS
        double profit = (pricePerWeight-cost)*weight;
        return profit; 
    }
    public double sell(double w){ //in the case you only want to buy some weight and not all of it. 
        
        double finalPrice;
        if(weightRemaining>= w){
            finalPrice=pricePerWeight*w;
            weightRemaining -= w;
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
        double price2 = pricePerWeight*weight;
        if(price2<price1){
            return -1; //el preu de l item de dins es mes gran que el preu de l item de fora 
        }
        if(price1<price2){
            return -1; //el preu de l'item de dins es mes petit que el preu de l item de fora
        } 
        return 0; 
    }
}
