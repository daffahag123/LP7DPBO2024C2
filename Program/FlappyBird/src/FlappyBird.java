/* Saya Daffa Fakhry Anshori dengan NIM 2200337 mengerjakan soal LP 7
   dalam Praktikum mata kuliah Desain dan Pemrograman Berbasis Objek, untuk keberkahan-Nya
   maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamin. */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    // Image attributes
    private int frameWidth = 360;
    private int frameHeight = 640;
    private Image backgroundImage;
    private Image birdImage;
    private Image lowerPipeImage;
    private Image upperPipeImage;

    // Player attributes
    private int playerStartPosX = frameWidth / 8;
    private int playerStartPosY = frameHeight / 2;
    private int playerWidth = 34;
    private int playerHeight = 24;
    Player player;

    // Pipe attributes
    private int pipeStartPosX = frameWidth;
    private int pipeStartPosY = 0;
    private int pipeWidth = 64;
    private int pipeHeight = 512;
    private ArrayList<Pipe> pipes;

    // Game logic
    Timer gameLoop;
    Timer pipesCooldown;
    private int gravity = 1;

    // Score attributes
    private int currentScore;
    private int bestScore;
    private JLabel scoreLabel;

    // Constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
        // setBackground(Color.blue);

        // Panggil method playBackgroundMusic() saat permainan dimulai
        Sound.playBackgroundMusic();

        // Load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/40, this);
        gameLoop.start();

        // Membuat JLabel untuk skor
        currentScore = 0;
        bestScore = 0;
        scoreLabel = new JLabel("Score: " + currentScore);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(scoreLabel); // Tambahkan label skor ke panel permainan

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + pipeHeight + openingSpace), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        // Jika Terjadi Tabrakan atau Jatuh ke Bawah
        if (checkCollision() || checkOutOfBounds()) {
            // Permainan Selesai
            handleGameOver();
        }
        // Periksa Pipa yang Lewat dan Perbarui Skor
        checkPassedPipes();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // Deteksi Tabrakan Dengan Pipa
    public boolean checkCollision() {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

            if (playerRect.intersects(pipeRect)) {
                return true; // Collision detected
            }
        }
        return false; // No collision detected
    }

    // Deteksi Jatuh ke Batas Bawah JFrame
    public boolean checkOutOfBounds() {
        return player.getPosY() + player.getHeight() >= frameHeight;
    }

    // Penanganan GameOver
    public void handleGameOver() {
        gameLoop.stop();        // Stop the game loop
        pipesCooldown.stop();   // Stop the pipe spawning

        // Panggil metode playCollisionSound() dari kelas Sound saat terjadi tabrakan
        Sound.playCollisionSound();
        // Menghentikan musik latar belakang saat game over
        Sound.stopBackgroundMusic();

        if (currentScore > bestScore) {
            bestScore = currentScore;
        }

        // Buat panel untuk menampung konten dialog
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 20, 0, 20));

        // Tambahkan label dengan pesan game over
        JLabel messageLabel = new JLabel("<html><center><h1 style='color:red;'>Game Over</h1><p>Your Score: " + currentScore + "</p><p>Best Score: " + bestScore + "</p></center></html>", SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        // Tampilkan dialog
        JOptionPane.showMessageDialog(null, panel, "Game Over", JOptionPane.PLAIN_MESSAGE);
    }

    // Restart Permainan
    public void restartGame() {
        // Menghentikan musik latar belakang jika sedang diputar
        if (Sound.isBackgroundMusicPlaying()) {
            Sound.stopBackgroundMusic();
        }

        // Mengatur Ulang Posisi Player dan Pipa ke Keadaan Awal
        player.setPosY(playerStartPosY);
        pipes.clear();
        // Mengatur Ulang Kecepatan Vertikal Pemain
        player.setVelocityY(-20 + gravity);

        // Memulai Kembali Perulangan Permainan dan Pembuatan Pipa
        gameLoop.start();
        pipesCooldown.start();

        // Mengatur ulang skor ke 0 dan memperbarui label skor
        currentScore = 0;
        updateScoreLabel();

        // Memulai kembali musik latar belakang saat permainan direstart
        if (!Sound.isBackgroundMusicPlaying()) {
            Sound.playBackgroundMusic();
        }
    }

    // Memeriksa Apakah Player Melewati Pipa dan Memperbarui Skor
    private void checkPassedPipes() {
        int passedPairs = 0; // Menyimpan jumlah pasangan pipa yang telah dilewati

        // Loop melalui setiap pasangan pipa
        for (int i = 0; i < pipes.size(); i += 2) {
            Pipe upperPipe = pipes.get(i);
            Pipe lowerPipe = pipes.get(i+1);

            // Pengecekan jika pasangan pipa sudah dilewati
            if (!upperPipe.isPassed() && upperPipe.getPosX() + upperPipe.getWidth() < player.getPosX()) {
                // Diset true untuk mencegah penambahan skor berulang kali
                // Jadi dapat dipastikan bahwa pasangan pipa tersebut tidak akan lagi dihitung saat pemain terus bergerak maju
                upperPipe.setPassed(true);
                lowerPipe.setPassed(true);
                passedPairs++; // Tambah jumlah pasangan pipa yang telah dilewati

                // Panggil metode playPassSound() dari kelas Sound saat pemain melewati pipa
                Sound.playPassSound();
            }
        }

        // Menambah skor berdasarkan jumlah pasangan pipa yang dilewati
        currentScore += passedPairs;
        // Memperbarui label skor
        updateScoreLabel();
    }

    // Update Score Label
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + currentScore);
    }

}
