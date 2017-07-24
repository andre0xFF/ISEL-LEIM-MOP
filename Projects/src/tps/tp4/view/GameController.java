package tps.tp4.view;

import tps.tp4.model.Board;
import tps.tp4.model.Game;
import tps.tp4.mvc.AbstractController;
import tps.tp4.mvc.ViewListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController extends AbstractController<GameView, Game> {

    public GameController(Game model) {
        super(model);
    }

    @Override
    protected void listeners(GameView view) {
        view.set(new ViewListener() {

            @Override
            public void on_load() {
                Board board = model.board();
                view.board.size(board.rows(), board.columns());
            }
        });

        view.board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board_click(e.getX(), e.getY(), view);
            }
        });

        view.panel_player_turn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    private void board_click(int x, int y, GameView view) {

    }
}