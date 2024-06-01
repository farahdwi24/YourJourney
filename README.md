# YourJourney

Aplikasi YourJourney adalah sebuah aplikasi mobile yang dirancang berdasarkan wisata dari Kota Purwakarta,  yang bertujuan membantu pengguna untuk menjelajahi berbagai tempat menarik di daerah tersebut. Dengan menggunakan aplikasi ini, pengguna dapat dengan mudah melihat daftar kategori tempat wisata, tempat ibadah, hotel, dan kuliner, beserta informasi detail yang terkait.

## Fitur Utama

1. **Login dan Register**: Pengguna melakukan Login dan Register akun.
2. **Daftar Destinasi Wisata**: Pengguna dapat menelusuri berbagai destinasi wisata yang tersedia di suatu daerah.
3. **Daftar Tempat Ibadah**: Pengguna dapat menemukan tempat-tempat ibadah di sekitar mereka.
4. **Daftar Hotel**: Pengguna dapat melihat hotel-hotel terdekat untuk mengatur akomodasi mereka.
5. **Daftar Kuliner**: Pengguna dapat menemukan berbagai tempat makan dan kuliner khas daerah setempat.
6. **Informasi Detail**: Pengguna dapat melihat informasi detail tentang setiap Daftar Kategori termasuk deskripsi, gambar, dan lokasi, dll.
7. **Manajemen Profil Pengguna**: Pengguna dapat mengelola profil mereka, termasuk mengedit informasi dan menghapus akun.

## Cara Penggunaan

1. **Registrasi atau Login**:
   - Saat pertama kali membuka aplikasi, pengguna akan diminta untuk melakukan registrasi atau login.
   - Pengguna dapat melakukan registrasi dengan mengisi formulir dengan nama, email, username, dan password.
   - Jika pengguna sudah memiliki akun, mereka dapat langsung login dengan username dan password.

2. **Halaman Utama**:
   - Setelah login, pengguna akan diarahkan ke halaman utama yang menampilkan ikon daftar kategori seperti hotel, kuliner, tempat ibadah, dan destinasi wisata.
   - Ikon tersebut dapat ditekan untuk melihat daftar dari setiap kategori atau informasi tempat-tempat menarik yang dipilih.
   - Pengguna dapat menekan tombol navigasi di bagian bawah layar untuk beralih antara halaman utama, profil pengguna, dan lainnya.

3. **Detail Tempat**:
   - Dari daftar setup kategori, pengguna dapat menekan lagi salah satu item untuk melihat detail tempat seperti gambar, deskripsi, alamat, dan informasi lainnya.

4. **Profil Pengguna**:
   - Di halaman profil, pengguna dapat melihat dan mengedit informasi profil mereka.
   - Pengguna juga dapat logout atau menghapus akun mereka dari aplikasi.

## Implementasi Teknis

- **Android Studio**: Digunakan untuk pengembangan Aplikasi.
- **SQLite**: Untuk manajemen data pengguna digunakan sebagai basis data lokal.
- **Retrofit**: Digunakan untuk melakukan komunikasi dengan server untuk mendapatkan data API.
- **Fragment**: Digunakan untuk mengatur tata letak antarmuka pengguna dan menampilkan informasi yang terbagi menjadi dua yaitu profile fragment dan home fragment.
- **SharedPreferences**: Digunakan untuk menyimpan status login pengguna.
- **Google Maps API**: Digunakan untuk menampilkan peta dan lokasi yang diperlukan di aplikasi.
