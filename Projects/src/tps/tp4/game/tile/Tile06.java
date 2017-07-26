package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.marker.SouthMarker;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Road;

public class Tile06 extends Tile {

    public Tile06() {
        super(
                new Castle(),
                new Castle(),
                new Road(),
                new Castle(),
                new Marker[] {
                        new CenterMarker(),
                        new SouthMarker()
                },
                1,
                "Tile06_ccrc_x1.jpg"
        );
    }

    @Override
    protected Tile06 poly_clone() {
        return new Tile06();
    }
}
