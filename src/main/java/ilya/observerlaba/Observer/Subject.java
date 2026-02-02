package ilya.observerlaba.Observer;

public interface Subject {
    public abstract boolean attach(final IObserver observer);

    public abstract boolean detach(final IObserver observer);

    public abstract void notifyAllObservers();

    public abstract int getStatus();
}
