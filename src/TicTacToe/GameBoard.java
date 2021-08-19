package TicTacToe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameBoard {
    char[][]board;
    int boardSize;
    Queue<Player>nextTurn;
    Scanner input;
    int []rowArray;
    int []colArray;
    int dlr,drl;

    public GameBoard(int boardSize, Player[] players){
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
        nextTurn = new LinkedList<>();
        nextTurn.offer(players[0]);
        nextTurn.offer(players[1]);
        input = new Scanner(System.in);
        dlr=0;
        drl=0;
        rowArray = new int[boardSize];
        colArray = new int[boardSize];
        initializeBoard();
    }

    public void initializeBoard(){
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                this.board[i][j] = 'B';
            }
        }
    }

    public void startGame(){
        int count=0;
        while(true)
        {
            count++;
            if(count>boardSize*boardSize){
                System.out.println("Match is a Draw");
                break;
            }

            Player p = nextTurn.poll();
            int []pos = getUserInput(p);
            int row = pos[0];
            int col = pos[1];

            if(p.getPlayerSymbol()=='X'){
                rowArray[row]++;
                colArray[col]++;
                if(row==col){
                    dlr++;
                }
                if(row+col==boardSize-1){
                    drl++;
                }
            }else{
                rowArray[row]--;
                colArray[col]--;
                if(row==col){
                    dlr--;
                }
                if(row+col==boardSize-1){
                    drl--;
                }
            }


            board[row][col] = p.getPlayerSymbol();
            printBoard();
            System.out.println("Board after the move");
            if (checkEndGame(row,col)) {
                System.out.println(p.getPlayerName()+" has won the Game!!");
                break;
            }
            nextTurn.offer(p);

        }
    }

    private boolean checkEndGame(int row, int col) {
        if(rowArray[row]==boardSize||colArray[col]==boardSize
            ||rowArray[row]==-1*boardSize||colArray[col]==-1*boardSize
        || dlr==boardSize||dlr==-1*boardSize||drl==boardSize||drl==-1*boardSize)
            return true;
        return false;
    }

    public int[] getUserInput(Player p) {
        System.out.println(p.getPlayerName()+" Please enter a row and col of your next move");

        int []userInput = new int [2];
         int row = input.nextInt();
        int col = input.nextInt();

        while(!invalidInput(row,col))
        {
            System.out.println(p.getPlayerName()+" Please enter a valid Input");
             row = input.nextInt();
             col = input.nextInt();
        }

        userInput[0]=row;
        userInput[1]=col;

        return userInput;
    }

    private boolean invalidInput(int row, int col) {
        if(row>=boardSize|| row<0 || col>=boardSize|| col<0)
            return false;
        if(board[row][col]!='B')
            return false;
        return true;
    }

    public void printBoard(){
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
               System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}
