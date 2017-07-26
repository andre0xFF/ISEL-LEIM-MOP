package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Field;
import tps.tp4.game.piece.Road;

public class Tile18 extends Tile {

    public Tile18() {
        super(
                new Castle(),
                new Road(),
                new Road(),
                new Field(),
                3,
                "Tile18_crrf_x3.jpg"
        );
    }

    @Override
    protected Tile18 poly_clone() {
        return new Tile18();
    }
}
