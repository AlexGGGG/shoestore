/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mad Max
 */
@Entity
public class Shoe implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shoeFirm;
    private double shoeSize;
    private double shoePrice;
    private int quantity;
    private int count;

    public Shoe() {
        
    }

    public String getShoeFirm() {
        return shoeFirm;
    }

    public void setShoeFirm(String shoeFirm) {
        this.shoeFirm = shoeFirm;
    }

    public double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(double shoeSize) {
        this.shoeSize = shoeSize;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }
    
    
    @Override
    public String toString() {
        return "Shoe{" 
                + "shoeFirm=" + shoeFirm
                + ", shoeSize=" + shoeSize 
                + ", shoePrice=" + shoePrice
                + ", quantity=" + quantity 
                + ", count=" + count 
                + '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.shoeFirm);
        hash = (int) (13 * hash + this.shoePrice);
        hash = 13 * hash + this.quantity;
        hash = 13 * hash + this.count;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shoe other = (Shoe) obj;
        if (this.shoePrice != other.shoePrice) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.shoePrice, other.shoePrice)) {
            return false;
        }
        
        return true;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
