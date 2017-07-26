package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;

public class Tile03 extends Tile {

    public Tile03() {
        super(
                new Castle(true, true),
                new Castle(true, true),
                new Castle(true, true),
                new Castle(true, true),
                2,
                "Tile03_cccc_2_x1.jpg"
        );
    }

    @Override
    protected Tile03 poly_clone() {
        return new Tile03();
    }
}
