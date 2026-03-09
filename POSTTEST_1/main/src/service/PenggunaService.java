package service;

import model.Pengguna;
import java.util.ArrayList;
import java.util.List;

public class PenggunaService {
    private final List<Pengguna> daftarPengguna;
    private int nextIdPengguna; // Untuk simulasi auto-increment ID

    public PenggunaService() {
        this.daftarPengguna = new ArrayList<>();
        this.nextIdPengguna = 1;
    }

    public void tambahPengguna(Pengguna pengguna) {
        pengguna.setId(this.nextIdPengguna++);
        daftarPengguna.add(pengguna);
        System.out.println("Pengguna berhasil ditambahkan dengan ID: " + pengguna.getId() + " - " + pengguna.getNama());
    }

    public List<Pengguna> getAllPengguna() {
        List<Pengguna> temp = new ArrayList<>();
        for (Pengguna pengguna : daftarPengguna) {
            temp.add(pengguna);
        }
        return temp;
    }

    public Pengguna getPenggunaById(int id) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getId() == id) {
                return pengguna;
            }
        }
        return null;
    }

    public boolean updatePengguna(int id, Pengguna updatedPengguna) {
        for (int i = 0; i < daftarPengguna.size(); i++) {
            if (daftarPengguna.get(i).getId() == id) {
                updatedPengguna.setId(id);
                daftarPengguna.set(i, updatedPengguna);
                System.out.println("Pengguna dengan ID " + id + " berhasil diperbarui.");
                return true;
            }
        }
        System.out.println("Pengguna dengan ID " + id + " tidak ditemukan.");
        return false;
    }

    public boolean hapusPengguna(int id) {
        for (int i = 0; i < daftarPengguna.size(); i++) {
            if (daftarPengguna.get(i).getId() == id) {
                daftarPengguna.remove(i);
                System.out.println("Pengguna dengan ID " + id + " berhasil dihapus.");
                return true;
            }
        }
        System.out.println("Pengguna dengan ID " + id + " tidak ditemukan.");
        return false;
    }
}