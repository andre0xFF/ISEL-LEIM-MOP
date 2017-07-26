package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile05 extends Tile {

    public Tile05() {
        super(
                new Castle(true, true),
                new Castle(true, true),
                new Field(),
                new Castle(true, true),
                1,
                "Tile05_ccfc_2_x1.jpg"
        );
    }

    @Override
    protected Tile05 poly_clone() {
        return new Tile05();
    }
}
