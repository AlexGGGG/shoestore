/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Customer;
import entity.Income;
import entity.Purchase;
import entity.Shoe;
import java.util.List;

/**
 *
 * @author Mad Max
 */
public interface Keeping {
    public void saveShoes(List<Shoe> shoes);
    public List<Shoe> loadShoes();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void savePurchases(List<Purchase> purchases);
    public List<Purchase> loadPurchases();
    public void saveIncome(Income income);
    public List<Income> loadIncome();

}
