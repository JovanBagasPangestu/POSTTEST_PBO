package service;

import model.BarangHilang;
import java.util.ArrayList;
import java.util.List;

public class BarangHilangService {
    private final List<BarangHilang> daftarBarangHilang;
    private int nextIdBarangHilang;

    public BarangHilangService() {
        this.daftarBarangHilang = new ArrayList<>();
        this.nextIdBarangHilang = 1;
    }

    public void tambahBarangHilang(BarangHilang barang) {
        barang.setId(this.nextIdBarangHilang++);
        daftarBarangHilang.add(barang);
        System.out.println("Barang hilang berhasil ditambahkan dengan ID: " + barang.getId() + " - " + barang.getNamaBarang());
    }

    // READ
    public List<BarangHilang> getAllBarangHilang() {
        List<BarangHilang> temp = new ArrayList<>();
        for (BarangHilang barang : daftarBarangHilang) {
            temp.add(barang);
        }
        return temp;
    }

    public BarangHilang getBarangHilangById(int id) {
        for (BarangHilang barang : daftarBarangHilang) {
            if (barang.getId() == id) {
                return barang;
            }
        }
        return null;
    }

    public boolean updateBarangHilang(int id, BarangHilang updatedBarang) {
        for (int i = 0; i < daftarBarangHilang.size(); i++) {
            if (daftarBarangHilang.get(i).getId() == id) {
                updatedBarang.setId(id);
                daftarBarangHilang.set(i, updatedBarang);
                System.out.println("Barang hilang dengan ID " + id + " berhasil diperbarui.");
                return true;
            }
        }
        System.out.println("Barang hilang dengan ID " + id + " tidak ditemukan.");
        return false;
    }

    public boolean hapusBarangHilang(int id) {
        for (int i = 0; i < daftarBarangHilang.size(); i++) {
            if (daftarBarangHilang.get(i).getId() == id) {
                daftarBarangHilang.remove(i);
                System.out.println("Barang hilang dengan ID " + id + " berhasil dihapus.");
                return true;
            }
        }
        System.out.println("Barang hilang dengan ID " + id + " tidak ditemukan.");
        return false;
    }
}