package ilya.observerlaba.Controller;

import ilya.observerlaba.Realization.MusicObserver;
import ilya.observerlaba.Realization.PictureObserver;
import ilya.observerlaba.Realization.TimeObserver;
import ilya.observerlaba.Realization.TimeServer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ObserverController {
    private TimeServer server;
    private TimeObserver timeObserver;
    private MusicObserver musicObserver;
    private PictureObserver pictureObserver;

    @FXML
    private Label timeLabel, stoppedLabel, runningLabel;
    @FXML
    private Label musicLabel;
    @FXML
    private ImageView imageView;

    public void initialize() {
        this.server = new TimeServer();

        this.timeObserver = new TimeObserver(this.server);
        this.musicObserver = new MusicObserver(this.server, "files/song.mp3");
        this.pictureObserver = new PictureObserver(this.server, "files/1.png", "files/2.png");

        this.server.attach(this.timeObserver);
        this.server.attach(this.musicObserver);
        this.server.attach(this.pictureObserver);

        final AnimationTimer uiTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeLabel.setText("Ticks: " + timeObserver.getCurrentTicks());
                musicLabel.setText("Music is " + (musicObserver.getStatus() ? "playing" : "not playing"));
                imageView.setImage(new Image(new File(pictureObserver.getCurrentPhoto()).toURI().toString()));
            }
        };
        uiTimer.start();

        this.stoppedLabel.setVisible(false);
        this.runningLabel.setVisible(true);
    }

    @FXML
    private void startTimeServer() {
        this.server.start();
        this.musicObserver.unpause();

        this.stoppedLabel.setVisible(false);
        this.runningLabel.setVisible(true);
    }

    @FXML
    private void stopTimeServer() {
        this.server.stop();
        this.musicObserver.pause();

        this.stoppedLabel.setVisible(true);
        this.runningLabel.setVisible(false);
    }

    @FXML
    private void restartTimeServer() {
        this.server.reset();
        this.musicObserver.reset();
        this.pictureObserver.reset();

        this.stoppedLabel.setVisible(false);
        this.runningLabel.setVisible(true);
    }
}
