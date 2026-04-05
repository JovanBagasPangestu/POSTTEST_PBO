import model.BarangDitemukan;
import model.BarangHilang;
import model.Pengguna;
import service.BarangDitemukanService;
import service.BarangHilangService;
import service.PenggunaService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BarangHilangService barangHilangService = new BarangHilangService();
    private static BarangDitemukanService barangDitemukanService = new BarangDitemukanService();
    private static PenggunaService penggunaService = new PenggunaService();

    // Method untuk mencegah error kalau user memasukkan huruf saat diminta angka
    private static int inputAngka() {
        while (!scanner.hasNextInt()) {
            System.out.println("Inputnya harus berupa angka ya. Silakan coba lagi.");
            scanner.next();
            System.out.print("Masukkan angka: ");
        }
        int angka = scanner.nextInt();
        scanner.nextLine();
        return angka;
    }

    private static boolean inputStatus() {
        boolean valid = false;
        boolean status = false;

        while (!valid) {
            String masukan = scanner.nextLine();
            if (masukan.equalsIgnoreCase("sudah")) {
                status = true;
                valid = true;
            } else if (masukan.equalsIgnoreCase("belum")) {
                status = false;
                valid = true;
            } else {
                System.out.println("Wah, sepertinya salah ketik.");
                System.out.print("Pastikan hanya mengetik 'sudah' atau 'belum': ");
            }
        }
        return status;
    }

    public static void main(String[] args) {
        boolean aplikasiJalan = true;

        while (aplikasiJalan) {
            String judul = "sistem manajemen barang hilang dan ditemukan";
            System.out.println("\n==============================================");
            System.out.println(StringUtils.upperCase(judul));;
            System.out.println("==============================================");
            System.out.println("1. Kelola Barang Hilang");
            System.out.println("2. Kelola Barang Ditemukan");
            System.out.println("3. Kelola Pengguna");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (masukkan angka): ");

            int pilihan = inputAngka();

            switch (pilihan) {
                case 1:
                    menuBarangHilang();
                    break;
                case 2:
                    menuBarangDitemukan();
                    break;
                case 3:
                    menuPengguna();
                    break;
                case 0:
                    aplikasiJalan = false;
                    System.out.println("Terima kasih telah menggunakan. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak ada di menu. pilih angka yang sesuai.");
            }
        }
    }

    // --- Menu Barang Hilang ---
    private static void menuBarangHilang() {
        boolean menuJalan = true;
        while (menuJalan) {
            System.out.println("\n--- Menu: Barang Hilang ---");
            System.out.println("1. Tambah Data Barang Hilang");
            System.out.println("2. Lihat Semua Barang Hilang");
            System.out.println("3. Cari Barang Hilang (berdasarkan ID)");
            System.out.println("4. Perbarui Data Barang Hilang");
            System.out.println("5. Hapus Data Barang Hilang");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");

            int pilihan = inputAngka();

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- Tambah Barang Hilang ---");
                    System.out.print("Nama Barang: ");
                    String nama = scanner.nextLine();
                    nama = StringUtils.capitalize(nama);
                    System.out.print("Deskripsi: ");
                    String deskripsi = scanner.nextLine();
                    System.out.print("Lokasi Hilang: ");
                    String lokasi = scanner.nextLine();
                    System.out.print("Tanggal Hilang (contoh: 23-01-2026): ");
                    String tanggal = scanner.nextLine();
                    System.out.print("Kontak Pelapor: ");
                    String kontak = scanner.nextLine();

                    BarangHilang barangBaru = new BarangHilang(0, nama, deskripsi, lokasi, tanggal, kontak);
                    barangHilangService.tambahBarangHilang(barangBaru);
                    break;
                case 2:
                    System.out.println("\n--- Daftar Barang Hilang ---");
                    List<BarangHilang> daftar = barangHilangService.getAllBarangHilang();
                    if (daftar.isEmpty()) {
                        System.out.println("Belum ada data barang hilang saat ini.");
                    } else {
                        for (int i = 0; i < daftar.size(); i++) {
                            daftar.get(i).tampilkanInfo();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Masukkan ID barang yang ingin dicari: ");
                    int idCari = inputAngka();
                    BarangHilang barangDitemukan = barangHilangService.getBarangHilangById(idCari);
                    if (barangDitemukan != null) {
                        System.out.println("Data ditemukan:");
                        barangDitemukan.tampilkanInfo();
                    } else {
                        System.out.println("Maaf, barang dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 4:
                    System.out.print("Masukkan ID barang yang ingin diperbarui: ");
                    int idUpdate = inputAngka();

                    BarangHilang barangLama = barangHilangService.getBarangHilangById(idUpdate);
                    if (barangLama != null) {
                        System.out.println("Data saat ini:");
                        barangLama.tampilkanInfo();

                        System.out.println("\nSilakan masukkan data baru (Wajib isi semua):");
                        System.out.print("Nama Barang: ");
                        barangLama.setNamaBarang(scanner.nextLine());
                        System.out.print("Deskripsi: ");
                        barangLama.setDeskripsi(scanner.nextLine());
                        System.out.print("Lokasi Hilang: ");
                        barangLama.setLokasiHilang(scanner.nextLine());
                        System.out.print("Tanggal Hilang: ");
                        barangLama.setTanggalHilang(scanner.nextLine());
                        System.out.print("Kontak Pelapor: ");
                        barangLama.setKontakPelapor(scanner.nextLine());

                        System.out.print("Apakah barang sudah ditemukan? (Ketik 'sudah' / 'belum'): ");
                        barangLama.setSudahDitemukan(inputStatus());

                        barangHilangService.updateBarangHilang(idUpdate, barangLama);
                    } else {
                        System.out.println("Maaf, barang dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 5:
                    System.out.print("Masukkan ID barang yang ingin dihapus: ");
                    int idHapus = inputAngka();
                    barangHilangService.hapusBarangHilang(idHapus);
                    break;
                case 0:
                    menuJalan = false;
                    break;
                default:
                    System.out.println("Pilihan tidak ada di menu. pilih angka yang sesuai.");
            }
        }
    }

    // --- Menu Barang Ditemukan ---
    private static void menuBarangDitemukan() {
        boolean menuJalan = true;
        while (menuJalan) {
            System.out.println("\n--- Menu: Barang Ditemukan ---");
            System.out.println("1. Tambah Data Barang Ditemukan");
            System.out.println("2. Lihat Semua Barang Ditemukan");
            System.out.println("3. Cari Barang Ditemukan (berdasarkan ID)");
            System.out.println("4. Perbarui Data Barang Ditemukan");
            System.out.println("5. Hapus Data Barang Ditemukan");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");

            int pilihan = inputAngka();

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- Tambah Barang Ditemukan ---");
                    System.out.print("Nama Barang: ");
                    String nama = scanner.nextLine();
                    nama = StringUtils.capitalize(nama);
                    System.out.print("Deskripsi: ");
                    String deskripsi = scanner.nextLine();
                    System.out.print("Lokasi Ditemukan: ");
                    String lokasi = scanner.nextLine();
                    System.out.print("Tanggal Ditemukan (contoh: 26-10-2026): ");
                    String tanggal = scanner.nextLine();
                    System.out.print("Kontak Penemu: ");
                    String kontak = scanner.nextLine();

                    BarangDitemukan barangBaru = new BarangDitemukan(0, nama, deskripsi, lokasi, tanggal, kontak);
                    barangDitemukanService.tambahBarangDitemukan(barangBaru);
                    break;
                case 2:
                    System.out.println("\n--- Daftar Barang Ditemukan ---");
                    List<BarangDitemukan> daftar = barangDitemukanService.getAllBarangDitemukan();
                    if (daftar.isEmpty()) {
                        System.out.println("Belum ada data barang ditemukan saat ini.");
                    } else {
                        for (int i = 0; i < daftar.size(); i++) {
                            daftar.get(i).tampilkanInfo();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Masukkan ID barang yang ingin dicari: ");
                    int idCari = inputAngka();
                    BarangDitemukan barangDitemukan = barangDitemukanService.getBarangDitemukanById(idCari);
                    if (barangDitemukan != null) {
                        System.out.println("Data ditemukan:");
                        barangDitemukan.tampilkanInfo();
                    } else {
                        System.out.println("Maaf, barang dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 4:
                    System.out.print("Masukkan ID barang yang ingin diperbarui: ");
                    int idUpdate = inputAngka();

                    BarangDitemukan barangLama = barangDitemukanService.getBarangDitemukanById(idUpdate);
                    if (barangLama != null) {
                        System.out.println("Data saat ini:");
                        barangLama.tampilkanInfo();

                        System.out.println("\nSilakan masukkan data baru (Wajib isi semua):");
                        System.out.print("Nama Barang: ");
                        barangLama.setNamaBarang(scanner.nextLine());
                        System.out.print("Deskripsi: ");
                        barangLama.setDeskripsi(scanner.nextLine());
                        System.out.print("Lokasi Ditemukan: ");
                        barangLama.setLokasiDitemukan(scanner.nextLine());
                        System.out.print("Tanggal Ditemukan: ");
                        barangLama.setTanggalDitemukan(scanner.nextLine());
                        System.out.print("Kontak Penemu: ");
                        barangLama.setKontakPenemu(scanner.nextLine());

                        System.out.print("Apakah barang sudah dikembalikan? (Ketik 'sudah' / 'belum'): ");
                        barangLama.setSudahDikembalikan(inputStatus());

                        barangDitemukanService.updateBarangDitemukan(idUpdate, barangLama);
                    } else {
                        System.out.println("Maaf, barang dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 5:
                    System.out.print("Masukkan ID barang yang ingin dihapus: ");
                    int idHapus = inputAngka();
                    barangDitemukanService.hapusBarangDitemukan(idHapus);
                    break;
                case 0:
                    menuJalan = false;
                    break;
                default:
                    System.out.println("Pilihan tidak ada di menu. pilih angka yang sesuai.");
            }
        }
    }

    // --- Menu Pengguna ---
    private static void menuPengguna() {
        boolean menuJalan = true;
        while (menuJalan) {
            System.out.println("\n--- Menu: Pengguna ---");
            System.out.println("1. Tambah Data Pengguna");
            System.out.println("2. Lihat Semua Pengguna");
            System.out.println("3. Cari Pengguna (berdasarkan ID)");
            System.out.println("4. Perbarui Data Pengguna");
            System.out.println("5. Hapus Data Pengguna");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");

            int pilihan = inputAngka();

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- Tambah Pengguna ---");
                    System.out.print("Nama Lengkap: ");
                    String nama = scanner.nextLine();
                    nama = StringUtils.capitalize(nama);
                    System.out.print("NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Pengguna penggunaBaru = new Pengguna(0, nama, nim, email);
                    penggunaService.tambahPengguna(penggunaBaru);
                    break;
                case 2:
                    System.out.println("\n--- Daftar Pengguna ---");
                    List<Pengguna> daftar = penggunaService.getAllPengguna();
                    if (daftar.isEmpty()) {
                        System.out.println("Belum ada data pengguna saat ini.");
                    } else {
                        for (int i = 0; i < daftar.size(); i++) {
                            daftar.get(i).tampilkanInfo();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Masukkan ID pengguna yang ingin dicari: ");
                    int idCari = inputAngka();
                    Pengguna penggunaDitemukan = penggunaService.getPenggunaById(idCari);
                    if (penggunaDitemukan != null) {
                        System.out.println("Data ditemukan:");
                        penggunaDitemukan.tampilkanInfo();
                    } else {
                        System.out.println("Maaf, pengguna dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 4:
                    System.out.print("Masukkan ID pengguna yang ingin diperbarui: ");
                    int idUpdate = inputAngka();

                    Pengguna penggunaLama = penggunaService.getPenggunaById(idUpdate);
                    if (penggunaLama != null) {
                        System.out.println("Data saat ini:");
                        penggunaLama.tampilkanInfo();

                        System.out.println("\nSilakan masukkan data baru (Wajib isi semua):");
                        System.out.print("Nama Lengkap: ");
                        penggunaLama.setNama(scanner.nextLine());
                        System.out.print("NIM: ");
                        penggunaLama.setNim(scanner.nextLine());
                        System.out.print("Email: ");
                        penggunaLama.setEmail(scanner.nextLine());

                        penggunaService.updatePengguna(idUpdate, penggunaLama);
                    } else {
                        System.out.println("Maaf, pengguna dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 5:
                    System.out.print("Masukkan ID pengguna yang ingin dihapus: ");
                    int idHapus = inputAngka();
                    penggunaService.hapusPengguna(idHapus);
                    break;
                case 0:
                    menuJalan = false;
                    break;
                default:
                    System.out.println("Pilihan tidak ada di menu. pilih angka yang sesuai.");
            }
        }
    }
}