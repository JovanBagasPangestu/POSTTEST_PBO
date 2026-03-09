package service;

import model.BarangDitemukan;
import java.util.ArrayList;
import java.util.List;

public class BarangDitemukanService {
    private final List<BarangDitemukan> daftarBarangDitemukan;
    private int nextIdBarangDitemukan;

    public BarangDitemukanService() {
        this.daftarBarangDitemukan = new ArrayList<>();
        this.nextIdBarangDitemukan = 1;
    }

    public void tambahBarangDitemukan(BarangDitemukan barang) {
        barang.setId(this.nextIdBarangDitemukan++);
        daftarBarangDitemukan.add(barang);
        System.out.println("Barang ditemukan berhasil ditambahkan dengan ID: " + barang.getId() + " - " + barang.getNamaBarang());
    }

    public List<BarangDitemukan> getAllBarangDitemukan() {
        List<BarangDitemukan> temp = new ArrayList<>();
        for (BarangDitemukan barang : daftarBarangDitemukan) {
            temp.add(barang);
        }
        return temp;
    }

    public BarangDitemukan getBarangDitemukanById(int id) {
        for (BarangDitemukan barang : daftarBarangDitemukan) {
            if (barang.getId() == id) {
                return barang;
            }
        }
        return null;
    }

    public boolean updateBarangDitemukan(int id, BarangDitemukan updatedBarang) {
        for (int i = 0; i < daftarBarangDitemukan.size(); i++) {
            if (daftarBarangDitemukan.get(i).getId() == id) {
                updatedBarang.setId(id);
                daftarBarangDitemukan.set(i, updatedBarang);
                System.out.println("Barang ditemukan dengan ID " + id + " berhasil diperbarui.");
                return true;
            }
        }
        System.out.println("Barang ditemukan dengan ID " + id + " tidak ditemukan.");
        return false;
    }

    public boolean hapusBarangDitemukan(int id) {
        for (int i = 0; i < daftarBarangDitemukan.size(); i++) {
            if (daftarBarangDitemukan.get(i).getId() == id) {
                daftarBarangDitemukan.remove(i);
                System.out.println("Barang ditemukan dengan ID " + id + " berhasil dihapus.");
                return true;
            }
        }
        System.out.println("Barang ditemukan dengan ID " + id + " tidak ditemukan.");
        return false;
    }
}