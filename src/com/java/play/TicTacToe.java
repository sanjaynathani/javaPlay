package com.java.play;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class TicTacToe {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Board board = new Board(3);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Player playerX = new Player('X', "Player X Thread", board);
        Player playerO = new Player('O', "Player O Thread", board);

        FutureTask<Result>  playerXTask = new FutureTask<>(playerX);
        FutureTask<Result>  playerOTask = new FutureTask<>(playerO);

        executorService.submit(playerXTask);
        executorService.submit(playerOTask);

        while(!executorService.isTerminated()){
            if(playerOTask.isDone() || playerXTask.isDone()){
                executorService.shutdownNow();
                Result resultX = playerXTask.get();
                Result resultO = playerOTask.get();
                if(resultX.equals(Result.WIN)){
                    System.out.println(playerX.getSymbol() +" Won!!");
                    executorService.shutdownNow();
                }else if(resultO.equals(Result.WIN)){
                    System.out.println(playerO.getSymbol() +" Won!!");
                    executorService.shutdownNow();
                }else if(resultX.equals(Result.DRAW) || resultO.equals(Result.DRAW)){
                    System.out.println("Its a Draw!");
                    executorService.shutdownNow();
                }
            }
        }
    }
}

class Player implements Callable<Result> {

    private Character symbol;
    private String name;
    private Board board;

    public Player(Character symbol, String name, Board board) {
        this.symbol = symbol;
        this.name = name;
        this.board = board;
    }

    public char getSymbol(){
        return this.symbol;
    }


    @Override
    public Result call() throws Exception {
        Result result = Result.CONTINUE;
        while (result.equals(Result.CONTINUE)) {
            synchronized(board) {
                result = play();
                if(result.equals(Result.WIN)) {
                    return result;
                }
            }
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){break;}
        }
        return result;
    }

    private Result play(){
        System.out.println(this.name+"'s move");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] data = line.split(" ");
        int row = Integer.parseInt(data[0]);
        int col = Integer.parseInt(data[1]);
        return this.board.fillBoard(row, col, this);
    }

}

enum Result {
    WIN(1),
    DRAW(2),
    CONTINUE(3);

    int num;

    private Result(int num){
        this.num = num;
    }
}


class Board {

    private int size;
    private char[][] boardArray;
    private int drawCounter;

    public Board(int size) {
        this.size = size;
        boardArray = new char[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                boardArray[i][j] = ' ';
            }
        }
    }

    public Result fillBoard(int row, int col, Player player) {
        this.drawCounter++;
        this.boardArray[row][col] = player.getSymbol();
        if(isWin(row, col, player)){
            return  Result.WIN;
        }else if(isDraw()) {
            return Result.DRAW;
        }else{
            return Result.CONTINUE;
        }
    }

    private boolean isWin(int row, int col, Player player){
        boolean winner = true;
        for(int i=row; i<this.size; i++){
            if(this.boardArray[i][col] != player.getSymbol()){
                winner = false;
                break;
            }
        }
        if(winner){
            for(int i=row; i>=0; i--){
                if(this.boardArray[i][col] != player.getSymbol()){
                    winner = false;
                    break;
                }
            }
        }
        winner = true;
        for(int j=col; j<this.size; j++){
            if(this.boardArray[row][j] != player.getSymbol()){
                winner = false;
                break;
            }
        }
        if(winner){
            for(int j=col; j>=0; j--){
                if(this.boardArray[row][j] != player.getSymbol()){
                    winner = false;
                    break;
                }
            }
        }
        if(winner) return true;
        else if(row == col){ //diagonal
            winner = true;
            for(int i=row; i<size; i++){
                if(this.boardArray[i][i] != player.getSymbol()){
                    winner = false;
                    break;
                }
            }
            if(winner){
                for(int i=row; i>=0; i--){
                    if(this.boardArray[i][i] != player.getSymbol()){
                        winner = false;
                        break;
                    }
                }
                if(winner) return true;
            }
        }
        return false;
    }

    public boolean isDraw(){
        return this.drawCounter == size * size;
    }
}