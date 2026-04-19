# Laporan Posttest

## Sistem Manajemen Barang Hilang dan Ditemukan di Lingkungan Kampus

**Nama : Jovan Bagas Pangestu**
**NIM  : 2409106071**

---

## 1. Deskripsi Sistem

Sistem Manajemen Barang Hilang dan Ditemukan di Lingkungan Kampus adalah aplikasi berbasis konsol (Command-Line Interface) yang dirancang untuk membantu mengelola laporan barang hilang dan barang ditemukan di lingkungan akademis. Aplikasi ini dibangun dengan mengimplementasikan konsep dasar Pemrograman Berorientasi Objek (PBO) menggunakan bahasa Java, dengan fokus utama pada:

- Perancangan arsitektur kelas yang terstruktur dan modular
- Utilisasi Constructor (Parameterized & Default) untuk inisialisasi objek
- Penerapan prinsip Inheritance, Encapsulation, Polymorphism, dan Abstraction
- Penggunaan **Abstract Class** dan **Interface** sebagai kontrak perilaku kelas
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

Pada metode `main()`, terdapat demonstrasi eksplisit polymorphism menggunakan variabel bertipe abstract class `Barang` yang mereferensikan objek subclass konkret (`BarangHilang` dan `BarangDitemukan`), diikuti pemanggilan `tampilkanInfo()` untuk memperlihatkan method overriding dan overloading secara langsung.

```java
Barang barang1 = new BarangHilang(1, "Dompet", "Kulit", "Kampus", "2026", "0812");
Barang barang2 = new BarangDitemukan(2, "HP", "Samsung", "Parkiran", "2026", "0821");

barang1.tampilkanInfo();          // abstract method — dieksekusi versi override
barang1.tampilkanInfo(true);      // overloading (boolean detail)
barang1.tampilkanInfo("Barang:"); // overloading (String prefix)
```

---

## 3. Struktur Proyek

Proyek mengimplementasikan pemisahan direktori berbasis package untuk mengelompokkan kelas berdasarkan tanggung jawab fungsionalnya (Single Responsibility Principle), menjaga modularitas dan keterbacaan kode (Clean Code).

```
src/
├── Main.java                       # Entry point, navigasi menu, validasi input, control flow
└── model/
    ├── Barang.java                 # Abstract class — blueprint entitas barang
    ├── OperasiBarang.java          # Interface — kontrak operasi tambah & hapus
    ├── BarangHilang.java           # Subclass Barang + implements OperasiBarang
    ├── BarangDitemukan.java        # Subclass Barang + implements OperasiBarang
    └── Pengguna.java               # Entitas data pengguna
```

| Package / File | Jenis | Tanggung Jawab |
|---|---|---|
| `src/Main.java` | Class | Entry point, navigasi menu, validasi input, control flow |
| `src/model/Barang.java` | Abstract Class | Blueprint entitas barang dengan abstract method `tampilkanInfo()` |
| `src/model/OperasiBarang.java` | Interface | Kontrak operasi `tambahData()` dan `hapusData()` |
| `src/model/BarangHilang.java` | Concrete Class | Subclass Barang; atribut barang hilang + implementasi `OperasiBarang` |
| `src/model/BarangDitemukan.java` | Concrete Class | Subclass Barang; atribut barang ditemukan + implementasi `OperasiBarang` |
| `src/model/Pengguna.java` | Class | Entitas pengguna dengan atribut `id`, `nama`, `nim`, `email` |

---

## 4. Penjelasan Kode

### 4.1. Kelas Model — `Barang.java` (Abstract Class)

Kelas `Barang` dideklarasikan sebagai **abstract class**, artinya kelas ini tidak dapat diinstansiasi secara langsung dan wajib di-extend oleh subclass konkret. Atribut `id`, `namaBarang`, dan `deskripsi` dideklarasikan `protected` agar dapat diakses langsung oleh subclass.

- **Abstract Method** — `tampilkanInfo()` tanpa parameter dideklarasikan `abstract`, memaksa setiap subclass untuk menyediakan implementasinya sendiri:

```java
public abstract void tampilkanInfo();
```

- **Constructor Overloading** — Mengimplementasikan parameterized constructor `Barang(int id, String namaBarang, String deskripsi)` dan no-argument constructor `Barang()` sebagai default.
- **Getter & Setter** — Metode `getId()`, `getNamaBarang()`, `getDeskripsi()` beserta setter-nya untuk enkapsulasi.
- **Method Overloading `tampilkanInfo()`** — Dua versi konkret dengan signature berbeda tetap tersedia sebagai metode yang diwarisi subclass:

```java
public void tampilkanInfo(boolean detail) { ... }  // dengan flag detail
public void tampilkanInfo(String prefix)  { ... }  // dengan prefix teks
```

### 4.2. Interface — `OperasiBarang.java`

`OperasiBarang` adalah interface yang mendefinisikan kontrak perilaku operasi dasar untuk entitas barang. Setiap kelas yang mengimplementasikan interface ini wajib menyediakan implementasi kedua method berikut:

```java
public interface OperasiBarang {
    void tambahData();
    void hapusData();
}
```

Interface ini diimplementasikan oleh `BarangHilang` dan `BarangDitemukan` menggunakan keyword `implements`. Ini merupakan penerapan konsep **abstraction** sekaligus mendukung **loose coupling** karena perilaku operasi dipisahkan dari detail implementasinya.

### 4.3. Kelas Model — `BarangHilang.java`

`BarangHilang` adalah subclass konkret dari abstract class `Barang` sekaligus mengimplementasikan interface `OperasiBarang` (`extends Barang implements OperasiBarang`). Kelas ini menambahkan atribut spesifik: `lokasiHilang`, `tanggalHilang`, `kontakPelapor`, dan `sudahDitemukan` (boolean). Terdapat juga atribut `kategori` (protected) dan `status` (package-private).

- **Pemanggilan `super()`** — Parameterized constructor memanggil `super(id, namaBarang, deskripsi)` untuk menginisialisasi atribut yang diwarisi dari `Barang`.
- **Override `tampilkanInfo()`** — Mengimplementasikan abstract method dari `Barang` dengan mencetak seluruh detail barang hilang secara langsung, menggunakan ternary operator untuk status:

```java
String status = sudahDitemukan ? "Ditemukan" : "Belum Ditemukan";
```

- **Implementasi `OperasiBarang`** — Menyediakan implementasi konkret untuk kedua method interface:

```java
@Override
public void tambahData() {
    System.out.println("Menambahkan data barang hilang...");
}

@Override
public void hapusData() {
    System.out.println("Menghapus data barang hilang...");
}
```

- **Default Constructor** — Memberikan nilai bawaan seperti `"Lokasi Default"`, `"YYYY-MM-DD"`, dan `false` untuk `sudahDitemukan`.

### 4.4. Kelas Model — `BarangDitemukan.java`

`BarangDitemukan` adalah subclass konkret dari abstract class `Barang` sekaligus mengimplementasikan interface `OperasiBarang` (`extends Barang implements OperasiBarang`). Kelas ini menambahkan atribut `lokasiDitemukan`, `tanggalDitemukan`, `kontakPenemu`, dan `sudahDikembalikan` (boolean). Terdapat juga atribut `kategori` (protected) dan `kondisi` (package-private).

- **Override `tampilkanInfo()`** — Mengimplementasikan abstract method dari `Barang` dengan mencetak header `=== Barang Ditemukan ===` diikuti seluruh detail, menggunakan ternary operator untuk status:

```java
String status = sudahDikembalikan ? "Dikembalikan" : "Belum Dikembalikan";
```

- **Implementasi `OperasiBarang`** — Menyediakan implementasi konkret untuk kedua method interface:

```java
@Override
public void tambahData() {
    System.out.println("Menambahkan data barang ditemukan...");
}

@Override
public void hapusData() {
    System.out.println("Menghapus data barang ditemukan...");
}
```

- **Polymorphism** — Objek `BarangDitemukan` dapat direferensikan oleh variabel tipe `Barang` (upcasting) maupun tipe `OperasiBarang`, dan pemanggilan method akan mengeksekusi implementasi yang sesuai (dynamic dispatch).

### 4.5. Kelas Model — `Pengguna.java`

Kelas `Pengguna` merupakan kelas konkret biasa yang tidak meng-extend kelas lain maupun mengimplementasikan interface. Atribut `id`, `nama`, `nim`, dan `email` dideklarasikan `private` untuk enkapsulasi penuh. Terdapat juga atribut `role` (protected) dan `status` (package-private).

- **Constructor Overloading** — Parameterized constructor `Pengguna(int id, String nama, String nim, String email)` dan default constructor dengan nilai bawaan.
- **Keyword `this`** — Digunakan secara konsisten di semua constructor dan setter untuk menghilangkan ambiguitas antara parameter lokal dan variabel instans (`this.id = id`, `this.nama = nama`, dst.).
- **Method `tampilkanInfo()`** — Mencetak semua atribut dalam satu baris output.

### 4.6. Kelas Service — Pola Umum

Ketiga kelas service (`BarangHilangService`, `BarangDitemukanService`, `PenggunaService`) memiliki struktur yang konsisten sebagai Business Logic Layer:

- **Penyimpanan Data** — Menggunakan `java.util.ArrayList<T>` yang dideklarasikan `private final List<T>` untuk mencegah reassignment referensi koleksi.
- **Auto-increment ID** — Variabel `nextId` di-increment setiap kali objek baru ditambahkan: `barang.setId(this.nextId++)`.
- **CREATE** (`tambah`) — Memanggil `.setId()` untuk menyuntikkan ID otomatis, lalu `.add()` untuk menambah ke ArrayList.
- **READ** (`getAll`) — Membuat salinan ArrayList menggunakan enhanced for-loop, mengembalikan `List` baru untuk mencegah modifikasi langsung pada data asli.
- **READ** (`getById`) — Mengiterasi dengan enhanced for-loop, membandingkan ID, mengembalikan objek atau `null` jika tidak ditemukan.
- **UPDATE** (`update`) — Menggunakan indexed for-loop untuk menemukan indeks presisi, lalu `.set(i, updatedObj)` untuk mengganti objek.
- **DELETE** (`hapus`) — Menggunakan indexed for-loop untuk menemukan indeks, lalu `.remove(i)` untuk menghapus elemen.

---

## 5. Konsep OOP yang Diimplementasikan

### 5.1. Encapsulation

Seluruh atribut pada kelas model dideklarasikan `private` (Pengguna) atau `protected` (Barang dan subclass-nya) dan hanya dapat diakses melalui metode getter dan setter. Ini melindungi integritas data internal objek dari akses langsung yang tidak sah dari luar kelas.

### 5.2. Inheritance (Pewarisan)

`BarangHilang` dan `BarangDitemukan` mewarisi atribut dan metode dari abstract class `Barang` melalui keyword `extends`. Ini menghindari duplikasi kode untuk properti umum (`id`, `namaBarang`, `deskripsi`) dan method overloading (`tampilkanInfo(boolean)`, `tampilkanInfo(String)`) yang digunakan bersama.

### 5.3. Abstraction (Abstraksi)

Abstraksi diimplementasikan melalui dua mekanisme:

- **Abstract Class** — `Barang` dideklarasikan `abstract` dengan abstract method `tampilkanInfo()`. Kelas ini tidak dapat diinstansiasi langsung dan mendelegasikan detail implementasi tampilan ke masing-masing subclass.
- **Interface** — `OperasiBarang` mendefinisikan kontrak perilaku `tambahData()` dan `hapusData()` tanpa menyediakan implementasi, memisahkan *apa yang harus dilakukan* dari *bagaimana cara melakukannya*.

### 5.4. Polymorphism

Terdapat dua bentuk polymorphism yang diimplementasikan:

- **Method Overriding** — `BarangHilang` dan `BarangDitemukan` meng-override abstract method `tampilkanInfo()` dari `Barang` menggunakan anotasi `@Override`, serta mengimplementasikan `tambahData()` dan `hapusData()` dari interface `OperasiBarang`.
- **Method Overloading** — Kelas `Barang` mendefinisikan dua versi konkret `tampilkanInfo()` dengan parameter berbeda (`boolean detail` dan `String prefix`) yang diwarisi oleh seluruh subclass.

### 5.5. Constructor Overloading

Setiap kelas model menyediakan dua constructor: parameterized constructor untuk inisialisasi dengan data lengkap, dan default (no-argument) constructor dengan nilai bawaan. Keyword `this` digunakan secara konsisten untuk menghilangkan ambiguitas antara variabel instans dan parameter lokal.