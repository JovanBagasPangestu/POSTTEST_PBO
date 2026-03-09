package model;

public class BarangDitemukan {
    private int id;
    private String namaBarang;
    private String deskripsi;
    private String lokasiDitemukan;
    private String tanggalDitemukan;
    private String kontakPenemu;
    private boolean sudahDikembalikan;

    public BarangDitemukan(int id, String namaBarang, String deskripsi, String lokasiDitemukan, String tanggalDitemukan, String kontakPenemu) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.deskripsi = deskripsi;
        this.lokasiDitemukan = lokasiDitemukan;
        this.tanggalDitemukan = tanggalDitemukan;
        this.kontakPenemu = kontakPenemu;
        this.sudahDikembalikan = false;
    }

    public BarangDitemukan() {
        this.id = 0;
        this.namaBarang = "Barang Default";
        this.deskripsi = "Deskripsi Default";
        this.lokasiDitemukan = "Lokasi Default";
        this.tanggalDitemukan = "YYYY-MM-DD";
        this.kontakPenemu = "Kontak Default";
        this.sudahDikembalikan = false;
    }

    public int getId() { return this.id; }
    public String getNamaBarang() { return this.namaBarang; }
    public String getDeskripsi() { return this.deskripsi; }
    public String getLokasiDitemukan() { return this.lokasiDitemukan; }
    public String getTanggalDitemukan() { return this.tanggalDitemukan; }
    public String getKontakPenemu() { return this.kontakPenemu; }
    public boolean isSudahDikembalikan() { return this.sudahDikembalikan; }

    public void setId(int id) { this.id = id; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public void setLokasiDitemukan(String lokasiDitemukan) { this.lokasiDitemukan = lokasiDitemukan; }
    public void setTanggalDitemukan(String tanggalDitemukan) { this.tanggalDitemukan = tanggalDitemukan; }
    public void setKontakPenemu(String kontakPenemu) { this.kontakPenemu = kontakPenemu; }
    public void setSudahDikembalikan(boolean sudahDikembalikan) { this.sudahDikembalikan = sudahDikembalikan; }

    public void tampilkanInfo() {
        String status = this.sudahDikembalikan ? "Dikembalikan" : "Belum Dikembalikan";

        System.out.println("ID: " + this.id +
                ", Barang: " + this.namaBarang +
                ", Deskripsi: " + this.deskripsi +
                ", Lokasi Ditemukan: " + this.lokasiDitemukan +
                ", Tanggal Ditemukan: " + this.tanggalDitemukan +
                ", Kontak Penemu: " + this.kontakPenemu +
                ", Status: " + status);
    }
}