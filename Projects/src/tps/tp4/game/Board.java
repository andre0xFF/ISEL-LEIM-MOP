package tps.tp4.game;

import tps.tp4.game.aux.RingList;
import tps.tp4.game.tile.Tile20;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Collection;
import java.util.HashMap;

public class Board extends JPanel implements ViewComponent {

    public static final Tile INITIAL_TILE = new Tile20();
    public static final int DEFAULT_LINES = 14;
    public static final int DEFAULT_COLUMNS = 20;
    public static final int MAX_PLAYERS = 5;
    public static final int MAX_PLAYERS_MARKERS = 8;
    public static final int MAX_MARKERS = MAX_PLAYERS_MARKERS * 5;

    private final int rows;
    private final int columns;
    private final Tile[][] tiles;

    private HashMap<Player, Integer> markers_count = new HashMap<>();
    private RingList<Player> players = new RingList<>();

    private Tile last_tile;
    private Player last_player;

    public Board(Collection players) {
        this(DEFAULT_LINES, DEFAULT_COLUMNS, players);
    }

    public Board(int rows, int columns, Collection players) {
        this.rows = rows;
        this.columns = columns;

        this.tiles = new Tile[this.rows][this.columns];
        this.tiles[this.rows / 2][this.columns / 2] = INITIAL_TILE;

        this.players.addAll(players);
        this.players.get(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw((Graphics2D) g);
    }

    public RingList<Player> players() {
        return this.players;
    }

    @Override
    public void draw(Graphics2D g) {
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
        // Row outside of range
        if (row < 0 || row > this.tiles.length) {
            return false;
        }

        // Column outside of range
        if (column < 0 || column > this.tiles[0].length) {
            return false;
        }

        // Tile should not be empty
        if (!this.empty(row, column)) {
            return false;
        }

        Player player = this.players.get();

        // A player can only play once
        if (this.last_player != null && this.last_player.equals(player)) {
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

        // If tile's pieces can be connected
        if (!eval) {
            return false;
        }

        // TODO: Check if this tile closed any group of pieces.
        // If yes, attribute score to player, remove and return the markers

        this.tiles[row][column] = tile;
        this.last_tile = tile;
        this.last_player = player;
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

    public boolean marker(Point point) {
        // Can't place a marker more than once per play
        if (this.last_tile == null) {
            return false;
        }

        // Tile should not be empty
        if (this.empty(point)) {
            return false;
        }

        Player player = this.players().get();
        int count = this.markers_count(player);

        if (count == 0) {
            return false;
        }

        int l = this.coordinates(point.y);
        int c = this.coordinates(point.x);

        point.x -= c * Tile.SIZE;
        point.y -= l * Tile.SIZE;

        // Can only place marker on the last played tile
        if (!this.last_tile.equals(this.tiles[l][c])) {
            return false;
        }

        // If marker can be placed on coordinates
        if (!this.tiles[l][c].marker(player, point)) {
            return false;
        }

        // TODO: Check if connected pieces already contain a marker

        this.markers_count.put(player, --count);
        this.last_tile = null;
        repaint();

        return true;
    }

    public void end() {
        // Count how many pieces have at least one marker.

        // A closed castle touching a field gets 3 points. A field can only be counted once.

        // A closed castle gets 2 points for each castle piece.

        // Castle pieces with shields count double.

        // Road piece gets 1 point, closed or not.

        // A church is closed if all fields around are not empty

        // If a group of pieces contains markers from multiple players, then the player
        // with more markers gets the points. In case or draw everyone gets points.
    }

    @Override
    public void listeners() { }
}
