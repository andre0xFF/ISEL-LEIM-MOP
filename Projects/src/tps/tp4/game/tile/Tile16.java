package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.marker.NorthMarker;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile16 extends Tile {

    public Tile16() {
        super(
                new Castle(),
                new Field(),
                new Field(),
                new Field(),
                new Marker[] {
                        new NorthMarker(),
                        new CenterMarker(),
                },
                5,
                "Tile16_cfff_x5.jpg"
        );
    }

    @Override
    protected Tile16 poly_clone() {
        return new Tile16();
    }
}
