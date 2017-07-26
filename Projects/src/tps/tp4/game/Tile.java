package tps.tp4.game;

import org.imgscalr.Scalr;
import tps.tp4.game.marker.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Tile implements ViewComponent {

    public static final int SIZE = 70;
    public static final String PATH = "./resource/tile/";

    private Tile north_tile;
    private Tile east_tile;
    private Tile south_tile;
    private Tile west_tile;

    private Piece north_piece;
    private Piece east_piece;
    private Piece south_piece;
    private Piece west_piece;
    private Piece[] pieces;
    private Piece center_piece;

    private BufferedImage image;
    private int rotation = 0;
    protected final String FILENAME;
    public final int EQUAL_TILES;

    protected abstract Tile poly_clone();

    public Tile(Piece north, Piece east, Piece south,
                Piece west, Piece center,
                int quantity, String filename) {

        this.pieces = new Piece[] { north, east, south, west };
        this.north_piece = north;
        this.north_piece.marker(new NorthMarker());
        this.east_piece = east;
        this.east_piece.marker(new EastMarker());
        this.south_piece = south;
        this.south_piece.marker(new SouthMarker());
        this.west_piece = west;
        this.west_piece.marker(new WestMarker());
        this.center_piece = center;

        if (this.center_piece != null) {
            this.center_piece.marker(new CenterMarker());
        }

        this.EQUAL_TILES = quantity;
        this.FILENAME = filename;

        try {
            this.image = ImageIO.read(new File(PATH.concat(this.FILENAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tile(Piece north, Piece east, Piece south,
                Piece west, int quantity,
                String filename) {

        this(north, east, south, west, null, quantity, filename);
    }

    public void rotate() {
        this.rotation += 90;

        if (this.rotation == 360) {
            this.rotation = 0;
        }

        Piece aux = this.north_piece;
        this.north_piece = this.west_piece;
        this.west_piece = this.south_piece;
        this.south_piece = this.east_piece;
        this.east_piece = aux;

        this.image = Scalr.rotate(this.image, Scalr.Rotation.CW_90, Scalr.OP_ANTIALIAS);
    }

    public boolean marker(Player player, Point point) {
        int size = Tile.SIZE / 3;

        for (Piece piece : this.pieces) {
            boolean eval = piece.marker().set(player, point, Tile.SIZE / 3);

            if (eval) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Marker> markers() {
        ArrayList<Marker> markers = new ArrayList<>();

        for (Piece piece : this.pieces) {
            Marker marker = piece.marker();

            if (marker.active()) {
                markers.add(marker);
            }
        }

        return markers;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.image, 0, 0, Tile.SIZE, Tile.SIZE, null);

        for (Piece piece: this.pieces) {
            piece.draw(g);
        }
    }

    public Tile clone() {
        // Can't clone if there's an active marker
        for (Piece piece : this.pieces) {
            if (piece.marker().active()) {
                return null;
            }
        }

        return poly_clone();
    }

    public boolean connection(Tile north, Tile east, Tile south, Tile west) {
        return (
                (north == null || north != null && north.south_piece.equals(this.north_piece)) &&
                (east == null || east != null && east.west_piece.equals(this.east_piece)) &&
                (south == null || south != null && south.north_piece.equals(this.south_piece)) &&
                (west == null || west != null && west.east_piece.equals(this.west_piece)) &&
                (north != null || east != null || south != null || west != null)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (rotation != tile.rotation) return false;
        if (!north_piece.equals(tile.north_piece)) return false;
        if (!east_piece.equals(tile.east_piece)) return false;
        if (!south_piece.equals(tile.south_piece)) return false;
        if (!west_piece.equals(tile.west_piece)) return false;
        return center_piece != null ? center_piece.equals(tile.center_piece) : tile.center_piece == null;
    }

    @Override
    public int hashCode() {
        int result = north_piece.hashCode();
        result = 31 * result + east_piece.hashCode();
        result = 31 * result + south_piece.hashCode();
        result = 31 * result + west_piece.hashCode();
        result = 31 * result + (center_piece != null ? center_piece.hashCode() : 0);
        result = 31 * result + rotation;
        return result;
    }

    @Override
    public void listeners() { }
}
