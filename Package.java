/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class Package implements Taxable{
    private int width;
    private int height;
    private int depth; 
    private double price;
    
    public Package(int w, int h, double p){
        width =w;
        height=h;
        price =p;
    }
    
    public double getPrice(){
        return price;
    }
    public void setPrice(double newPrice){
        price=newPrice;
    }
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setWidth(int w){
        width=w;
    }
    
    public void setHeight(int h){
        height=h;
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
}
