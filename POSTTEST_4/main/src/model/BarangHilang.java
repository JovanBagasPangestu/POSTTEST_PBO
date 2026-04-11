package model;

public class BarangHilang extends Barang {
    private String lokasiHilang;
    private String tanggalHilang;
    private String kontakPelapor;
    private boolean sudahDitemukan;

    protected String kategori;
    String status;

    public BarangHilang(int id, String namaBarang, String deskripsi,
                        String lokasiHilang, String tanggalHilang, String kontakPelapor) {

        super(id, namaBarang, deskripsi);
        this.lokasiHilang = lokasiHilang;
        this.tanggalHilang = tanggalHilang;
        this.kontakPelapor = kontakPelapor;
        this.sudahDitemukan = false;
    }

    public BarangHilang() {
        super(0, "Barang Default", "Deskripsi Default");
        this.lokasiHilang = "Lokasi Default";
        this.tanggalHilang = "YYYY-MM-DD";
        this.kontakPelapor = "Kontak Default";
        this.sudahDitemukan = false;
    }

    // Getter
    public String getLokasiHilang() { return lokasiHilang; }
    public String getTanggalHilang() { return tanggalHilang; }
    public String getKontakPelapor() { return kontakPelapor; }
    public boolean isSudahDitemukan() { return sudahDitemukan; }

    // Setter
    public void setLokasiHilang(String lokasiHilang) { this.lokasiHilang = lokasiHilang; }
    public void setTanggalHilang(String tanggalHilang) { this.tanggalHilang = tanggalHilang; }
    public void setKontakPelapor(String kontakPelapor) { this.kontakPelapor = kontakPelapor; }
    public void setSudahDitemukan(boolean sudahDitemukan) { this.sudahDitemukan = sudahDitemukan; }

    @Override
    public void tampilkanInfo() {
        System.out.println("=== Barang Hilang ===");
        super.tampilkanInfo();

        String status = sudahDitemukan ? "Ditemukan" : "Belum Ditemukan";

        System.out.println("Lokasi: " + lokasiHilang +
                ", Tanggal: " + tanggalHilang +
                ", Status: " + status);
    }
}