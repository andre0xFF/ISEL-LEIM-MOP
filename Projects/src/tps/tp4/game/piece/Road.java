package tps.tp4.game.piece;

import tps.tp4.game.EdgePiece;
import tps.tp4.game.Piece;

public class Road extends Piece implements EdgePiece {

    public static final char SYMBOL = 'R';

    public Road() {
        super(SYMBOL, 1);
    }
}
