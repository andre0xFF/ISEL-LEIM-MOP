package tps.tppppppp4.game.piece;

public abstract class Piece {
    protected final String symbol;
    protected int value;
    private boolean connected;
    private boolean shield;

    public Piece(String symbol, int value, boolean connected, boolean shield) {
        this.symbol = symbol;
        this.connected(connected);
        this.shield(shield);
    }

    public String symbol() {
        return this.symbol;
    }

    public int value() {
        return this.value;
    }

    public void connected(boolean connected) {
        this.connected = connected;
    }

    public void shield(boolean shield) {
        this.shield = shield;
        this.value *= 2;
    }

    public abstract void load(String filename);
}
