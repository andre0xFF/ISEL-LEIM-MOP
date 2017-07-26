package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.*;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile15 extends Tile {

    public Tile15() {
        super(
                new Castle(false, false),
                new Field(),
                new Castle(false, false),
                new Field(),
                new Marker[] {
                        new NorthMarker(),
                        new EastMarker(),
                        new SouthMarker(),
                        new WestMarker()
                },
                3,
                "Tile15_cfcf_nc_x3.jpg"
        );
    }

    @Override
    protected Tile15 poly_clone() {
        return new Tile15();
    }
}
