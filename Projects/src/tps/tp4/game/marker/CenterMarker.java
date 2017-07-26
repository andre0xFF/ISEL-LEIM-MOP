package tps.tp4.game.marker;

import tps.tp4.game.Marker;
import tps.tp4.game.Player;
import tps.tp4.game.Tile;

public class CenterMarker extends Marker {

    private static final int LINE = 2;
    private static final int COLUMN = 2;


    public CenterMarker() {
        super(LINE, COLUMN);
    }
}
