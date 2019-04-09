/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class Box extends Package{
    private int depth;
    
    
    public Box(int w, int h,double p, int d){
        super(w,h,p);
        depth=d;
    }
    
    public int getDepth(){
        return depth;
    }
    
    public void setDepth(int d){
        depth=d;
    }
    
    public boolean isSuitable(double []size){
       
        double w, h, d;
        w=size[0];
        h=size[1];
        d=size[2];
        if(d==0){ 
            return false;
        }
        else{
            if(w <= getWidth() && h <= getHeight() && d<= depth){
                return true;
            }
        }
        return false;  
    }
}
