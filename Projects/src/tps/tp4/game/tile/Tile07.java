package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.marker.SouthMarker;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Road;

public class Tile07 extends Tile {

    public Tile07() {
        super(
                new Castle(true, true),
                new Castle(true, true),
                new Road(),
                new Castle(true, true),
                new Marker[] {
                        new CenterMarker(),
                        new SouthMarker()
                },
                2,
                "Tile07_ccrc_2_x2.jpg"
        );
    }

    @Override
    protected Tile07 poly_clone() {
        return new Tile07();
    }
}
