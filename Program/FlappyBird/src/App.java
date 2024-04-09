/* Saya Daffa Fakhry Anshori dengan NIM 2200337 mengerjakan soal LP 7
   dalam Praktikum mata kuliah Desain dan Pemrograman Berbasis Objek, untuk keberkahan-Nya
   maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamin. */

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class App {
    public static void main(String[] args) {
        // Buat sebuah frame untuk GUI Form
        JFrame startFrame = new JFrame("Main Menu");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(280, 480);
        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(false);

        // Tambahkan JPanel dengan latar belakang gambar
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon backgroundImage = new ImageIcon(App.class.getResource("assets/background.png"));
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(new BorderLayout());

        // Tambahkan label "Game Flappy Bird" di tengah atas
        JLabel titleLabel = new JLabel("FlappyBird", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(25, 0, 0, 0));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Tambahkan gambar burung di bawah label "Game Flappy Bird"
        ImageIcon birdIcon = new ImageIcon(App.class.getResource("assets/bird.png"));
        Image birdImage = birdIcon.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedBirdIcon = new ImageIcon(birdImage);
        JLabel birdLabel = new JLabel(resizedBirdIcon);
        panel.add(birdLabel, BorderLayout.CENTER);

        // Buat objek JButton untuk tombol
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            // Tutup GUI Form
            startFrame.dispose();

            // Buka JFrame game FlappyBird
            JFrame gameFrame = new JFrame("Flappy Bird");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(360, 640);
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setResizable(false);

            // Buat objek JPanel
            FlappyBird flappyBird = new FlappyBird();
            gameFrame.add(flappyBird);
            gameFrame.pack();
            flappyBird.requestFocus();
            gameFrame.setVisible(true);
        });

        // Tambahkan tombol ke dalam JPanel di tengah bawah
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        startButton.setMargin(new Insets(5, 10, 5, 10));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        buttonPanel.add(startButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Tambahkan JPanel ke dalam JFrame
        startFrame.add(panel);
        startFrame.setVisible(true);
    }
}
