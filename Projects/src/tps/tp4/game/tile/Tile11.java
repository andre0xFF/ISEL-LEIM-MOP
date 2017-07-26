package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Castle;
import tps.tp4.game.piece.Road;

public class Tile11 extends Tile {

    public Tile11() {
        super(
                new Castle(true, true),
                new Road(),
                new Road(),
                new Castle(true, true),
                2,
                "Tile11_crrc_2_x2.jpg"
        );
    }

    @Override
    protected Tile11 poly_clone() {
        return new Tile11();
    }
}
