package tps.tp4.game.piece;

import tps.tp4.game.CenterPiece;
import tps.tp4.game.Piece;

public class Church extends Piece implements CenterPiece {

    public static final char SYMBOL = 'I';

    public Church() {
        super(SYMBOL, 1);
    }
}
