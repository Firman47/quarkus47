package org.acme.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
@Entity
public class Product extends PanacheEntity{

    public Long id;
    public String productname;
    public String harga;
    public String stok;

    @JoinColumn(name = "idVarian")
    @ManyToOne()
    private Varian varian ;
    
    @JoinColumn(name = "idWarna")
    @ManyToOne()
    private Warna warna ;
    public Warna getWarna() {
        return warna;
    }

    
    @OneToMany(mappedBy = "product")
    private List<Varian> varians = new ArrayList<>();

    public List<Varian> getVarians() {
        return varians;
    }

    public void setVarians(List<Varian> varians) {
        this.varians = varians;
    }

    public void setWarna(Warna warna) {
        this.warna = warna;
    }

    public Varian getVarian() {
        return varian;
    }

    public void setVarian(Varian varian) {
        this.varian = varian;
    }

    @Column(name = "is_delete")
    private boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
