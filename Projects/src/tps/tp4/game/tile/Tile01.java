package tps.tp4.game.tile;

import tps.tp4.game.Tile;
import tps.tp4.game.piece.Church;
import tps.tp4.game.piece.Field;

public class Tile01 extends Tile {

    public Tile01() {
        super(
                new Field(),
                new Field(),
                new Field(),
                new Field(),
                new Church(),
                4,
                "Tile01_ffff_i_x4.jpg"
        );
    }

    @Override
    protected Tile01 poly_clone() {
        return new Tile01();
    }
}
