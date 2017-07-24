package tps.tp4;

import tps.tp4.view.GameController;
import tps.tp4.model.Game;
import tps.tp4.view.GameView;

public class CarcassonneApp {

    public static void main(String[] args) {
        Game game = new tps.tp4.model.Game();
        GameController controller = new GameController(game);
        GameView view = new GameView();

        controller.set(view);
    }
}
