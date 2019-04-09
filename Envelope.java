/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class Envelope extends Package{
    private String name;
    
    public Envelope (int w, int h, double p, String n){
        super(w,h,p);
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name=n;
    }
    
    public boolean isSuitable(double []size){
        double w, h, d;
        w=size[0];
        h=size[1];
        d=size[2];
        if(d!=0){ 
            return false;
        }
        else{
            if(w <= getWidth() && h <= getHeight()){
                return true;
            }
        }
        return false;  
    }
    
    
    
}
