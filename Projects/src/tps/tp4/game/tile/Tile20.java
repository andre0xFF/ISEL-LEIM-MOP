package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.marker.NorthMarker;
import tps.tp4.game.marker.SouthMarker;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;
import tps.tp4.game.piece.Road;

public class Tile20 extends Tile {

    public Tile20() {
        super(
                new Castle(),
                new Road(),
                new Field(),
                new Road(),
                new Marker[] {
                        new NorthMarker(),
                        new CenterMarker(),
                        new SouthMarker(),
                },
                4,
                "Tile20_crfr_x4.jpg"
        );
    }

    @Override
    protected Tile20 poly_clone() {
        return new Tile20();
    }
}
