package com.company;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        Board board = new Board();



        MiniMaxPlayer white_player = new MiniMaxPlayer("white", 4, 8, board);
        MiniMaxPlayer black_player = new MiniMaxPlayer("black", 4, 0, board);
        Set<String> move = new HashSet<String >();

        int walls_count = 0;

        while (true){
           // String action = white_player.get_best_action(black_player);
            String action = white_player.minimax(black_player,0,true,0,0);
            white_player.play(action, false);
            board.print_map();
            System.out.println(
                    "white: " + action + ", evaluation: " + white_player.evaluate(black_player) +
                            ", left walls: " + white_player.walls_count
            );


            if (white_player.is_winner()){
                System.out.println("White player just won with " + white_player.moves_count + " moves!");
                break;
            }
            if (action.split("#")[0].equals("wall")) walls_count += 1;

            TimeUnit.SECONDS.sleep(3);

            //action = black_player.get_best_action(white_player);
            action = white_player.minimax(white_player,0,true,0,0);

            black_player.play(action, false);
            board.print_map();
            System.out.println(
                    "black: " + action + ", evaluation: " + black_player.evaluate(white_player) +
                            ", left walls: " + black_player.walls_count
            );

            if (black_player.is_winner()){
                System.out.println("Black player just won with " + black_player.moves_count + " moves!");
                break;
            }

            if (action.split("#")[0].equals("wall")) walls_count += 1;

            TimeUnit.SECONDS.sleep(3);
        }
    }
}