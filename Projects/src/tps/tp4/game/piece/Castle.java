package tps.tp4.game.piece;

public class Castle extends EdgePiece {

    public final boolean connected;
    public final boolean shield;

    public Castle() {
        this(true, false);
    }

    public Castle(boolean connected, boolean shield) {
        super("c", 1);
        this.connected = connected;
        this.shield = shield;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Castle castle = (Castle) o;

        if (connected != castle.connected) return false;
        return shield == castle.shield;
    }

    @Override
    public int hashCode() {
        int result = (connected ? 1 : 0);
        result = 31 * result + (shield ? 1 : 0);
        return result;
    }
}
