package ilya.observerlaba.Realization;

import ilya.observerlaba.Observer.IObserver;
import ilya.observerlaba.Observer.Subject;

public class PictureObserver implements IObserver {
    private static final int CHANGE_PICTURE_TICK = 50;

    private final Subject subject; // либо делать IObserver не как интерфейс, а как абстрактный класс с Subject полем
    private final String[] photosPaths;
    private int currentIdx = 0;

    public PictureObserver(final Subject subject, final String... photosPaths) {
        this.subject = subject;
        this.photosPaths = photosPaths;
    }

    @Override
    public void update() {
        if ((this.subject.getStatus() + 1) % CHANGE_PICTURE_TICK == 0) { // каждые 50 тиков меняем фотографию на следующую (циклически)
            this.changePhoto();
        }
    }

    private void changePhoto() {
        this.currentIdx = ++this.currentIdx % this.photosPaths.length;
    }

    public String getCurrentPhoto() {
        return this.photosPaths[this.currentIdx];
    }

    public void reset() {
        this.currentIdx = 0;
    }
}
