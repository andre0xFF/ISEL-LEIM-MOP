package tps.tp4.game.aux;

import java.util.ArrayList;

public class RingList<T> extends ArrayList<T> {

    int id = 0;

    public T get() {
        return super.get(this.id);
    }

    public T next() {
        this.increment();
        return this.get();
    }

    private void increment() {
        this.id++;

        if (this.id >= super.size()) {
            this.id = 0;
        }
    }
}
