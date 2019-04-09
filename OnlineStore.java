
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Date;
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
public class OnlineStore {
    
    /**
     * @param args the command line arguments
     */
    static protected LinkedList <User> users;
    static protected LinkedList <Item> itemsSold; 
    static protected LinkedList <Item> itemsAvailable;
    static protected LinkedList <Package> packages;
    static protected LinkedList <Sale> sales;
    private double totalPrice;
    static private double totalBenefit;
    private double sumTotalTax;
    static Date today = new java.util.Date(); //today's date 
    
    public static Date incrementDate(Date date){
        today = date; 
        return today; 
    }
    
    public static void sell(Buyer b,Seller s, Item i){
        
        //buy unit item
        if(i instanceof UnitItem){
            System.out.println("The buyer: "+b.getName()+ " is buying the following unit item: "+ i.getName());
            b.buy(i);
            Package packagenew = i.assignBestPackage(packages);
            System.out.println("The best package is assigned to your item");

            if(b.pay(i.getPricePlusTax()+packagenew.getPricePlusTax())){
                System.out.println("The account number "+ b.getAccountNumber()+" has payed the item");
                
                //calculates the profit
                totalBenefit += i.calculateProfit();
                System.out.println("The actual benefit of the Store is: "+ totalBenefit);
                
                
                Sale newsale = new Sale(i,b,today,today);
                sales.add(newsale);
                
                //adds & removes from the respective lists
                itemsSold.add(i);
                itemsAvailable.remove(i);
            }
            else{
                System.out.println("There's not enough money in the account");
            }
            System.out.println("\n");
        }
        
        //buy weigthed item
        if(i instanceof WeightedItem){
            
            System.out.println("The buyer: "+b.getName()+ " is buying the following weighted item: "+ i.getName());
            b.buy(i);
            Package newpackage = i.assignBestPackage(packages);
            System.out.println("The best package is assigned to your item");

            if(b.pay(i.getPricePlusTax()+newpackage.getPricePlusTax())){
                System.out.println("The account number "+ b.getAccountNumber()+" has payed the item");
                //calculates the profit
                totalBenefit += i.calculateProfit();
                System.out.println("The actual benefit of the Store is: "+ totalBenefit);
                
                Sale newsale = new Sale(i,b,today,today);
                sales.add(newsale);

                //adds & removes from the respective lists
                itemsSold.add(i);
                itemsAvailable.remove(i);
            }
            else{
                System.out.println("There's not enough money in the account");
            }         
        }
        
        //buy auction item
        if(i instanceof AuctionItem){
            
            b.buy(i);
            Package newpackage = i.assignBestPackage(packages);
                if(b.pay(i.getPricePlusTax()+newpackage.getPricePlusTax())){
                    b.pay(i.getPricePlusTax()+newpackage.getPricePlusTax());
                    s.sell(i);
                    Sale newsale = new Sale(i,b,today,today);
                    sales.add(newsale);

                    totalBenefit += i.calculateProfit();
                    itemsSold.add(i);
                    itemsAvailable.remove(i);

                    System.out.println("The item has been sold to "+ b.getName() + "for the following price"+ i.getPrice());
                    System.out.println("The current benefit is: "+ totalBenefit);
                }
                else{
                    System.out.println("There's not enough money in the account");
                }
            }
    }
 
    
    public static void main(String[] args) {
        // TODO code application logic here
        users = new LinkedList<>();
        itemsSold = new LinkedList<>();
        itemsAvailable = new LinkedList<>();
        packages = new LinkedList<>();
        sales = new LinkedList<>();
        
        
        double []size= new double[]{1.30, 1.2, 1.4};
        double cost = 1.323;
        double uprice = 1.5;
        int quantity = 1;
        double wPrice = 1.5;
        double weight = 2;
        double startinPrice = 1.25;
        double []size1= new double[3];
        
        User admin1 = new Administrator("Admin1 (admin)"," 193524", "17051998");
        User admin2 = new Administrator("Amin2 (admin)", "193448", "06011998");
        User seller1 = new Seller("Seller1", "0001", "0001", "0001");
        User seller2 = new Seller("Seller2", "0002", "0002", "0002");
        User buyer1 = new Buyer("Buyer1", "0003", "0003", "0003", 1000);
        User buyer2 = new Buyer("Buyer2", "0004", "0004", "0004", 1000);
        User store = new Seller("Store", "0005", "0005", "0005");
                
        users.add(admin1);
        users.add(admin2);
        users.add(seller1);
        users.add(seller2);
        users.add(buyer1);
        users.add(buyer2);
        
        Item unit1 = new UnitItem("A amb gana", "Music", size ,cost, uprice, quantity);
        Item unit2 = new UnitItem("AQuim", "Music", size , cost,uprice, quantity);
        Item weight1 = new WeightedItem("Book1", "Book", size, cost, wPrice, weight);
        Item weight2 = new WeightedItem("Book2", "Book", size, cost, wPrice, weight);
        Item weight3 = new WeightedItem("Gold watch", "Really Expensive", size, cost, wPrice, 10);
        Item auction1 = new AuctionItem("Pc1", "PC", size, cost, startinPrice, today);
        Item auction2 = new AuctionItem("Pc2", "PC", size, cost, startinPrice, today);
        Item auction3 = new AuctionItem("Pc3", "PC", size, cost, startinPrice, today);
        
    
        itemsAvailable.add(unit1);
        itemsAvailable.add(unit2);
        itemsAvailable.add(weight1);
        itemsAvailable.add(weight2);
        itemsAvailable.add(weight3);
        
        Package env1 = new Envelope(1,2,1, "A1");
        Package env2 = new Envelope(2,1,2, "A2");
        Package box1 = new Box(1,2,3,3);
        Package box2 = new Box(1,2,4,4);
        
        packages.add(env1);
        packages.add(env2);
        packages.add(box1);
        packages.add(box2);
        
        //buy a unit item (unit2)
        sell((Buyer)buyer1, ((Seller)store), unit2);
        
        //buy a weighted item (weight1)
        sell((Buyer)buyer1, ((Seller)store), weight2);
        
        //At this moment the buyer1 has bought unit2 and weight1.
        System.out.println("\n\n");
        
        System.out.println("The seller: "+seller1.getName()+ " is putting the following items up for auction: "+ auction1.getName()+", "+ auction2.getName());
        ((Seller)seller1).availableItems.add(auction1);
        ((Seller)seller1).availableItems.add(auction2);
        
        itemsAvailable.add(auction1);
        itemsAvailable.add(auction2);
        
        
       
        System.out.println("The item has been added to the auction");
        System.out.println("\n\n");
        
        //At this moment two items had been added to the auction and to the seller1 list.
       
        //place a bid
        System.out.println("The buyer: "+buyer1.getName()+ " is putting a bid in the following item: "+ auction1.getName()+ "  (where all is correct)");
        double bid1= 30.5;
        
        Date date1 = new java.util.Date();
        date1.setDate(20);
        date1.setMonth(04);
        date1.setYear(117);
        
        System.out.println(date1);
              
        if(!((AuctionItem)auction1).frozen(date1)){
            if(((AuctionItem)auction1).getPrice()<bid1){
                ((AuctionItem)auction1).makeBid(((Buyer)buyer1), bid1);
                System.out.println("The bid has been placed in the item "+ ((AuctionItem)auction1).getName()
                               + " so the current price is " + ((AuctionItem)auction1).getPrice());
            }
            else{
                System.out.println("You need to raise your bid");
            }
        }
        else{
            System.out.println("The bidding period is over");
            
        }
        
        System.out.println("\n");
        
        //malament de data 
        System.out.println("The buyer: "+buyer1.getName()+ " is putting a bid in the following item: "+ auction2.getName()+ "  (where the date is wrong)");
        double bid2= 25.5;
        
        
        Date date2 = new java.util.Date();
        date2.setDate(20);
        date2.setMonth(04);
        date2.setYear(118);
        
        
        if(!((AuctionItem)auction2).frozen(date2)){
            if(((AuctionItem)auction2).getPrice()<bid2){
                ((AuctionItem)auction2).makeBid(((Buyer)buyer1), bid2);
                System.out.println("The bid has been placed in the item "+ ((AuctionItem)auction2).getName()
                               + " so the current price is " + ((AuctionItem)auction2).getPrice());
            }
            else{
                System.out.println("You need to raise your bid");
            }
        }
        else{
            System.out.println("The bidding period is over");
        }
        System.out.println("\n");
        
        //price wrong
        System.out.println("The buyer: "+buyer1.getName()+ " is putting a bid in the following item: "+ auction1.getName()+ "  (where the price is wrong)");

        double bid3= 0.5;
        
        Date date3 = new java.util.Date();
        date3.setDate(04);
        date3.setMonth(01);
        date3.setYear(116);
        
        if(!((AuctionItem)auction2).frozen(date3)){
            if(((AuctionItem)auction2).getPrice()<bid3){
                ((AuctionItem)auction2).makeBid(((Buyer)buyer1), bid3);
                System.out.println("The bid has been placed in the item "+ ((AuctionItem)auction2).getName()
                               + " so the current price is " + ((AuctionItem)auction2).getPrice());
            }
            else{
                System.out.println("You need to raise your bid");
            }
        }
        else{
            System.out.println("The bidding period is over");
        }

        // manage auction
        
        System.out.println("\n\n");
        
        System.out.println("Admin: "+admin1.getName()+ " is managing an auction for the following item: "+ auction1.getName()+ "  (where all is correct)");

        
        
        Date date4 = new java.util.Date();
        date4.setDate(10);
        date4.setMonth(12);
        date4.setYear(117);
        
        
        
        if(!((Administrator)admin1).manageAuction(((AuctionItem)auction1), date4)){
            System.out.println("The item is still available in the auction");
        }
        else{
            Buyer buyerAuction =((AuctionItem)auction1).getBuyer();
            if(buyerAuction == null){
                System.out.println("There is no bidder in the item, thus it gets removed.");
                ((Seller)seller1).availableItems.remove(auction1);
                itemsAvailable.remove(auction1);
            }
            else{
               
               sell(buyerAuction, ((Seller)seller1), auction1); 
                
            }    
        }
        System.out.println("\n");
        System.out.println("Admin: "+admin1.getName()+ " is managing an auction for the following item: "+ auction1.getName()+ "  (where there is no bidder)");

        
        
        if(!((Administrator)admin1).manageAuction(((AuctionItem)auction2), date4)){
            System.out.println("The item is still available in the auction");
        }
        else{
            Buyer buyerAuction =((AuctionItem)auction2).getBuyer();
            if(buyerAuction == null){
                System.out.println("There is no bidder in the item, thus it gets removed.");
                ((Seller)seller1).availableItems.remove(auction2);
                itemsAvailable.remove(auction2);
                
            }
            else{
                sell(buyerAuction, ((Seller)seller1), auction1);
                
                System.out.println("The item has been sold to "+ buyerAuction.getName() + " for the following price "+ auction2.getPrice());
                System.out.println("The current benefit is: "+ totalBenefit);
            }    
        } 
        System.out.println("\n");
        System.out.println("Admin: "+admin1.getName()+ " is managing an auction for the following item: "+ auction1.getName()+ "  (where it's still available in the auction)");

        
        
        Date date5 = new java.util.Date();
        date1.setDate(02);
        date1.setMonth(01);
        date1.setYear(115);
        
        if(!((Administrator)admin1).manageAuction(((AuctionItem)auction2), date5)){
            System.out.println("The item is still available in the auction");
        }
        else{
            Buyer buyerAuction =((AuctionItem)auction2).getBuyer();
            if(buyerAuction == null){
                System.out.println("There is no bidder in the item, thus it gets removed.");
                ((Seller)seller1).availableItems.remove(auction2);
                itemsAvailable.remove(auction2);
                
            }
            else{
                sell(buyerAuction, ((Seller)seller1), auction1);
                
                System.out.println("The item has been sold to "+ buyerAuction.getName() + "for the following price"+ auction2.getPrice()+ "\n");
                System.out.println("The current benefit is: "+ totalBenefit);
            }    
        }
        
        
        //expell user
        System.out.println("\n");
        System.out.println("The admin: "+ admin1.getName()+" is deleting the following user: "+ buyer2.getName());
        if(((Administrator)admin1).expel(buyer2)){
            users.remove(buyer2);
            System.out.println("The user has been deleted\n");
        }
        
        //incrementingDate
        System.out.println("\n");
        Date date6 = new java.util.Date();
        date6.setDate(06);
        date6.setMonth(01);
        date6.setYear(119);
        
        incrementDate(date6);
        
        System.out.println("Incrementing the date, the new date is: ");
        System.out.print(date6);
        System.out.println("\n\n\n");
        
        
        Item auction4 = new AuctionItem("Pc4", "PC", size, cost, 40.0, date6);
        
        System.out.println("Admin: "+admin1.getName()+ " is managing an auction for the following item: "+ auction4.getName()); 
            if(!((Administrator)admin1).manageAuction(((AuctionItem)auction4), date6)){
            System.out.println("The item is still available in the auction");
            }
            else{
                Buyer buyerAuction =((AuctionItem)auction1).getBuyer();
                if(buyerAuction == null){
                    System.out.println("There is no bidder in the item, thus it gets removed.");
                    ((Seller)seller1).availableItems.remove(auction4);
                    itemsAvailable.remove(auction4);
                }
                else{              
                    sell(buyerAuction, ((Seller)seller1), auction4); 
                System.out.println("The item has been sold to "+ buyerAuction.getName() + "for the following price"+ auction4.getPrice());
                System.out.println("The current benefit is: "+ totalBenefit);
            }    
        }
        System.out.println("\n");
        System.out.println("Admin: "+admin1.getName()+ " is managing an auction for the following item: "+ auction4.getName()+ "(where there is no bidder)");

        
        
        if(!((Administrator)admin1).manageAuction(((AuctionItem)auction2), date4)){
            System.out.println("The item is still available in the auction");
        }
        else{
            Buyer buyerAuction =((AuctionItem)auction2).getBuyer();
            if(buyerAuction == null){
                System.out.println("There is no bidder in the item, thus it gets removed.");
                ((Seller)seller1).availableItems.remove(auction2);
                itemsAvailable.remove(auction2);
                
            }
            else{
                sell(buyerAuction, ((Seller)seller1), auction1);
                
                System.out.println("The item has been sold to "+ buyerAuction.getName() + " for the following price "+ auction2.getPrice());
                System.out.println("The current benefit is: "+ totalBenefit);
            }
            System.out.println("\n");
        }
        
        
        
        
        
        //buying item 1 on a new date
       
        sell((Buyer)buyer1, ((Seller)store), unit2);
        
        
        
        
        
        
        
        
        //sort
        
        int k;
        System.out.println("List of Items Availables before sorting");
        for(k=0; k<itemsAvailable.size(); k++){
        System.out.printf("%s\n",itemsAvailable.get(k).getName()+" "+itemsAvailable.get(k).getPricePlusTax()+"$");
        }
        
        System.out.println("\n");
        
        Collections.sort(itemsAvailable);
        System.out.println("List of Items Availables after sorting");
        for(k=0; k<itemsAvailable.size(); k++){
        
        System.out.printf("%s\n",itemsAvailable.get(k).getName()+" "+itemsAvailable.get(k).getPricePlusTax()+"$");
        }
        
        System.out.println("\n");
        
        
        System.out.println("List of Sales before sorting");
        for(k=0; k<sales.size(); k++){
        System.out.printf("%s\n",sales.get(k).getItem().getName()+" "+sales.get(k).getSaleDate());
        }
        
        System.out.println("\n");
        
        Collections.sort(sales);
        
        System.out.println("List of Sales after sorting");
        for(k=0; k<sales.size(); k++){
        System.out.printf("%s\n",sales.get(k).getItem().getName()+" "+sales.get(k).getSaleDate());
        }
        
        
        System.out.println("\nPerforming Inventory...\n");
        ((Administrator)admin1).Inventory(itemsAvailable);
        
        
    }
        
    }
    

