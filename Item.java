
import java.util.LinkedList;
import java.util.Collections;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public abstract class Item implements Taxable, Comparable{
    private String name;
    private String type;
    private double []size = new double[3];
    protected double cost;
    private Package pack;
    
    public Item(){
        
    }
    public Item(String n, String t,double [] s, double c){
        name = n;
        type = t;
        size = s;
        cost = c;
    }

    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public double[] getSize(){
        return size;
    }
    public double getCost(){
        return cost;
    }
    public Package getPack(){
        return pack;
    } 
    
    public void setName(String n){
        name = n;
    }
    public void setType(String t){
        type = t;
    }
    public void setSize(double []s){
        size=s;
    }
    public void setCost(double c){
        cost = c;
    }
    public void setPack(Package p){
        pack=p;
    }
    
    public Package assignBestPackage(LinkedList<Package> Lp){
    int i,j,okPackage;
    okPackage=0;
    LinkedList <Package> auxlist = new LinkedList();
    
    for(i=0; i<Lp.size(); i++){
        if(Lp.get(i) instanceof Box){
           ((Box) Lp.get(i)).isSuitable(size);
           auxlist.add(Lp.get(i));
        }
        else{
            ((Envelope)Lp.get(i)).isSuitable(size);
            auxlist.add(Lp.get(i));
        }
    }
    
    for(j=0; j< auxlist.size()-1;j++){
        if(size[2]== 0){
            if(((auxlist.get(j).getHeight()-size[1])<=(auxlist.get(j+1).getHeight()-size[1])
                    &&(auxlist.get(j).getWidth()-size[0])<=(auxlist.get(j+1).getWidth()-size[0]))){
                okPackage = j;
            }
            else{
                okPackage=j+1;
            }
        }
        else{
            if(((auxlist.get(j).getHeight()-size[1])<=(auxlist.get(j+1).getHeight()-size[1])
                    &&(auxlist.get(j).getWidth()-size[0])<=(auxlist.get(j+1).getWidth()-size[0])
                    &&(((Box)auxlist.get(j)).getDepth() -size[2]<= ((Box)auxlist.get(j+1)).getDepth() -size[2]))){
                okPackage = j;
            }
            else{
                okPackage=j+1;
            }
        }   
    }
    pack= auxlist.get(okPackage);
    return pack;
    }
    
    public abstract double getPrice();

    public abstract double calculateProfit();
    
    public abstract double getPriceOnlyTax();
    
    public abstract double getPricePlusTax();
   
    public abstract double sumTotalTax(Taxable t);
    
    
    public abstract int compareTo(Object i);
    
    

    
}
