package tps.tp4.mvc;

public abstract class AbstractController<V extends AbstractView, M extends AbstractModel> {
    protected AbstractView view;
    protected M model;

    public AbstractController(M model) {
        this.model = model;
    }

    public final void set(V view) {
        this.view = view;
        this.listeners(view);
    }

    protected abstract void listeners(V view);
}
