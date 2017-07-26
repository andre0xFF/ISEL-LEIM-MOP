package tps.tppppppp4.game.piece;

public class Castle extends EdgePiece {


    public static final String SYMBOL = "c";

    public Castle() {
        this(true, false);
    }

    public Castle(boolean connected, boolean shield) {
        super(Castle.SYMBOL, 1, connected, shield);
    }
}
