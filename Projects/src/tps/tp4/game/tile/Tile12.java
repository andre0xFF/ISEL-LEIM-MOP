package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile12 extends Tile {

    public Tile12() {
        super(
                new Field(),
                new Castle(),
                new Field(),
                new Castle(),
                1,
                "Tile12_fcfc_x1.jpg"
        );
    }

    @Override
    protected Tile12 poly_clone() {
        return new Tile12();
    }
}
