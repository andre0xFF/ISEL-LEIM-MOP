package tps.tp4.game.piece;

import tps.tp4.game.EdgePiece;
import tps.tp4.game.Piece;

public class Field extends Piece implements EdgePiece {

    public static final char SYMBOL = 'F';

    public Field() {
        super(SYMBOL, 1);
    }
}
