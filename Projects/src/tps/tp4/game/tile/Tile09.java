package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile09 extends Tile {

    public Tile09() {
        super(
                new Castle(true, true),
                new Field(),
                new Field(),
                new Castle(true, true),
                2,
                "Tile09_cffc_2_x2.jpg"
        );
    }

    @Override
    protected Tile09 poly_clone() {
        return new Tile09();
    }
}
