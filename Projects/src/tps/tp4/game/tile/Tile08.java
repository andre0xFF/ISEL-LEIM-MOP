package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;

public class Tile08 extends Tile {

    public Tile08() {
        super(
                new Castle(),
                new Field(),
                new Field(),
                new Castle(),
                3,
                "Tile08_cffc_x3.jpg"
        );
    }


    @Override
    protected Tile08 poly_clone() {
        return new Tile08();
    }
}
