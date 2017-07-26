package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.marker.NorthMarker;
import tps.tp4.game.piece.Field;
import tps.tp4.game.piece.Road;

public class Tile23 extends Tile {

    public Tile23() {
        super(
                new Field(),
                new Road(),
                new Road(),
                new Road(),
                new Marker[] {
                        new NorthMarker(),
                        new CenterMarker(),
                },
                4,
                "Tile23_frrr_x4.jpg"
        );
    }

    @Override
    protected Tile23 poly_clone() {
        return new Tile23();
    }
}
