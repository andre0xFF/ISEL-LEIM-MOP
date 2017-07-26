package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.NorthEastMarker;
import tps.tp4.game.marker.NorthWestMarker;
import tps.tp4.game.marker.SouthEastMarker;
import tps.tp4.game.marker.SouthWestMarker;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile08 extends Tile {

    public Tile08() {
        super(
                new Castle(),
                new Field(),
                new Field(),
                new Castle(),
                new Marker[] {
                        new NorthWestMarker(),
                        new SouthEastMarker(),
                },
                3,
                "Tile08_cffc_x3.jpg"
        );
    }


    @Override
    protected Tile08 poly_clone() {
        return new Tile08();
    }
}
