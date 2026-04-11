# Laporan Posttest

## Sistem Manajemen Barang Hilang dan Ditemukan di Lingkungan Kampus

**Nama : Jovan Bagas Pangestu**
**NIM  : 2409106071**

---

## 1. Deskripsi Sistem

Sistem Manajemen Barang Hilang dan Ditemukan di Lingkungan Kampus adalah aplikasi berbasis konsol (Command-Line Interface) yang dirancang untuk membantu mengelola laporan barang hilang dan barang ditemukan di lingkungan akademis. Aplikasi ini dibangun dengan mengimplementasikan konsep dasar Pemrograman Berorientasi Objek (PBO) menggunakan bahasa Java, dengan fokus utama pada:

- Perancangan arsitektur kelas yang terstruktur dan modular
- Utilisasi Constructor (Parameterized & Default) untuk inisialisasi objek
- Penerapan prinsip Inheritance, Encapsulation, dan Polymorphism
- Pengelolaan data secara dinamis menggunakan Collections (ArrayList)
- Penggunaan library eksternal Apache Commons Lang (`StringUtils`) untuk manipulasi String

---

## 2. Fitur Utama

Aplikasi ini memiliki instrumen dasar untuk mengelola tiga entitas utama — Barang Hilang, Barang Ditemukan, dan Pengguna — yang keseluruhannya dikelola secara non-persisten di dalam memori.

### 2.1. CRUD (Create, Read, Update, Delete) Data

| Entitas | CREATE | READ | UPDATE | DELETE |
|---|---|---|---|---|
| Barang Hilang | Tambah laporan baru | Tampil semua / cari by ID | Update status & info | Hapus by ID |
| Barang Ditemukan | Tambah laporan baru | Tampil semua / cari by ID | Update status pengembalian | Hapus by ID |
| Pengguna | Tambah profil pengguna | Tampil semua / cari by ID | Update info kontak | Hapus by ID |

### 2.2. Sistem Navigasi Berulang (Looping)

Program beroperasi menggunakan perulangan `while` konvensional (`while (aplikasiJalan)`) untuk mempertahankan siklus hidup aplikasi hingga pengguna memilih menu **0. Keluar**. Setiap sub-menu (`menuBarangHilang()`, `menuBarangDitemukan()`, `menuPengguna()`) juga memiliki perulangan sendiri dengan variabel boolean `menuJalan`.

### 2.3. Validasi Input Defensif

Sistem dilengkapi dua mekanisme validasi input di dalam kelas `Main`:

- **`inputAngka()`** — Menggunakan `while (!scanner.hasNextInt())` untuk mencegah `InputMismatchException` ketika pengguna memasukkan karakter non-numerik.
- **`inputStatus()`** — Menggunakan `while (!valid)` untuk memvalidasi input teks, hanya menerima `"sudah"` atau `"belum"` (case-insensitive), lalu mengonversinya ke tipe `boolean`.

### 2.4. Manajemen ID Otomatis

ID untuk setiap entitas baru di-generate secara otomatis menggunakan operator post-increment (`nextIdBarangHilang++`, `nextIdBarangDitemukan++`, `nextIdPengguna++`) di dalam masing-masing kelas Service pada saat metode `tambah` dipanggil.

### 2.5. Manipulasi String dengan Apache Commons Lang

Aplikasi menggunakan library `org.apache.commons.lang3.StringUtils` untuk:

- **`StringUtils.upperCase(judul)`** — Mengonversi judul menu utama menjadi huruf kapital semua.
- **`StringUtils.capitalize(nama)`** — Mengkapitalisasi huruf pertama nama barang dan nama pengguna saat input data baru.

### 2.6. Demonstrasi Polymorphism di Entry Point

Pada metode `main()`, terdapat demonstrasi eksplisit polymorphism menggunakan variabel bertipe parent class `Barang` yang mereferensikan objek child class (`BarangHilang` dan `BarangDitemukan`), diikuti pemanggilan `tampilkanInfo()` untuk memperlihatkan method overriding dan overloading secara langsung.

```java
Barang barang1 = new BarangHilang(1, "Dompet", "Kulit", "Kampus", "2026", "0812");
Barang barang2 = new BarangDitemukan(2, "HP", "Samsung", "Parkiran", "2026", "0821");

barang1.tampilkanInfo();         // overriding
barang1.tampilkanInfo(true);     // overloading (boolean detail)
barang1.tampilkanInfo("Barang:"); // overloading (String prefix)
```

---

## 3. Struktur Proyek

Proyek mengimplementasikan pemisahan direktori berbasis package untuk mengelompokkan kelas berdasarkan tanggung jawab fungsionalnya (Single Responsibility Principle), menjaga modularitas dan keterbacaan kode (Clean Code).

```
src/
├── Main.java                       # Entry point, navigasi menu, validasi input, control flow
├── model/
│   ├── Barang.java                 # Superclass / blueprint entitas barang
│   ├── BarangHilang.java           # Subclass Barang — entitas barang hilang
│   ├── BarangDitemukan.java        # Subclass Barang — entitas barang ditemukan
│   └── Pengguna.java               # Entitas data pengguna
└── service/
    ├── BarangHilangService.java    # Business logic & CRUD barang hilang
    ├── BarangDitemukanService.java  # Business logic & CRUD barang ditemukan
    └── PenggunaService.java        # Business logic & CRUD pengguna
```

| Package / File | Kelas | Tanggung Jawab |
|---|---|---|
| `src/` | `Main.java` | Entry point, navigasi menu, validasi input, control flow |
| `src/model/` | `Barang.java` | Superclass dengan atribut `id`, `namaBarang`, `deskripsi` |
| `src/model/` | `BarangHilang.java` | Subclass Barang; tambah `lokasiHilang`, `tanggalHilang`, `kontakPelapor`, `sudahDitemukan` |
| `src/model/` | `BarangDitemukan.java` | Subclass Barang; tambah `lokasiDitemukan`, `tanggalDitemukan`, `kontakPenemu`, `sudahDikembalikan` |
| `src/model/` | `Pengguna.java` | Entitas pengguna dengan atribut `id`, `nama`, `nim`, `email` |
| `src/service/` | `BarangHilangService.java` | Business logic layer: CRUD untuk entitas `BarangHilang` |
| `src/service/` | `BarangDitemukanService.java` | Business logic layer: CRUD untuk entitas `BarangDitemukan` |
| `src/service/` | `PenggunaService.java` | Business logic layer: CRUD untuk entitas `Pengguna` |

---

## 4. Penjelasan Kode

### 4.1. Kelas Model — `Barang.java` (Superclass)

Kelas `Barang` merupakan superclass yang menjadi pondasi hierarki kelas model. Atribut `id`, `namaBarang`, dan `deskripsi` dideklarasikan dengan modifier `protected` agar dapat diakses langsung oleh subclass.

- **Properti** — Modifier `protected` mendukung inheritance sekaligus membatasi akses dari luar package.
- **Constructor Overloading** — Mengimplementasikan parameterized constructor `Barang(int id, String namaBarang, String deskripsi)` dan no-argument constructor `Barang()` sebagai default.
- **Getter & Setter** — Metode `getId()`, `getNamaBarang()`, `getDeskripsi()` beserta setter-nya.
- **Method Overloading `tampilkanInfo()`** — Terdapat tiga versi dengan signature berbeda:

```java
public void tampilkanInfo()                  // tanpa parameter
public void tampilkanInfo(boolean detail)    // dengan flag detail
public void tampilkanInfo(String prefix)     // dengan prefix teks
```

### 4.2. Kelas Model — `BarangHilang.java`

`BarangHilang` adalah subclass dari `Barang` yang menambahkan atribut spesifik: `lokasiHilang`, `tanggalHilang`, `kontakPelapor`, dan `sudahDitemukan` (boolean). Terdapat juga atribut `kategori` (protected) dan `status` (package-private).

- **Pemanggilan `super()`** — Parameterized constructor memanggil `super(id, namaBarang, deskripsi)` untuk menginisialisasi atribut warisan.
- **Override `tampilkanInfo()`** — Menambahkan informasi lokasi, tanggal, dan status menggunakan ternary operator:

```java
String status = sudahDitemukan ? "Ditemukan" : "Belum Ditemukan";
```

- **Default Constructor** — Memberikan nilai bawaan seperti `"Lokasi Default"`, `"YYYY-MM-DD"`, dan `false` untuk `sudahDitemukan`.

### 4.3. Kelas Model — `BarangDitemukan.java`

`BarangDitemukan` adalah subclass dari `Barang` yang menambahkan atribut `lokasiDitemukan`, `tanggalDitemukan`, `kontakPenemu`, dan `sudahDikembalikan` (boolean). Terdapat juga atribut `kategori` (protected) dan `kondisi` (package-private).

- **Override `tampilkanInfo()`** — Menampilkan header `=== Barang Ditemukan ===`, memanggil `super.tampilkanInfo()`, lalu menambahkan detail lokasi dan status pengembalian:

```java
String status = sudahDikembalikan ? "Dikembalikan" : "Belum Dikembalikan";
```

- **Polymorphism** — Objek `BarangDitemukan` dapat direferensikan oleh variabel tipe `Barang` (upcasting), dan pemanggilan `tampilkanInfo()` akan mengeksekusi versi yang di-override (dynamic dispatch).

### 4.4. Kelas Model — `Pengguna.java`

Kelas `Pengguna` tidak meng-extend kelas lain. Atribut `id`, `nama`, `nim`, dan `email` dideklarasikan `private` untuk enkapsulasi penuh. Terdapat juga atribut `role` (protected) dan `status` (package-private).

- **Constructor Overloading** — Parameterized constructor `Pengguna(int id, String nama, String nim, String email)` dan default constructor dengan nilai bawaan.
- **Keyword `this`** — Digunakan secara konsisten di semua constructor dan setter untuk menghilangkan ambiguitas antara parameter lokal dan variabel instans (`this.id = id`, `this.nama = nama`, dst.).
- **Method `tampilkanInfo()`** — Mencetak semua atribut dalam satu baris output.

### 4.5. Kelas Service — Pola Umum

Ketiga kelas service memiliki struktur yang konsisten sebagai Business Logic Layer:

- **Penyimpanan Data** — Menggunakan `java.util.ArrayList<T>` yang dideklarasikan `private final List<T>` untuk mencegah reassignment referensi koleksi.
- **Auto-increment ID** — Variabel `nextId` di-increment setiap kali objek baru ditambahkan: `barang.setId(this.nextId++)`.
- **CREATE** (`tambah`) — Memanggil `.setId()` untuk menyuntikkan ID otomatis, lalu `.add()` untuk menambah ke ArrayList.
- **READ** (`getAll`) — Membuat salinan ArrayList menggunakan enhanced for-loop, mengembalikan `List` baru untuk mencegah modifikasi langsung pada data asli.
- **READ** (`getById`) — Mengiterasi dengan enhanced for-loop, membandingkan ID, mengembalikan objek atau `null` jika tidak ditemukan.
- **UPDATE** (`update`) — Menggunakan indexed for-loop untuk menemukan indeks presisi, lalu `.set(i, updatedObj)` untuk mengganti objek.
- **DELETE** (`hapus`) — Menggunakan indexed for-loop untuk menemukan indeks, lalu `.remove(i)` untuk menghapus elemen.

### 4.6. Kelas Utama — `Main.java`

`Main.java` berfungsi sebagai entry point dan Controller yang mengintegrasikan semua komponen. Terdapat tiga instansi `static` service dan satu `Scanner` bersama yang digunakan di seluruh metode.

- **Pemisahan Metode Menu** — Logika navigasi dipisah ke `menuBarangHilang()`, `menuBarangDitemukan()`, dan `menuPengguna()` untuk menerapkan prinsip DRY (Don't Repeat Yourself) dan menjaga metode `main()` tetap bersih.
- **`switch-case`** — Digunakan di setiap menu untuk merutekan pilihan pengguna ke aksi yang sesuai, dengan `default` untuk menangani input di luar rentang menu.
- **`inputAngka()`** — Membungkus logika `while (!scanner.hasNextInt())` untuk melakukan flushing otomatis pada karakter non-numerik.
- **`inputStatus()`** — Mentransformasi respons teks `"sudah"` / `"belum"` menjadi tipe `boolean` secara dinamis.
- **`StringUtils.capitalize()`** — Diterapkan pada nama barang dan nama pengguna setelah dibaca dari scanner untuk memastikan format penulisan yang konsisten.

---

## 5. Konsep OOP yang Diimplementasikan

### 5.1. Encapsulation

Seluruh atribut pada kelas model dideklarasikan `private` (Pengguna) atau `protected` (Barang dan subclass-nya) dan hanya dapat diakses melalui metode getter dan setter. Ini melindungi integritas data internal objek dari akses langsung yang tidak sah dari luar kelas.

### 5.2. Inheritance (Pewarisan)

`BarangHilang` dan `BarangDitemukan` mewarisi atribut dan metode dari superclass `Barang` melalui keyword `extends`. Ini menghindari duplikasi kode untuk properti umum (`id`, `namaBarang`, `deskripsi`) dan metode (getter/setter/`tampilkanInfo`) yang digunakan bersama.

### 5.3. Polymorphism

Terdapat dua bentuk polymorphism yang diimplementasikan:

- **Method Overriding** — `BarangHilang` dan `BarangDitemukan` meng-override `tampilkanInfo()` dari `Barang` menggunakan anotasi `@Override`, menambahkan detail spesifik masing-masing entitas.
- **Method Overloading** — Kelas `Barang` mendefinisikan tiga versi `tampilkanInfo()` dengan parameter berbeda. Didemonstrasikan secara eksplisit di `Main.java`.

### 5.4. Constructor Overloading

Setiap kelas model menyediakan dua constructor: parameterized constructor untuk inisialisasi dengan data lengkap, dan default (no-argument) constructor dengan nilai bawaan. Keyword `this` digunakan secara konsisten untuk menghilangkan ambiguitas.

### 5.5. Abstraksi Melalui Pemisahan Layer

Proyek mengimplementasikan abstraksi melalui pemisahan tanggung jawab:

- **Model** — Hanya bertanggung jawab atas representasi dan penyimpanan data.
- **Service** — Menangani logika bisnis dan operasi CRUD.
- **Main** — Menangani antarmuka pengguna dan alur kontrol.