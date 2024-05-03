package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; 
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Product {

    @Id
    @GeneratedValue
    public Long id;
    public String productname;
    public String harga;
    public String stok;

    @OneToMany(mappedBy = "product")
    private List<Varian> varian = new ArrayList<>();
    

    public List<Varian> getVarian() {
        return varian;
    }

    public void setVarian(List<Varian> varian) {
        this.varian = varian;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productname;
    }

    public void setProductName(String productname) {
        this.productname = productname;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }


 }
