# MyAnimePage

Aplikasi desktop Java Swing untuk mencari referensi anime dan menyimpan catatan pribadi. Proyek ini memakai pola MVC sederhana, mengambil data anime langsung dari Jikan REST API, dan menyimpan data pengguna serta daftar bookmark ke dalam database MySQL.

## Fitur Utama

- **Autentikasi pengguna** – registrasi dan login sederhana menggunakan tabel `user`.
- **Katalog anime** – menampilkan daftar Top Anime dari Jikan API lengkap dengan judul, poster, skor, dan ringkasan.
- **Pencarian & pagination** – mencari anime berdasarkan kata kunci sekaligus berpindah halaman data.
- **Bookmark dengan catatan** – menyimpan anime favorit per pengguna, menambahkan catatan pribadi, serta mengubah atau menghapusnya.
- **UI desktop interaktif** – tampilan Swing yang terdiri dari `LoginView`, `RegisterView`, `DashboardView`, serta `PopUpView` untuk pengelolaan bookmark.

## Teknologi & Dependensi

- Bahasa: Java 23 (lihat `javac.source=23` pada `nbproject/project.properties`)
- Build tool: Apache Ant (berkas `build.xml`)
- GUI builder: NetBeans (AbsoluteLayout)
- REST client & JSON: [`gson-2.10.1`](tool/gson-2.10.1.jar)
- Database driver: [`mysql-connector-j-8.3.0`](tool/mysql-connector-j-8.3.0.jar)
- REST API eksternal: [Jikan v4](https://docs.api.jikan.moe/)

## Struktur Proyek

```
myanimepage/
├── src/
│   ├── Main/                 # Entry point aplikasi (`Main.Main`)
│   ├── Controller/           # Logika UI + akses DAO
│   ├── Model/                # Entity, DAO, dan koneksi DB
│   ├── View/                 # Form Swing (.java + .form)
│   └── Images/               # Aset gambar profil
├── service/linkanime.sql     # Skrip pembuatan database
├── tool/                     # Library pihak ketiga (Gson & MySQL driver)
├── nbproject/                # Konfigurasi NetBeans
├── build.xml                 # Skrip Ant
└── manifest.mf
```

## Prasyarat

1. **JDK 23** (atau setidaknya JDK 21 jika Anda menurunkan `javac.source` di konfigurasi).
2. **Apache Ant** atau NetBeans IDE 15+ yang sudah menyertakan Ant.
3. **MySQL / MariaDB** yang dapat diakses secara lokal.
4. Akses internet untuk memanggil Jikan API.

## Menyiapkan Database

1. Buat database `linkanime`.
2. Impor `service/linkanime.sql` untuk membuat tabel `user` dan `bookmark` beserta contoh data.
3. Jika kredensial database lokal Anda berbeda, ubah konfigurasi di `src/Model/Connector.java`:
   ```java
   private static String username_db = "root";
   private static String password_db = "";
   ```
4. Pastikan MySQL Driver (`tool/mysql-connector-j-8.3.0.jar`) terdaftar di classpath Ant atau Library NetBeans.

## Menjalankan Aplikasi

### Via NetBeans

1. Pilih _File → Open Project..._ lalu arahkan ke folder `myanimepage`.
2. Pastikan library Gson dan MySQL Connector sudah dikenali (NetBeans biasanya otomatis karena dirujuk lewat `nbproject/project.properties`).
3. Tekan _Run_ atau `Shift + F6` untuk menjalankan `Main.Main`.

### Via Ant

```bash
cd myanimepage
ant clean run
```

Perintah di atas akan membangun dan langsung menjalankan aplikasi (mengacu pada `Main.Main`).

## Kredensial Contoh

Setelah mengimpor SQL, Anda bisa langsung login memakai salah satu contoh pengguna berikut:

- `bintoro / 123`
- `khatama / 123`

Gunakan menu Register untuk menambah akun baru. Password belum dienkripsi, jadi jangan pakai kata sandi penting.

## Catatan Integrasi API

- Jikan API memiliki rate limit (±3 request/detik/IP). Jika halaman katalog tidak muncul, tunggu sejenak.
- Pastikan perangkat memiliki koneksi internet, karena data anime tidak disimpan di database lokal.

## Struktur Database Singkat

- **user**: menyimpan `id`, `username`, `password`, `name`, `age`.
- **bookmark**: menyimpan relasi pengguna–anime, URL gambar, dan catatan pribadi. Tabel ini memakai constraint unik `user_id + anime_id` untuk mencegah duplikasi.

## Pengembangan Lanjutan yang Disarankan

- Mengenkripsi password (mis. BCrypt) sebelum disimpan.
- Menyimpan konfigurasi database di berkas `.properties` atau variabel lingkungan.
- Menambahkan unit test untuk DAO menggunakan database uji.
- Menangani error jaringan Jikan API dengan retry dan cache sederhana.

Selamat mengembangkan! Jika ada kebutuhan dokumentasi tambahan (mis. diagram atau panduan build multi-platform), tinggal tambahkan pada README ini.
