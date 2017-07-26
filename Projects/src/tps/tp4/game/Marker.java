package tps.tp4.game;

import java.awt.*;

public abstract class Marker implements ViewComponent {

    public static final int SIZE = 10;

    public final int X;
    public final int Y;

    private Player player;
    private boolean active = false;

    public Marker(int line, int column) {
        this.X = column * Tile.SIZE / 3 - 3 * Marker.SIZE / 2;
        this.Y = line * Tile.SIZE / 3 - 3 * Marker.SIZE / 2;
    }

    @Override
    public void draw(Graphics2D g) {
        if (!this.active || this.player == null) {
            return;
        }

        g.setColor(this.player.color);
        g.fillRect(X, Y, SIZE, SIZE);
    }

    public void player(Player player) {
        this.player = player;
        this.active = true;
    }

    public boolean active() {
        return this.active;
    }

    @Override
    public void listeners() {

    }
}
