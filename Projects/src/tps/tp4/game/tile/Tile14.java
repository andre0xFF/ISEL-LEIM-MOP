package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.*;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile14 extends Tile {

    public Tile14() {
        super(
                new Castle(false, false),
                new Field(),
                new Field(),
                new Castle(false, false),
                new Marker[] {
                        new SouthEastMarker(),
                        new NorthMarker(),
                        new WestMarker(),
                },
                2,
                "Tile14_cffc_nc_x2.jpg"
        );
    }

    @Override
    protected Tile14 poly_clone() {
        return new Tile14();
    }
}
