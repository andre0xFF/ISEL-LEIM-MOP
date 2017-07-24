package tps.tp4.view.component;

import tps.tp4.mvc.GameViewComponent;

import javax.swing.*;
import java.awt.*;

public class TileView extends JComponent implements GameViewComponent {

    public final static int SIZE = 55;
    private Color color = Color.RED;

    public TileView() {
        super.setSize(new Dimension(TileView.SIZE, TileView.SIZE));
        super.setPreferredSize(new Dimension(TileView.SIZE, TileView.SIZE));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(this.color);
        g.drawRect(0, 0, TileView.SIZE, TileView.SIZE);
    }
}
