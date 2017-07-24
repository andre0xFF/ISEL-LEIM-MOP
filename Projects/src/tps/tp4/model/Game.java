package tps.tp4.model;

import tps.tp4.mvc.AbstractModel;

public class Game extends AbstractModel {

    private Board board = new Board();

    public Board board() {
        return this.board;
    }
}
