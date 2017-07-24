package tps.tp4.view.component;

import tps.tp4.mvc.GameViewComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoardView extends JPanel implements GameViewComponent {

    private int columns;
    private int rows;
    private int width;
    private int height;
    private TileView[][] tiles;

    public BoardView() {
        this.size(14, 20);
        this.initialize();
    }

    private void initialize() {
        for (int l = 0; l < this.tiles.length; l++) {
            for (int c = 0; c < this.tiles[l].length; c++) {
                this.tiles[l][c] = new TileView();
            }
        }

//        super.revalidate();
    }

    public void size(int rows, int columns) {
        this.rows = rows;
        this.height = TileView.SIZE * this.rows;
        this.columns = columns;
        this.width = TileView.SIZE * this.columns;
        super.setSize(new Dimension(this.width, this.height));
        super.setPreferredSize(new Dimension(this.width, this.height));
        this.tiles = new TileView[this.rows][this.columns];
        this.initialize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw((Graphics2D) g);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform transform = g.getTransform();

        for (int l = 0; l < this.tiles.length; l++) {
            for (int c = 0; c < this.tiles[l].length; c++) {
                this.tiles[l][c].draw(g);
                g.translate(TileView.SIZE, 0);
            }
            g.translate(-1 * this.tiles[l].length * TileView.SIZE, TileView.SIZE);
        }

        g.setTransform(transform);
    }
}
