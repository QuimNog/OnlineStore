/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quim
 */
public interface Taxable {
    public static final double IVA = 0.16;
    public double getPrice();
    public double getPriceOnlyTax();
    public double getPricePlusTax();
    public double sumTotalTax(Taxable t);
}
