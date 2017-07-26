package tps.tp4.game.piece;

import tps.tp4.game.CenterPiece;
import tps.tp4.game.Piece;
import tps.tp4.game.marker.CenterMarker;

public class Church extends Piece implements CenterPiece {

    public static final char SYMBOL = 'I';

    public Church() {
        super(SYMBOL, 1, new CenterMarker());
    }
}
