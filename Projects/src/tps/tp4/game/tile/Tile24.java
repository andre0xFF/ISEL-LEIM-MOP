package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.piece.Road;

public class Tile24 extends Tile {

    public Tile24() {
        super(
                new Road(),
                new Road(),
                new Road(),
                new Road(),
                new Marker[] {
                        new CenterMarker(),
                },
                1,
                "Tile24_rrrr_x1.jpg"
        );
    }

    @Override
    protected Tile24 poly_clone() {
        return new Tile24();
    }
}
