package tps.tppppppp4.game.piece;

public abstract class MiddlePiece extends Piece {
    public MiddlePiece(String symbol, int value, boolean connected, boolean shield) {
        super(symbol, value, connected, shield);
    }

    @Override
    public void load(String filename) {
        return;
    }
}
