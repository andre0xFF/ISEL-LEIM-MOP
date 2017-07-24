package tps.tp4.model;

// A classe Board deve conter o tabuleiro em si e deve ser ela própria um JPanel dividido em grelha com as
// quadrículas pretendidas e ter métodos para: colocar uma peça; obter a peça nas coordenadas x e y; colocar
// um marcador numa característica de uma peça; remover marcadores de uma característica numa peça e nas
// peças que dão continuidade a essa característica; limpar o tabuleiro; e outros que forem necessários.

public class Board {

    private int columns = 20;
    private int rows = 14;

    public int columns() {
        return this.columns;
    }

    public int rows() {
        return this.rows;
    }
}
