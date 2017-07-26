package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.marker.SouthMarker;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile04 extends Tile {

    public Tile04() {
        super(
                new Castle(),
                new Castle(),
                new Field(),
                new Castle(),
                new Marker[] {
                        new CenterMarker(),
                        new SouthMarker()
                },
                3,
                "Tile04_ccfc_x3.jpg"
        );
    }

    @Override
    protected Tile04 poly_clone() {
        return new Tile04();
    }
}
