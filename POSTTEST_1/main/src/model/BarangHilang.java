package model;

public class BarangHilang {
    private int id;
    private String namaBarang;
    private String deskripsi;
    private String lokasiHilang;
    private String tanggalHilang;
    private String kontakPelapor;
    private boolean sudahDitemukan;

    public BarangHilang(int id, String namaBarang, String deskripsi, String lokasiHilang, String tanggalHilang, String kontakPelapor) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.deskripsi = deskripsi;
        this.lokasiHilang = lokasiHilang;
        this.tanggalHilang = tanggalHilang;
        this.kontakPelapor = kontakPelapor;
        this.sudahDitemukan = false;
    }

    public BarangHilang() {
        this.id = 0;
        this.namaBarang = "Barang Default";
        this.deskripsi = "Deskripsi Default";
        this.lokasiHilang = "Lokasi Default";
        this.tanggalHilang = "YYYY-MM-DD";
        this.kontakPelapor = "Kontak Default";
        this.sudahDitemukan = false;
    }

    public int getId() { return this.id; }
    public String getNamaBarang() { return this.namaBarang; }
    public String getDeskripsi() { return this.deskripsi; }
    public String getLokasiHilang() { return this.lokasiHilang; }
    public String getTanggalHilang() { return this.tanggalHilang; }
    public String getKontakPelapor() { return this.kontakPelapor; }
    public boolean isSudahDitemukan() { return this.sudahDitemukan; }

    public void setId(int id) { this.id = id; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public void setLokasiHilang(String lokasiHilang) { this.lokasiHilang = lokasiHilang; }
    public void setTanggalHilang(String tanggalHilang) { this.tanggalHilang = tanggalHilang; }
    public void setKontakPelapor(String kontakPelapor) { this.kontakPelapor = kontakPelapor; }
    public void setSudahDitemukan(boolean sudahDitemukan) { this.sudahDitemukan = sudahDitemukan; }

    public void tampilkanInfo() {
        String status = this.sudahDitemukan ? "Ditemukan" : "Belum Ditemukan";

        System.out.println("ID: " + this.id +
                ", Barang: " + this.namaBarang +
                ", Deskripsi: " + this.deskripsi +
                ", Lokasi Hilang: " + this.lokasiHilang +
                ", Tanggal Hilang: " + this.tanggalHilang +
                ", Kontak: " + this.kontakPelapor +
                ", Status: " + status);
    }
}