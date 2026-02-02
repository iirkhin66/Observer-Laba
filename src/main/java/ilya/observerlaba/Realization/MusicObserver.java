package ilya.observerlaba.Realization;

import ilya.observerlaba.Observer.IObserver;
import ilya.observerlaba.Observer.Subject;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicObserver implements IObserver {
    private static final int MUSIC_PLAY_TICK = 70;
    private static final int MUSIC_STOP_TICK = 140;

    private final Subject subject; // либо делать IObserver не как интерфейс, а как абстрактный класс с Subject полем
    private final String musicPath;
    private final MediaPlayer mediaPlayer;

    public MusicObserver(final Subject subject, final String musicPath) {
        this.subject = subject;
        this.musicPath = musicPath;
        this.mediaPlayer = new MediaPlayer(new Media(new File(this.musicPath).toURI().toString()));
    }

    @Override
    public void update() {
        if ((this.subject.getStatus() + 1) % MUSIC_STOP_TICK == 0) { // каждые 140 тиков выключаем музыку
            this.stop();
        } else if ((this.subject.getStatus() + 1) % MUSIC_PLAY_TICK == 0) { // каждые 70 тиков включаем музыку (кроме каждых 140-х тиков)
            this.start(); // т. е. 70-й тик – включаем, 140-й тик – выключаем, 210-й тик – включаем, и т. д.
        }
    }

    private void start() {
        if (this.mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            this.mediaPlayer.play();
        }
    }

    private void stop() {
        if (this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            this.mediaPlayer.stop();
        }
    }

    public void pause() {
        if (this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            this.mediaPlayer.pause();
        }
    }

    public void unpause() {
        if (this.mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            this.mediaPlayer.play();
        }
    }

    public boolean getStatus() {
        if (this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.mediaPlayer.stop();
    }
}
