/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Shoe;
import entity.Purchase;
import entity.Customer;
import entity.Income;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class SaverToBase implements Keeping{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShoeStoreSKTVp20PU");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();

    @Override
    public void saveShoes(List<Shoe> shoes) {
        tx.begin();
            for (int i = 0; i < shoes.size(); i++) {
                if(shoes.get(i).getId() == null){
                    em.persist(shoes.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Shoe> loadShoes() {
        List<Shoe> shoes = null;
        try {
            shoes = em.createQuery("SELECT shoe FROM Shoe shoe")
                .getResultList();
        } catch (Exception e) {
            shoes = new ArrayList<>();
        }
        return shoes;
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        tx.begin();
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i).getId() == null){
                    em.persist(customers.get(i));
                }
            }
        tx.commit();
    }
    @Override
    public List<Customer> loadCustomers() {
        List<Customer> customers = null;
        try {
            customers = em.createQuery("SELECT customer FROM Customer customer")
                .getResultList();
        } catch (Exception e) {
            customers = new ArrayList<>();
        }
        return customers;
    }

    @Override
    public void savePurchases(List<Purchase> purchases) {
        tx.begin();
            for (int i = 0; i < purchases.size(); i++) {
                if(purchases.get(i).getId() == null){
                    em.persist(purchases.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Purchase> loadPurchases() {
        List<Purchase> purchases = null;
        try {
            purchases = em.createQuery("SELECT purchase FROM Purchase purchase")
                .getResultList();
        } catch (Exception e) {
            purchases = new ArrayList<>();
        }
        return purchases;
    }

    @Override
    public void saveIncome(Income income) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Income> loadIncome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}