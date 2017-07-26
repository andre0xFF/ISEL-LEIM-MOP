package tps.tp4.game;

import java.awt.*;

public abstract class Marker implements ViewComponent {

    public static final int SIZE = 10;

    public final Point point;

    private Player player;
    private boolean active = false;

    /**
     * Params reference Tile's division of rows and columns
     * not Board's rows and columns
     * @param line
     * @param column
     */
    public Marker(int line, int column) {
        this.point = new Point(
                column * Tile.SIZE / 3 - 3 * Marker.SIZE / 2,
                line * Tile.SIZE / 3 - 3 * Marker.SIZE / 2
        );
    }

    @Override
    public void draw(Graphics2D g) {
        if (!this.active || this.player == null) {
            return;
        }

        g.setColor(this.player.color());
        g.fillRect(this.point.x, this.point.y, SIZE, SIZE);
    }

    public boolean set(Player player, Point point, int offset) {
        boolean x_eval = point.x > this.point.x - offset && point.x < this.point.x + offset;
        boolean y_eval = point.y > this.point.y - offset && point.y < this.point.y + offset;

        if (!x_eval || !y_eval) {
            return false;
        }

        this.active = true;
        this.player = player;
        return true;
    }

    public boolean active() {
        return this.active;
    }

    @Override
    public void listeners() { }
}
