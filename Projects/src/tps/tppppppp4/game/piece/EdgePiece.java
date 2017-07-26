package tps.tppppppp4.game.piece;

public abstract class EdgePiece extends Piece {
    public EdgePiece(String symbol, int value, boolean connected, boolean shield) {
        super(symbol, value, connected, shield);
    }

    public void load(String filename) {
        if (filename == null) {
            return;
        }

        String[] split = filename.split("_");
        String word = split[1];

        if (!word.contains(String.valueOf(this.symbol))) {
            return;
        }

        boolean connected = filename.contains("_nc_");
        boolean shield = filename.contains("_2_");

        super.connected(connected);
        super.shield(shield);
    }
}
