# LP7DPBO2024C2

Saya Daffa Fakhry Anshori dengan NIM 2200337 mengerjakan soal LP 7 dalam Praktikum mata kuliah Desain dan Pemrograman Berbasis Objek, 
untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamin.

# Desain Program
Program ini adalah permainan Flappy Bird sederhana yang dibuat menggunakan Java Swing. Permainan terdiri dari dua kelas utama, yaitu:
1. Kelas App

   Kelas App bertanggung jawab untuk menampilkan tampilan awal atau main menu permainan Flappy Bird. Ketika aplikasi pertama kali dijalankan, kelas ini akan membuat dan menampilkan JFrame yang berisi main menu.    Pengguna dapat memulai permainan dengan menekan tombol "Start Game" yang ada di main menu. Ketika tombol tersebut ditekan, kelas ini akan menutup main menu dan membuka permainan Flappy Bird dengan menampilkan    kelas FlappyBird.

2. Kelas FlappyBird

   Kelas FlappyBird adalah kelas yang mengatur permainan Flappy Bird itu sendiri. Di dalam kelas ini, logika permainan diimplementasikan. Ini termasuk menggambar elemen-elemen permainan seperti burung dan pipa,    menggerakkan objek-objek permainan, menangani kollision antara objek-objek, serta memeriksa dan mengatur skor pemain. Selain itu, kelas ini juga menangani suara-suara permainan seperti musik latar belakang dan   efek suara saat pemain melewati pipa atau menabrak pipa.

Selain kedua kelas utama tersebut, program ini juga menggunakan kelas-kelas tambahan untuk mengatur elemen-elemen seperti pemain (Player), pipa (Pipe), dan efek suara (Sound). Penjelasannya sebagai berikut:
1. Kelas Player
   
   Kelas Player bertanggung jawab untuk merepresentasikan pemain atau karakter utama dalam permainan Flappy Bird. Berikut penjelasan singkat tentang atribut dan metode yang dimilikinya:
- posX dan posY: Menyimpan posisi pemain di layar.
- width dan height: Menyimpan lebar dan tinggi pemain.
- image: Menyimpan gambar yang digunakan untuk menggambar pemain.
- velocityY: Menyimpan kecepatan vertikal pemain, yang digunakan untuk mengatur gerakan pemain naik turun.

2. Kelas Pipe
   
   Kelas Pipe digunakan untuk merepresentasikan pipa yang menjadi rintangan dalam permainan Flappy Bird. Berikut penjelasan tentang atribut dan metode dalam kelas ini:
- posX dan posY: Menyimpan posisi pipa di layar.
- width dan height: Menyimpan lebar dan tinggi pipa.
- image: Menyimpan gambar yang digunakan untuk menggambar pipa.
- velocityX: Menyimpan kecepatan horizontal pipa, yang digunakan untuk mengatur gerakan pipa dari kanan ke kiri.
- passed: Menyimpan status apakah pasangan pipa ini sudah dilewati oleh pemain atau belum.

3. Kelas Sound
   
   Kelas Sound bertanggung jawab untuk memutar dan mengelola efek suara dalam permainan Flappy Bird. Berikut adalah penjelasan singkat tentang fungsionalitas yang dimiliki kelas ini:
- playBackgroundMusic(): Memulai musik latar belakang saat permainan dimulai.
- stopBackgroundMusic(): Menghentikan musik latar belakang.
- isBackgroundMusicPlaying(): Memeriksa apakah musik latar belakang sedang diputar.
- playPassSound(): Memutar efek suara saat pemain melewati pipa.
- playCollisionSound(): Memutar efek suara saat pemain menabrak pipa atau jatuh.

# Alur Program
1. Ketika aplikasi dijalankan, tampilan awal (main menu) muncul.
2. Pengguna dapat memilih untuk memulai permainan dengan menekan tombol "Start Game".
3. Saat tombol "Start Game" ditekan, JFrame permainan Flappy Bird akan muncul.
4. Di dalam permainan, pemain mengontrol burung  dengan menekan tombol "Spasi" untuk membuatnya melompat. Pemain harus menghindari tabrakan dengan pipa dan jatuh ke bawah. 
5. Setiap kali burung berhasil melewati sepasang pipa, skor akan bertambah. Jika burung menabrak pipa atau jatuh ke bawah, permainan berakhir dan pesan dialog Game Over muncul dan menampilkan skor saat ini dan skor terbaik.
6. Pengguna dapat merestart permainan dengan menekan tombol "R" pada keyboard.
7. Skor saat ini dan skor terbaik ditampilkan dalam JLabel di dalam permainan dan diperbarui setiap kali permainan dimulai kembali.






