package tps.tp4.game;

import tps.tp4.game.tile.Tile20;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Board extends JPanel implements ViewComponent {

    private static final Tile INITIAL_TILE = new Tile20();
    private static final int DEFAULT_LINES = 14;
    private static final int DEFAULT_COLUMNS = 20;

    private final int rows;
    private final int columns;
    private final Tile[][] tiles;

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

    public boolean add(Tile tile, Point point) {
        int l = this.coordinates(point.y);
        int c = this.coordinates(point.x);

        return this.add(tile, l, c);
    }

    public boolean add(Tile tile, int line, int column) {
        if (line < 0 || line > this.tiles.length) {
            return false;
        }

        if (column < 0 || column > this.tiles[0].length) {
            return false;
        }

        if (!this.empty(line, column)) {
            return false;
        }

        this.tiles[line][column] = tile;
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

    @Override
    public void listeners() {

    }
}
