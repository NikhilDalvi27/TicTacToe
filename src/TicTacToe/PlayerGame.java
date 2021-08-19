package TicTacToe;

public class PlayerGame {
    public static void main(String[] args) {
        Player p1 = new Player();
        p1.setPlayerId(1);
        p1.setPlayerName("Nikhil");
        p1.setPlayerSymbol('X');

        Player p2 = new Player();
        p2.setPlayerId(2);
        p2.setPlayerName("John");
        p2.setPlayerSymbol('O');

        Player[]players = {p1,p2};
        GameBoard  gameBoard = new GameBoard(3,players);
        gameBoard.startGame();
    }
}
