package tps.tppppppp4.game.tile;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Deck extends JComponent {

    ArrayList<Tile> deck = new ArrayList<>();

    public Deck() {
        this.load();
    }

    private void load() {
        File[] files = new File(Tile.PATH).listFiles();

        for (File file : files) {
            this.deck.add(tile);
        }
    }
}
