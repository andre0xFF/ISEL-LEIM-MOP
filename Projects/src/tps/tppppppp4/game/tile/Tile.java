package tps.tppppppp4.game.tile;

import org.imgscalr.Scalr;
import tps.tppppppp4.game.ViewComponent;
import tps.tppppppp4.game.piece.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Tile extends JComponent implements ViewComponent {

    public static final String PATH = "./resource/tile/";

    public static final HashMap<String, EdgePiece> EDGE_PIECES = new HashMap<String, EdgePiece>() {{
       put(Castle.SYMBOL, new Castle());
       put(Field.SYMBOL, new Field());
       put(Road.SYMBOL, new Road());
    }};

    public final int QUANTITY;

    private EdgePiece north;
    private EdgePiece east;
    private EdgePiece south;
    private EdgePiece west;
    private MiddlePiece center;


    public final static int SIZE = 55;
    private BufferedImage image;
    private int rotation_angle = 0;
    private String filename;

    public abstract Tile get();

    public Tile(String filename, int quantity) {
        super.setSize(new Dimension(Tile.SIZE, Tile.SIZE));
        super.setPreferredSize(new Dimension(Tile.SIZE, Tile.SIZE));

        QUANTITY = quantity;

        if (filename == null) {
            return;
        }

        try {
            this.filename = filename;
            this.image = ImageIO.read(new File(PATH.concat(filename)));
            //  this.image = Scalr.resize(this.image, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.pieces();
    }

    public final Tile[] generate() {
        Tile[] tiles = new Tile[QUANTITY];

        for (int i = 0 ; i < QUANTITY; i++) {
            tiles[i] = this.get();
        }

        return tiles;
    }

    private final void pieces() {
        String word = this.filename.split("_")[1];

        this.north = EDGE_PIECES.get(String.valueOf(word.charAt(0)));
        this.north.load(this.filename);
        this.east = EDGE_PIECES.get(String.valueOf(word.charAt(1)));
        this.east.load(this.filename);
        this.south = EDGE_PIECES.get(String.valueOf(word.charAt(2)));
        this.south.load(this.filename);
        this.west = EDGE_PIECES.get(String.valueOf(word.charAt(3)));
        this.west.load(this.filename);

        if (this.filename.contains(String.format("_%s_", Church.SYMBOL))) {
            this.center = new Church();
            this.center.load(this.filename);
        }
    }

    @Override
    public final void draw(Graphics2D g) {
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, Tile.SIZE, Tile.SIZE, null);
        }
    }

    public final void rotate_image(int angle) {
        this.rotation_angle = angle;
        this.image = Scalr.rotate(this.image, Scalr.Rotation.CW_90, Scalr.OP_ANTIALIAS);
    }
}
