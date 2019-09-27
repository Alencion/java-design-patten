package TemplateMethod;

public class TemplateMethod {
}

abstract class Game{
    public Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }
    public void run(){
        start();
        while(!haveWinner())
            takeTurn();
        System.out.println("Player "+ getWinningPlayer()
        + " wins");
    }

    protected abstract void start();
    protected abstract boolean haveWinner();
    protected abstract void takeTurn();
    protected abstract int getWinningPlayer();

    protected int currentPlayer;
    protected final  int numberOfPlayer;
}

class Chess extends Game{
    private int maxTurns = 10;
    private int turn = 1;

    public Chess(int numberOfPlayer) {
        super(numberOfPlayer);
    }

    @Override
    protected void start() {
        System.out.println("Starting a game of chess.");
    }

    @Override
    protected boolean haveWinner() {
        return turn ==maxTurns;
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + (turn++) + " taken by player "+ currentPlayer);
    }

    @Override
    protected int getWinningPlayer() {
        return 0;
    }
}

class Demo{
    public static void main(String[] args) {

    }
}
