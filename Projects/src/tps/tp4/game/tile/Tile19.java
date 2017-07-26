package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Road;

public class Tile19 extends Tile {

    public Tile19() {
        super(
                new Castle(),
                new Road(),
                new Road(),
                new Road(),
                3,
                "Tile19_crrr_x3.jpg"
        );
    }

    @Override
    protected Tile19 poly_clone() {
        return new Tile19();
    }
}
