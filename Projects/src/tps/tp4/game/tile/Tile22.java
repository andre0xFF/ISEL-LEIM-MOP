package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Field;
import tps.tp4.game.piece.Road;

public class Tile22 extends Tile {

    public Tile22() {
        super(
                new Field(),
                new Field(),
                new Road(),
                new Road(),
                9,
                "Tile22_ffrr_x9.jpg"
        );
    }

    @Override
    protected Tile22 poly_clone() {
        return new Tile22();
    }
}
