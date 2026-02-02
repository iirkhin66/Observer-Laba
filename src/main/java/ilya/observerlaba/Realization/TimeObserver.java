package ilya.observerlaba.Realization;

import ilya.observerlaba.Observer.IObserver;
import ilya.observerlaba.Observer.Subject;

public class TimeObserver implements IObserver {
    private final Subject subject; // либо делать IObserver не как интерфейс, а как абстрактный класс с Subject полем
    private int currentTicks = 0;

    public TimeObserver(final Subject subject) {
        this.subject = subject;
    }

    @Override
    public void update() {
        this.currentTicks = this.subject.getStatus();
    }

    public int getCurrentTicks() {
        return this.currentTicks;
    }
}
