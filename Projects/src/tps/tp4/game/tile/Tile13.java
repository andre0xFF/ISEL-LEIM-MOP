package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile13 extends Tile {

    public Tile13() {
        super(
                new Field(),
                new Castle(true, true),
                new Field(),
                new Castle(true, true),
                2,
                "Tile13_fcfc_2_x2.jpg"
        );
    }

    @Override
    protected Tile13 poly_clone() {
        return new Tile13();
    }
}
