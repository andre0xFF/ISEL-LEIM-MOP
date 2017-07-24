package tps.tp4.view.component;

import com.intellij.ui.RoundedLineBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OtherTile extends JButton {

    private BufferedImage image;

    public OtherTile() {
        super.setBorderPainted(true);
        super.setContentAreaFilled(false);
        super.setDefaultCapable(true);
        super.setEnabled(true);
        super.setFocusPainted(false);
        super.setFocusable(false);
        super.setOpaque(true);
        super.setRequestFocusEnabled(true);
        super.setRolloverEnabled(false);


//        String filepath = "/home/affonseca/Workspace/ISEL-LEIM/MOP/Projects/src/tps/tp4/resources/tiles/Tile01_ffff_i_x4.jpg";
//        this.image = ImageIO.read(new File(filepath));
        super.setSize(new Dimension(75, 75));
        super.setPreferredSize(new Dimension(75, 75));
        super.setMargin(new Insets(0, 0, 0, 0));
//        super.setBorder(BorderFactory.createEmptyBorder());
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        g.drawImage(this.image, 0, 0, this);
//        super.paintComponent(g);
//    }
}
