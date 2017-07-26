package tps.tp4.game;

import org.imgscalr.Scalr;
import tps.tp4.game.piece.CenterPiece;
import tps.tp4.game.piece.EdgePiece;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Tile implements ViewComponent {

    public final static int SIZE = 70;
    public static final String PATH = "./resource/tile/";

    public final EdgePiece NORTH;
    public final EdgePiece EAST;
    public final EdgePiece SOUTH;
    public final EdgePiece WEST;
    public final CenterPiece CENTER;
    public final Marker[] MARKERS;

    private BufferedImage image;
    private int rotation = 0;
    protected final String FILENAME;
    public final int QUANTITY;

    protected abstract Tile poly_clone();

    public Tile(EdgePiece north, EdgePiece east, EdgePiece south,
                EdgePiece west, CenterPiece center, Marker[] markers,
                int quantity, String filename) {

        this.NORTH = north;
        this.EAST = east;
        this.SOUTH = south;
        this.WEST = west;
        this.CENTER = center;
        this.MARKERS = markers;
        this.QUANTITY = quantity;
        this.FILENAME = filename;

        try {
            this.image = ImageIO.read(new File(PATH.concat(this.FILENAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tile(EdgePiece north, EdgePiece east, EdgePiece south,
                EdgePiece west, Marker[] markers, int quantity,
                String filename) {

        this(north, east, south, west, null, markers, quantity, filename);
    }

    public void rotate() {
        this.rotation += 90;

        if (this.rotation == 360) {
            this.rotation = 0;
        }

        this.image = Scalr.rotate(this.image, Scalr.Rotation.CW_90, Scalr.OP_ANTIALIAS);
    }

    public void marker(Player player, Point point) {
        int size = Tile.SIZE / 3;

        for (Marker marker : this.MARKERS) {
            boolean x_eval = point.x > marker.X - size && point.x < marker.X + size;
            boolean y_eval = point.y > marker.Y - size && point.y < marker.Y + size;

            if (x_eval && y_eval) {
                marker.player(player);
                break;
            }
        }
    }

    public ArrayList<Marker> markers() {
        ArrayList<Marker> markers = new ArrayList<>();

        for (Marker marker : this.MARKERS) {
            if (marker.active()) {
                markers.add(marker);
            }
        }

        return markers;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.image, 0, 0, Tile.SIZE, Tile.SIZE, null);

        for (Marker marker : this.MARKERS) {
            marker.draw(g);
        }
    }

    public Tile clone() {
        // Can't clone if there's an active marker
        for (Marker marker : this.MARKERS) {
            if (marker.active()) {
                return null;
            }
        }

        return poly_clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (rotation != tile.rotation) return false;
        if (!NORTH.equals(tile.NORTH)) return false;
        if (!EAST.equals(tile.EAST)) return false;
        if (!SOUTH.equals(tile.SOUTH)) return false;
        if (!WEST.equals(tile.WEST)) return false;
        return CENTER != null ? CENTER.equals(tile.CENTER) : tile.CENTER == null;
    }

    @Override
    public int hashCode() {
        int result = NORTH.hashCode();
        result = 31 * result + EAST.hashCode();
        result = 31 * result + SOUTH.hashCode();
        result = 31 * result + WEST.hashCode();
        result = 31 * result + (CENTER != null ? CENTER.hashCode() : 0);
        result = 31 * result + rotation;
        return result;
    }

    @Override
    public void listeners() {

    }
}
