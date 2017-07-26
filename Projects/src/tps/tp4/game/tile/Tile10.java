package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Road;

public class Tile10 extends Tile {

    public Tile10() {
        super(
                new Castle(),
                new Road(),
                new Road(),
                new Castle(),
                3,
                "Tile10_crrc_x3.jpg"
        );
    }

    @Override
    protected Tile10 poly_clone() {
        return new Tile10();
    }
}
