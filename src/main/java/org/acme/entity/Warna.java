package org.acme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="warna")
public class Warna {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String warna;
    
    @ManyToOne
    @JoinColumn(name = "varian_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_varian_varian_d"))
    private Varian varian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        warna = warna;
    }

    public Varian getVarian() {
        return varian;
    }

    public void setVarian(Varian varian) {
        this.varian = varian;
    }

}
