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
import java.util.Collections;


public class Sale implements Comparable{
    private Item item;
    private Buyer buyer;
    private Date saleDate;
    private Date sentDate;
    
    public Sale(Item i,Buyer b,Date saleD,Date sentD){
        item = i;
        buyer = b;
        saleDate= saleD;
        sentDate=sentD;
    }
    public void setItem(Item i){
        item = i;
    }
    public void setBuyer(Buyer b){
        buyer = b;
    }
    public void setSaleDate(Date saleD){
        saleDate = saleD;
    }
    public void setSentDate(Date sentD){
        sentDate=sentD;
    }
    public Item getItem(){
        return item;
    }
    public Buyer getBuyer(){
        return buyer;
    }
    public Date getSaleDate(){
        return saleDate;
    }
    public Date getSentDate(){
        return sentDate;
    }
    @Override
    public int compareTo(Object s ){ ///??????
        Sale other = (Sale)s;
        if(saleDate.after(other.getSaleDate())){
            return -1; //saleDate es mes tard que la de l objecte de fora
        }
        if(saleDate.before(other.getSaleDate())){
            return 1; //sale date es abans que la de lobjecte de fora
        }
        return 0;  //mateixa data
    }
}
