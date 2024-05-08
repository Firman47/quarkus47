package org.acme.entity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
// import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "varian")
public class Varian extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String tipe;
    
    @Column(name = "is_deleted")
    private boolean isDeleted;

    
    @OneToMany(mappedBy = "varian")
    private List<Warna> warna = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_product_product_id"))
    private Product product;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTipe() {
        return tipe;
    }
    
    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
    public List<Warna> getWarna() {
        return warna;
    }
    
    public void setWarna(List<Warna> warna) {
        this.warna = warna;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


}
