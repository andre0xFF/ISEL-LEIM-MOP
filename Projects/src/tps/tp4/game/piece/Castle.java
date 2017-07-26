package tps.tp4.game.piece;

import tps.tp4.game.CenterPiece;
import tps.tp4.game.EdgePiece;
import tps.tp4.game.Piece;

public class Castle extends Piece implements EdgePiece, CenterPiece {

    public static final char SYMBOL = 'C';

    public final boolean connected;
    public final boolean shield;

    public Castle() {
        this(true, false);
    }

    public Castle(boolean connected, boolean shield) {
        super(SYMBOL, 1);
        this.connected = connected;
        this.shield = shield;
    }
}
