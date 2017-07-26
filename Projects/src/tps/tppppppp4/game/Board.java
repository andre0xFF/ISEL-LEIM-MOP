package tps.tppppppp4.game;

import tps.tppppppp4.game.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

// A classe Board deve conter o tabuleiro em si e deve ser ela própria um JPanel dividido em grelha com as
// quadrículas pretendidas e ter métodos para: colocar uma peça; obter a peça nas coordenadas X e Y; colocar
// um marcador numa característica de uma peça; remover marcadores de uma característica numa peça e nas
// peças que dão continuidade a essa característica; limpar o tabuleiro; e outros que forem necessários.

public class Board extends JPanel implements ViewComponent {

    private int columns;
    private int rows;
    private int width;
    private int height;

    private Tile[][] tiles;
    private Tile last_tile;
    private LinkedList<Tile> deck = new LinkedList<>();

    public Board() {
        this(14, 20);
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.height = Tile.SIZE * this.rows;

        this.columns = columns;
        this.width = Tile.SIZE * this.columns;

        super.setSize(new Dimension(this.width, this.height));
        super.setPreferredSize(new Dimension(this.width, this.height));

        this.tiles = new Tile[this.rows][this.columns];

        for (int l = 0; l < this.tiles.length; l++) {
            for (int c = 0; c < this.tiles[l].length; c++) {

            }
        }

        int row = this.rows/ 2;
        int column = this.columns / 2;
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
                g.translate(Tile.SIZE, 0);
            }
            g.translate(-1 * this.tiles[l].length * Tile.SIZE, Tile.SIZE);
        }

        g.setTransform(transform);
        g.dispose();
    }

    public void add(Tile tile, int x, int y) {
        int row = x * this.columns / this.width;
        int column = y * this.rows / this.height;
        this.place(tile, row, column);
    }

    private void place(Tile tile, int row, int column) {
        this.last_tile = tile;
        this.tiles[row][column] = tile;
    }
}
