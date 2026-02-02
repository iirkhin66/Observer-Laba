package ilya.observerlaba.Realization;

import ilya.observerlaba.Observer.IObserver;
import ilya.observerlaba.Observer.Subject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TimeServer implements Subject {
    private final Timeline timeline;
    private final List<IObserver> observers = new ArrayList<>();
    private int timeState = 0;

    public TimeServer() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(100), _ -> tick()));
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    private void tick() {
        ++this.timeState;

        this.notifyAllObservers();
    }

    @Override
    public boolean attach(final IObserver observer) {
        if (observer == null || this.observers.contains(observer)) {
            return false;
        }

        this.observers.add(observer);
        return true;
    }

    @Override
    public boolean detach(final IObserver observer) {
        if (observer == null || !this.observers.contains(observer)) {
            return false;
        }

        this.observers.remove(observer);
        return true;
    }

    @Override
    public void notifyAllObservers() {
        for (final IObserver observer : this.observers) {
            observer.update(); // если бы можно было update(this), то в каждом наблюдателе не пришлось бы
            // хранить Subject; либо делать IObserver не как интерфейс, а как абстрактный класс с Subject полем
        }
    }

    // тут getStatus возвращает timeState; иначе пришлось бы хранить в наблюдателях не Subject, а конкретный
    // TimeServer, что противоречит абстракциям и универсальности
    @Override
    public int getStatus() {
        return this.timeState;
    }

    public void start() {
        if (this.timeline.getStatus() != Animation.Status.RUNNING) {
            this.timeline.play();

            System.out.println("Starting timeline!");
        }
    }

    public void stop() {
        if (this.timeline.getStatus() == Animation.Status.RUNNING) {
            this.timeline.pause();

            System.out.println("Stopping timeline!");
        }
    }

    public void reset() {
        this.timeState = 0;
        this.timeline.stop();
        this.timeline.play();

        System.out.println("Restarting server!");
    }
}
