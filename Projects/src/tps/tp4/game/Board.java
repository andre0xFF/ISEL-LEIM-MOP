package tps.tp4.game;

import tps.tp4.game.tile.Tile20;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

public class Board extends JPanel implements ViewComponent {

    private static final Tile INITIAL_TILE = new Tile20();
    private static final int DEFAULT_LINES = 14;
    private static final int DEFAULT_COLUMNS = 20;
    private static final int MAX_PLAYERS_MARKERS = 8;
    private static final int MAX_MARKERS = MAX_PLAYERS_MARKERS * 5;

    private final int rows;
    private final int columns;
    private final Tile[][] tiles;
    private HashMap<Player, Integer> markers_count = new HashMap<>();

    private Tile last_tile;

    public Board() {
        this(DEFAULT_LINES, DEFAULT_COLUMNS);
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.tiles = new Tile[this.rows][this.columns];
        this.tiles[this.rows / 2][this.columns / 2] = INITIAL_TILE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw((Graphics2D) g);
    }

    @Override
    public void draw(Graphics2D g) {
        int cc = 0;
        AffineTransform transform = g.getTransform();

        for (int l = 0; l < this.tiles.length; l++) {

            for (int c = 0; c < this.tiles[l].length; c++) {
                g.setTransform(transform);

                if (this.tiles[l][c] == null) {
                    continue;
                }

                g.translate(c * Tile.SIZE, l * Tile.SIZE);
                this.tiles[l][c].draw(g);
            }

        }

        g.dispose();
    }

    public boolean tile(Tile tile, Point point) {
        int l = this.coordinates(point.y);
        int c = this.coordinates(point.x);

        return this.tile(tile, l, c);
    }

    public boolean tile(Tile tile, int row, int column) {
        if (row < 0 || row > this.tiles.length) {
            return false;
        }

        if (column < 0 || column > this.tiles[0].length) {
            return false;
        }

        if (!this.empty(row, column)) {
            return false;
        }

        int north = row - 1 < 0 ? 0 : row - 1;
        int east = column + 1 >= this.columns ? this.columns - 1 : column + 1;
        int south = row + 1 >= this.rows ? this.rows - 1 : row + 1;
        int west = column - 1 < 0 ? 0 : column - 1;

        boolean eval = tile.connection(
                this.tiles[north][column],
                this.tiles[row][east],
                this.tiles[south][column],
                this.tiles[row][west]
        );

        if (!eval) {
            return false;
        }

        this.tiles[row][column] = tile;
        this.last_tile = tile;
        repaint();

        return true;
    }

    public boolean empty(Point point) {
        int l = this.coordinates(point.y);
        int c = this.coordinates(point.x);

        return this.empty(l, c);
    }

    public boolean empty(int line, int column) {
        return this.tiles[line][column] == null;
    }

    public int coordinates(int point_value) {
        return point_value / Tile.SIZE;
    }

    public int markers_count(Player player) {
        if (this.markers_count.containsKey(player)) {
            return this.markers_count.get(player);
        }

        this.markers_count.put(player, MAX_PLAYERS_MARKERS);
        return MAX_PLAYERS_MARKERS;
    }

    public boolean marker(Player player, Point point) {
        if (this.last_tile == null) {
            return false;
        }

        if (this.empty(point)) {
            return false;
        }

        int count = this.markers_count(player);

        if (count == 0) {
            return false;
        }

        int l = this.coordinates(point.y);
        int c = this.coordinates(point.x);

        point.x -= c * Tile.SIZE;
        point.y -= l * Tile.SIZE;

        if (!this.last_tile.equals(this.tiles[l][c])) {
            return false;
        }

        if (!this.tiles[l][c].marker(player, point)) {
            return false;
        }

        this.markers_count.put(player, --count);
        repaint();

        return true;
    }

    @Override
    public void listeners() {

    }
}
