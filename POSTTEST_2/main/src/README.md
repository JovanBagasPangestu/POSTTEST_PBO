**Laporan Posttest**

**Sistem Manajemen Barang Hilang dan Ditemukan di Lingkungan Kampus**

**Nama : Jovan Bagas Pangestu**

**NIM: 2409106071**

1. Sistem Manajemen Barang Hilang dan Ditemukan di Lingkungan Kampus adalah aplikasi berbasis konsol yang dirancang untuk membantu mengelola laporan barang hilang dan barang ditemukan di lingkungan akademis. Aplikasi ini dibangun dengan mengimplementasikan konsep dasar Pemrograman Berorientasi Objek (PBO), dengan fokus utama pada perancangan arsitektur Class, utilisasi Constructor, dan pengelolaan data secara dinamis.


2. Fitur Utama
   Aplikasi ini memiliki instrumen dasar untuk mengelola data barang hilang, barang ditemukan, dan pengguna, yang keseluruhannya dikelola secara non-persisten di dalam memori. Fitur-fitur tersebut meliputi:

CRUD (Create, Read, Update, Delete) Data:

Barang Hilang= Menambahkan laporan, menampilkan seluruh data, mencari berdasarkan ID, memperbarui status informasi, dan menghapus laporan.

Barang Ditemukan= Menambahkan laporan, menampilkan seluruh data, mencari berdasarkan ID, memperbarui status pengembalian, dan menghapus laporan.

Pengguna (Master Data)= Menambahkan data profil, menampilkan seluruh entitas pengguna, mencari berdasarkan ID, memperbarui informasi kontak, dan menghapus data pengguna.

Sistem Navigasi Berulang (Looping)= Program beroperasi menggunakan perulangan while konvensional untuk mempertahankan siklus hidup aplikasi (application lifecycle) hingga pengguna menginisiasi perintah terminasi.

Validasi Input Defensif= Sistem dilengkapi dengan mekanisme isolasi galat (error handling berbasis logika) untuk mencegah InputMismatchException saat terjadi kesalahan pengetikan tipe data oleh pengguna.

Manajemen ID Otomatis= ID identifikasi untuk setiap entitas akan di- generate secara otomatis menggunakan operator increment di dalam Service saat instansiasi data baru terjadi.

3. Struktur Proyek
   Proyek ini mengimplementasikan pemisahan direktori (Package model dan service) untuk mengelompokkan class berdasarkan tanggung jawab fungsionalnya (Single Responsibility Principle). Hal ini bertujuan untuk menjaga modularitas dan keterbacaan kode (Clean Code).


4. Penjelasan Kode
   
4.1. Kelas Model (src/model/)
Setiap kelas di dalam paket model (BarangHilang, BarangDitemukan, Pengguna) dirancang sebagai rancangan dasar (blueprint) untuk entitas data. Kelas-kelas ini memuat:

Properti (Atribut): Variabel dengan access modifier private untuk menerapkan prinsip enkapsulasi. Tipe data String difungsikan untuk seluruh properti, termasuk tanggal, guna memfasilitasi format masukan yang lebih fleksibel bagi pengguna konsol.

Constructor Overloading: Setiap kelas mengimplementasikan parameterized constructor untuk inisialisasi objek secara langsung, serta non-argument constructor untuk memberikan nilai bawaan (default value). Keyword this diaplikasikan secara konsisten untuk mengeliminasi ambiguitas antara variabel instans dan parameter lokal.

Getter dan Setter: Metode public standar untuk mengakses dan memodifikasi nilai atribut yang telah dienkapsulasi.

Metode Tampilan Kustom (tampilkanInfo()): Sebuah metode bertipe void yang dikonstruksi secara spesifik untuk menyajikan detail informasi objek ke layar terminal. Metode ini memanfaatkan ternary operator untuk efisiensi logika pencetakan status objek.

4.2. Kelas Service (src/service/)
Kelas-kelas service (BarangHilangService, BarangDitemukanService, PenggunaService) bertindak sebagai lapisan pengelola logika bisnis (Business Logic Layer) dan operasi CRUD.

Penyimpanan Data (Collections): Menggunakan java.util.ArrayList dan antarmuka java.util.List sebagai wadah koleksi objek. Variabel referensi koleksi ini dideklarasikan dengan keyword final untuk memproteksi alokasi memori dari modifikasi tak disengaja.

Operasi Modifikasi Data:

Operasi Penambahan (CREATE) menggunakan method bawaan .add().

Operasi Pembacaan (READ) dioptimasi menggunakan Enhanced For Loop (for-each) untuk mengiterasi elemen secara efisien tanpa intervensi indeks manual.

Operasi Pembaruan (UPDATE) dan Penghapusan (DELETE) menggunakan For Loop konvensional untuk mengidentifikasi letak indeks presisi, dilanjutkan dengan eksekusi method .set() atau .remove().

4.3. Kelas Utama (src/Main.java)
Kelas Main berfungsi sebagai entry point sistem yang memfasilitasi Control Flow dan interaksi antarmuka terminal.

Pemisahan Logika Navigasi, Logika antarmuka dipecah ke dalam beberapa method fungsional (seperti menuBarangHilang(), menuPengguna()) agar blok eksekusi utama (metode main) tetap terstruktur dan memenuhi kaidah Don't Repeat Yourself (DRY).

Penanganan Masukan (Input Handling), * Mengimplementasikan utilitas method inputAngka() yang membungkus logika while(!scanner.hasNextInt()) untuk melakukan flushing otomatis pada karakter non-numerik.

Memiliki utilitas inputStatus() untuk mentransformasi parameter respons tekstual ("sudah" / "belum") menjadi tipe data primitif boolean secara dinamis.