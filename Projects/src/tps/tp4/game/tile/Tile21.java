package tps.tp4.game.tile;

import tps.tp4.game.Marker;
import tps.tp4.game.Tile;
import tps.tp4.game.marker.CenterMarker;
import tps.tp4.game.marker.EastMarker;
import tps.tp4.game.marker.WestMarker;
import tps.tp4.game.piece.Field;
import tps.tp4.game.piece.Road;

public class Tile21 extends Tile {

    public Tile21() {
        super(
                new Road(),
                new Field(),
                new Road(),
                new Field(),
                new Marker[] {
                        new CenterMarker(),
                        new EastMarker(),
                        new WestMarker(),
                },
                8,
                "Tile21_rfrf_x8.jpg"
        );
    }

    @Override
    protected Tile21 poly_clone() {
        return new Tile21();
    }
}
