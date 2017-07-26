package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;
import tps.tp4.game.piece.Road;

public class Tile17 extends Tile {

    public Tile17() {
        super(
                new Castle(),
                new Field(),
                new Road(),
                new Road(),
                3,
                "Tile17_cfrr_x3.jpg"
        );
    }

    @Override
    protected Tile17 poly_clone() {
        return new Tile17();
    }
}
