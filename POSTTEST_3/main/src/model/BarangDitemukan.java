package model;

public class BarangDitemukan extends Barang {
    private String lokasiDitemukan;
    private String tanggalDitemukan;
    private String kontakPenemu;
    private boolean sudahDikembalikan;

    protected String kategori;
    String kondisi;

    public BarangDitemukan(int id, String namaBarang, String deskripsi,
                           String lokasiDitemukan, String tanggalDitemukan, String kontakPenemu) {

        super(id, namaBarang, deskripsi); // ambil dari parent
        this.lokasiDitemukan = lokasiDitemukan;
        this.tanggalDitemukan = tanggalDitemukan;
        this.kontakPenemu = kontakPenemu;
        this.sudahDikembalikan = false;
    }

    // Constructor default
    public BarangDitemukan() {
        super(0, "Barang Default", "Deskripsi Default");
        this.lokasiDitemukan = "Lokasi Default";
        this.tanggalDitemukan = "YYYY-MM-DD";
        this.kontakPenemu = "Kontak Default";
        this.sudahDikembalikan = false;
    }

    // Getter
    public String getLokasiDitemukan() { return lokasiDitemukan; }
    public String getTanggalDitemukan() { return tanggalDitemukan; }
    public String getKontakPenemu() { return kontakPenemu; }
    public boolean isSudahDikembalikan() { return sudahDikembalikan; }

    // Setter
    public void setLokasiDitemukan(String lokasiDitemukan) { this.lokasiDitemukan = lokasiDitemukan; }
    public void setTanggalDitemukan(String tanggalDitemukan) { this.tanggalDitemukan = tanggalDitemukan; }
    public void setKontakPenemu(String kontakPenemu) { this.kontakPenemu = kontakPenemu; }
    public void setSudahDikembalikan(boolean sudahDikembalikan) { this.sudahDikembalikan = sudahDikembalikan; }

    @Override
    public void tampilkanInfo() {
        String status = sudahDikembalikan ? "Dikembalikan" : "Belum Dikembalikan";

        System.out.println("=== Barang Ditemukan ===");
        System.out.println("ID: " + id +
                ", Nama: " + namaBarang +
                ", Deskripsi: " + deskripsi +
                ", Lokasi: " + lokasiDitemukan +
                ", Status: " + status);
    }
}