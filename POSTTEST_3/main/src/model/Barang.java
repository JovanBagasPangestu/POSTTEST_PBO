package model;

public class Barang {
    protected int id;
    protected String namaBarang;
    protected String deskripsi;

    public Barang(int id, String namaBarang, String deskripsi) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.deskripsi = deskripsi;
    }

    public Barang() {}

    public int getId() { return id; }
    public String getNamaBarang() { return namaBarang; }
    public String getDeskripsi() { return deskripsi; }

    public void setId(int id) { this.id = id; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public void tampilkanInfo() {
        System.out.println("ID: " + id +
                ", Nama: " + namaBarang +
                ", Deskripsi: " + deskripsi);
    }
}