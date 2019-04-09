
import java.util.LinkedList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public class Buyer extends User{
    
    private String accountNumber;
    private LinkedList <Item> boughtItems;
    private double availableMoney;
    
    public Buyer(String n, String id, String p, String a, double money){
        super(n,id,p);
        accountNumber = a;
        boughtItems= new LinkedList<>();
        availableMoney= money;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    
    public void buy(Item i){//comprar tot l item
        boughtItems.add(i);
    }
 
    public boolean pay(double price){ //price es el preu final de l objecte mirar si el user te prou pasta
        
        return true;
    }
    
}
