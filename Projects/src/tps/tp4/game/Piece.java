package tps.tp4.game;

public abstract class Piece {

    public final String symbol;
    public final int value;

    protected Piece(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (value != piece.value) return false;
        return symbol.equals(piece.symbol);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + value;
        return result;
    }
}
