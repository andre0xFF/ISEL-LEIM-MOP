package tps.tp4.game;

import tps.tp4.game.tile.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class Deck extends JComponent implements ViewComponent {

    private ArrayList<Tile> tiles = new ArrayList<>();
    private Tile up_tile;

    public Deck() {
        this.tiles.add(new Tile01());
        this.tiles.add(new Tile02());
        this.tiles.add(new Tile03());
        this.tiles.add(new Tile04());
        this.tiles.add(new Tile05());
        this.tiles.add(new Tile06());
        this.tiles.add(new Tile07());
        this.tiles.add(new Tile08());
        this.tiles.add(new Tile09());
        this.tiles.add(new Tile10());
        this.tiles.add(new Tile11());
        this.tiles.add(new Tile12());
        this.tiles.add(new Tile13());
        this.tiles.add(new Tile14());
        this.tiles.add(new Tile15());
        this.tiles.add(new Tile16());
        this.tiles.add(new Tile17());
        this.tiles.add(new Tile18());
        this.tiles.add(new Tile19());
        this.tiles.add(new Tile20());
        this.tiles.add(new Tile21());
        this.tiles.add(new Tile22());
        this.tiles.add(new Tile23());
        this.tiles.add(new Tile24());

        ArrayList<Tile> clones = new ArrayList<>();

        for (Tile tile : this.tiles) {
            int q = tile.QUANTITY;

            for (int i = 1; i < q; i++) {
                clones.add(tile.clone());
            }
        }

        this.tiles.addAll(clones);
        this.up_tile = this.deal();

        this.listeners();
        super.setVisible(true);
    }

    private Tile deal() {
        if (this.tiles.size() == 0) {
            return null;
        }

        Random random = new Random();
        int id = random.nextInt(this.tiles.size());
        Tile tile = this.tiles.get(id);
        this.tiles.remove(tile);

        return tile;
    }

    public Tile get() {
        Tile tile = this.up_tile;
        this.up_tile = this.deal();
        repaint();
        return tile;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw((Graphics2D) g);
    }

    @Override
    public void draw(Graphics2D g) {
        if (this.up_tile == null) {
            return;
        }

        AffineTransform transform = g.getTransform();
        this.up_tile.draw(g);
        g.setTransform(transform);
        g.dispose();
    }

    public void on_click() {
        up_tile.rotate();
        repaint();
    }

    @Override
    public void listeners() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            on_click();
            }
        });
    }
}
