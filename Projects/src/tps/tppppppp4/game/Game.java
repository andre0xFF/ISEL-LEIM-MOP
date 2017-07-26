package tps.tppppppp4.game;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import tps.tppppppp4.game.piece.Piece;
import tps.tppppppp4.game.tile.Deck;
import tps.tppppppp4.game.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

// A classe Game deverá ser a Frame, que conterá uma zona central com o tabuleiro do jogo (Board), conterá
// nas laterais a informação acerca dos jogadores e deverá conter um menu. Esta classe deve: conter as peças
// por colocar, num contentor dinâmico; controlar quem será o jogador a jogar; e controlar o fim do jogo. Esta
// classe deverá implementar as jogadas; permitir rodar a peça que saiu ao jogador e colocá-la no tabuleiro;
// implementar o resto da jogada; e verificar e assinalar o fim de jogo se for caso disso.
// O menu deverá conter pelo menos: Ajuda, com as instruções para jogar; About, com: LEIM, MOP, 1617SI, a
// turma; o no e nomes dos alunos que realizaram o trabalho; e uma foto com todo o grupo. O menu também
// poderá ter algumas das funcionalidades nele incorporadas, tal como acesso às várias configurações. O
// tabuleiro por omissão será de 15 por 20 quadrículas, mas essas dimensões deverão ser configuráveis.

public class Game {

    public static final int MAX_PLAYERS = 5;

    private JFrame frame = new JFrame();
    private JPanel panel_game;
    private JPanel panel_players_container;
    private JPanel panel_player1;
    private JPanel panel_player2;
    private JPanel panel_player3;
    private JPanel panel_player4;
    private JPanel panel_player5;
    private JPanel panel_player_turn;
    private JPanel panel_main;
    private Tile next_tile;

    private Board board;
    private Player[] players;

    public Game(HashMap<String, Tile> tiles, HashMap<String, Piece> pieces) {
        frame.setTitle("Carcassonne");
        frame.setContentPane(this.panel_game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.listeners();
        this.start(1, 14, 20);
    }

    public void start(int players, int rows, int columns) {
        if (players > Game.MAX_PLAYERS) {
            players = 5;
        }

        if (players < 0) {
            players = 1;
        }

        this.players = new Player[players];

        for (int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player();
        }

        this.board = new Board(rows, columns);
    }


    public void listeners() {
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.add(next_tile, e.getX(), e.getY());
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel_game = new JPanel();
        panel_game.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel_players_container = new JPanel();
        panel_players_container.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel_game.add(panel_players_container, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, -1), null, null, 0, false));
        panel_player1 = new JPanel();
        panel_player1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel_player1.setEnabled(false);
        panel_players_container.add(panel_player1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel_player1.setBorder(BorderFactory.createTitledBorder("Player #1"));
        panel_player2 = new JPanel();
        panel_player2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel_player2.setEnabled(false);
        panel_players_container.add(panel_player2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel_player2.setBorder(BorderFactory.createTitledBorder("Player #2"));
        panel_player3 = new JPanel();
        panel_player3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel_player3.setEnabled(false);
        panel_players_container.add(panel_player3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel_player3.setBorder(BorderFactory.createTitledBorder("Player #3"));
        panel_player4 = new JPanel();
        panel_player4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel_player4.setEnabled(false);
        panel_players_container.add(panel_player4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel_player4.setBorder(BorderFactory.createTitledBorder("Player #4"));
        panel_player5 = new JPanel();
        panel_player5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel_player5.setEnabled(false);
        panel_players_container.add(panel_player5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel_player5.setBorder(BorderFactory.createTitledBorder("Player #5"));
        panel_player_turn = new JPanel();
        panel_player_turn.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel_game.add(panel_player_turn, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, -1), null, null, 0, false));
        panel_player_turn.setBorder(BorderFactory.createTitledBorder("Player turn"));
        final Spacer spacer1 = new Spacer();
        panel_player_turn.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel_player_turn.add(spacer2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel_player_turn.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Deck deck1 = new Deck();
        panel_player_turn.add(deck1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        panel_game.add(toolBar1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        panel_main = new JPanel();
        panel_main.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel_game.add(panel_main, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1200, 800), null, 0, false));
        board = new Board();
        panel_main.add(board, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel_game;
    }
}