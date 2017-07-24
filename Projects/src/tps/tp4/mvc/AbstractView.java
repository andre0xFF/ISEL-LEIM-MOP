package tps.tp4.mvc;

public abstract class AbstractView<T extends AbstractController> implements Runnable {

    private ViewListener listener;

    public void set(ViewListener listener) {
        this.listener = listener;
        this.listener.on_load();
    }
}