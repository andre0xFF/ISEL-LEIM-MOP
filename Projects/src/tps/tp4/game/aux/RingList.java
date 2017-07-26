package tps.tp4.game.aux;

import java.util.ArrayList;
import java.util.Random;

public class RingList<T> extends ArrayList<T> {

    int id = 0;

    public T get() {
        return super.get(this.id);
    }

    public T next() {
        this.increment();
        return this.get();
    }

    public T get(boolean random) {
        if (!random) {
            return null;
        }

        Random r = new Random();
        this.id = r.nextInt(super.size());
        return this.get(id);
    }

    private void increment() {
        this.id++;

        if (this.id >= super.size()) {
            this.id = 0;
        }
    }
}
