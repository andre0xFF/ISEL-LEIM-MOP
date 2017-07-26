package tps.tp4.game;

import java.awt.*;

public abstract class Piece implements ViewComponent {

    public final char symbol;
    public final int value;

    private Marker marker;

    protected Piece(char symbol, int value) {
        this(symbol, value, null);
    }

    protected Piece(char symbol, int value, Marker marker) {
        this.symbol = symbol;
        this.value = value;
        this.marker = marker;
    }

    public Marker marker() {
        return this.marker;
    }

    public void marker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (symbol != piece.symbol) return false;
        return value == piece.value;
    }

    @Override
    public int hashCode() {
        int result = (int) symbol;
        result = 31 * result + value;
        return result;
    }

    @Override
    public void draw(Graphics2D g) {
        this.marker.draw(g);
    }

    @Override
    public void listeners() { }
}
