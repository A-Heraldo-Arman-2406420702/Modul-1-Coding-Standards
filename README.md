# Reflection 1

## Clean Code Principles & Secure Coding Practices

Selama pengerjaan fitur **Edit** dan **Delete** pada tutorial ini, saya telah menerapkan beberapa prinsip _Clean Code_ dan _Secure Coding_ sebagai berikut:

### 1. Meaningful Names (Penamaan yang Jelas)

Saya berusaha menggunakan nama variabel, method, dan class yang deskriptif dan mencerminkan tujuannya.

- **Contoh:** Penggunaan nama `ProductService`, `ProductRepository`, `create`, `edit`, dan `delete` yang secara eksplisit menjelaskan apa yang dilakukan oleh komponen tersebut. Variabel seperti `productId` dan `productName` juga jelas maknanya dibandingkan hanya menggunakan `id` atau `name` yang bisa ambigu.

### 2. Single Responsibility Principle (SRP)

Saya telah memisahkan tanggung jawab antar komponen sesuai arsitektur Spring Boot (MVC):

- **Controller (`ProductController`):** Hanya bertugas mengatur alur request (menerima input) dan menentukan view mana yang akan ditampilkan. Tidak ada logika bisnis yang berat di sini.
- **Service (`ProductServiceImpl`):** Bertugas menangani logika bisnis. Controller tidak mengakses Repository secara langsung, melainkan melalui Service ini.
- **Repository (`ProductRepository`):** Fokus hanya pada pengelolaan data (menyimpan, mencari, mengupdate, dan menghapus data dari List).

### 3. Penerapan UUID untuk ID Produk (Secure Coding)

Alih-alih menggunakan integer sekuensial (1, 2, 3...) untuk ID produk, saya menggunakan **UUID (Universally Unique Identifier)**. Identifier digunakan untuk mendapatkan produk saat ingin melakukan edit di product tertentu

- **Alasan Keamanan:** Menggunakan ID sekuensial membuat data mudah ditebak (ID Enumeration Attack). Dengan UUID, penyerang akan sulit menebak ID produk lain hanya dengan melihat pola URL.

### 4. Penggunaan Lombok

Saya menggunakan anotasi `@Getter`, `@Setter` dari library Lombok pada model `Product`. Ini mengurangi _boilerplate code_ yang tidak perlu, sehingga kode menjadi lebih ringkas dan fokus pada atribut data.

## Mistakes & Areas for Improvement

Meskipun fitur sudah berjalan, saya menyadari ada beberapa kekurangan dan pelanggaran _best practice_ yang perlu diperbaiki:

### 1. Kurangnya Validasi Input

**Kesalahan:**
Saya belum menambahkan validasi pada input form. Pengguna bisa saja menginput nama produk kosong atau jumlah kuantitas negatif.

**Perbaikan:**
Menambahkan anotasi validasi dari `jakarta.validation` (seperti `@NotBlank`, `@Min(0)`) pada model `Product`, dan menambahkan `@Valid` serta `BindingResult` pada Controller untuk menangani error input dengan lebih elegan.

### 2. Unit Test Belum Lengkap

**Kesalahan:**
Saya menambahkan fitur `edit` dan `delete` di functional code, tetapi belum mengupdate atau menambahkan _Unit Test_ untuk fitur-fitur baru tersebut di `ProductService` maupun `ProductRepository`.

**Perbaikan:**
Saya harus membuat test case baru untuk memastikan method `edit()` benar-benar mengubah data yang dimaksud dan `delete()` benar-benar menghapus data dari list, serta menangani kasus jika ID tidak ditemukan (Negative Test Case).
