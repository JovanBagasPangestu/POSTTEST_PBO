package model;

public class Pengguna {
    private int id;
    private String nama;
    private String nim;
    private String email;

    public Pengguna(int id, String nama, String nim, String email) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.email = email;
    }

    public Pengguna() {
        this.id = 0;
        this.nama = "Nama Default";
        this.nim = "NIM Default";
        this.email = "Email Default";
    }

    public int getId() { return this.id; }
    public String getNama() { return this.nama; }
    public String getNim() { return this.nim; }
    public String getEmail() { return this.email; }

    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setNim(String nim) { this.nim = nim; }
    public void setEmail(String email) { this.email = email; }

    public void tampilkanInfo() {
        System.out.println("ID: " + this.id +
                ", Nama: " + this.nama +
                ", NIM: " + this.nim +
                ", Email: " + this.email);
    }
}