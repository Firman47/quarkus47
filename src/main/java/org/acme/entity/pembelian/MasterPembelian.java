package org.acme.entity.pembelian;

import java.util.Date;

import org.acme.entity.Product;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class MasterPembelian extends PanacheEntity {
    long id;
    String kode_pembelian;

    @JoinColumn(name = "idDetailPembelian")
    @ManyToOne()
    private DetailPembelian detailPembelian;
    String namaProduk;
    String hargaProduk;

    int jumlah;
    double total;
    double cash;
    double kembalian;
    Date tanggal;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getKode_pembelian() {
        return kode_pembelian;
    }
    public void setKode_pembelian(String kode_pembelian) {
        this.kode_pembelian = kode_pembelian;
    }
    public DetailPembelian getDetailPembelian() {
        return detailPembelian;
    }
    public void setDetailPembelian(DetailPembelian detailPembelian) {
        this.detailPembelian = detailPembelian;
    }
    public String getNamaProduk() {
        return namaProduk;
    }
    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }
    public String getHargaProduk() {
        return hargaProduk;
    }
    public void setHargaProduk(String hargaProduk) {
        this.hargaProduk = hargaProduk;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public double getCash() {
        return cash;
    }
    public void setCash(double cash) {
        this.cash = cash;
    }
    public double getKembalian() {
        return kembalian;
    }
    public void setKembalian(double kembalian) {
        this.kembalian = kembalian;
    }
    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }



    
    public String getNomorTransaksi() {
        return kode_pembelian;
    }

    public void setNomorTransaksi(String kode_pembelian) {
        this.kode_pembelian = kode_pembelian;
    }

    public Date getTanggalTransaksi() {
        return tanggal;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggal = tanggal;
    }

    @PrePersist
    public void prePersist() {
        // Mengatur nomor transaksi otomatis
        // Anda dapat menyesuaikan dengan logika yang sesuai, misalnya mengambil nomor transaksi dari generator unik, dsb.
        this.kode_pembelian = generateNomorTransaksi();

        // Mengatur tanggal transaksi otomatis
        this.tanggal = new Date(); // Menggunakan tanggal dan waktu saat ini

        // Menghitung total harga
        // calculateTotalHarga();
    }

    private String generateNomorTransaksi() {
        // Logika untuk menghasilkan nomor transaksi otomatis
        // Misalnya, Anda dapat menggunakan kombinasi tanggal, waktu, atau format khusus lainnya
        // Contoh sederhana: "TRX" + timestamp
        return "PMB-" + System.currentTimeMillis();
    }

}
