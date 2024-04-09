/* Saya Daffa Fakhry Anshori dengan NIM 2200337 mengerjakan soal LP 7
   dalam Praktikum mata kuliah Desain dan Pemrograman Berbasis Objek, untuk keberkahan-Nya
   maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamin. */

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private static Clip backgroundMusic;
    private static Clip passSound;
    private static Clip collisionSound;
    private static boolean isBackgroundMusicPlaying = false;

    public static void playBackgroundMusic() {
        // Kode untuk memulai musik latar belakang
        isBackgroundMusicPlaying = true;

        try {
            URL backgroundMusicFile = Sound.class.getResource("sounds/background_music.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(backgroundMusicFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        // Kode untuk menghentikan musik latar belakang
        isBackgroundMusicPlaying = false;

        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    public static boolean isBackgroundMusicPlaying() {
        return isBackgroundMusicPlaying;
    }

    public static void playPassSound() {
        try {
            URL passSoundFile = Sound.class.getResource("sounds/pass.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(passSoundFile);
            passSound = AudioSystem.getClip();
            passSound.open(audioInputStream);
            passSound.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playCollisionSound() {
        try {
            URL collisionFile = Sound.class.getResource("sounds/collision.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(collisionFile);
            collisionSound = AudioSystem.getClip();
            collisionSound.open(audioInputStream);
            collisionSound.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
