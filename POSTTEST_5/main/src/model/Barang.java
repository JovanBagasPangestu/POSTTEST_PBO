package model;

public abstract class Barang {
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

    // Abstract Methode
    public abstract void tampilkanInfo();

    // OVERLOADING
    public void tampilkanInfo(boolean detail) {
        if (detail) {
            System.out.println("ID: " + id +
                    ", Nama: " + namaBarang +
                    ", Deskripsi: " + deskripsi);
        } else {
            System.out.println("Nama: " + namaBarang);
        }
    }

    public void tampilkanInfo(String prefix) {
        System.out.println(prefix + " " + namaBarang);}
}