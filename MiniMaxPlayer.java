package com.company;

import java.util.*;
public class MiniMaxPlayer extends Player{

    private double MAX_DEPTH = 2.0;
    private double INFINITY = 9999.0;
    private int MAX = 1000;
    private int MIN = -1000;

    public MiniMaxPlayer(String color, int x, int y, Board board) {
        super(color, x, y, board);
    }

    public String bfs(MiniMaxPlayer opponent){
        System.out.println("bfs");

        double self_distance = 0.0;
        double opponent_distance = 0.0;
        Set<MiniMaxPlayer> players = new HashSet<MiniMaxPlayer>();
        players.add(this);
        players.add(opponent);

        Set<Piece> destination = new HashSet<Piece>();
        for (MiniMaxPlayer player : players) {
            if (player.color.equals("white")) destination = this.board.get_white_goal_pieces();
            else destination = this.board.get_black_goal_pieces();

            Queue<Piece> queue = new LinkedList<Piece>();
            HashMap<Piece, Boolean> visited = new HashMap<Piece, Boolean>();
            HashMap<Piece, Double> distances = new HashMap<Piece, Double>();

            for (int y = 0; y < this.board.ROWS_NUM; y++) {
                for (int x = 0; x < this.board.COLS_NUM; x++) {
                    visited.put(this.board.boardMap[y][x], false);
                    distances.put(this.board.boardMap[y][x], this.INFINITY);
                }
            }

            String player_pos = player.get_position();
            int x = Integer.parseInt(player_pos.split(",")[0]);
            int y = Integer.parseInt(player_pos.split(",")[1]);
            Piece player_piece = this.board.get_piece(x , y);

            queue.add(player_piece);
            visited.put(player_piece, true);
            distances.put(player_piece, 0.0);

            while (queue.size() != 0){
                Piece piece = ((LinkedList<Piece>) queue).removeFirst();

                Set<Piece> piece_temp = new HashSet<Piece>();

                piece_temp = this.board.get_piece_neighbors(piece);
                for (Piece p : piece_temp) {
                    if (!visited.get(p)){
                        double t = distances.get(piece) + 1;
                        distances.put(p, t);
                        visited.put(p, true);
                        queue.add(p);
                    }
                }

                double min_distance = this.INFINITY;

                for (Piece p_key : distances.keySet()) {
                    if (destination.contains(p_key)){
                        if (distances.get(p_key) < min_distance){
                            min_distance = distances.get(p_key);
                        }
                    }
                }

                if (player == this) self_distance = min_distance;
                else opponent_distance = min_distance;
            }
        }

        return self_distance + "," + opponent_distance;
    }

    public double evaluate(MiniMaxPlayer opponent){
        System.out.println("evaluate");

        String distances = this.bfs(opponent);
        double self_distance = Double.parseDouble(distances.split(",")[0]);
        double opponent_distance  = Double.parseDouble(distances.split(",")[1]);

        double total_score = (1.5 * opponent_distance - self_distance) * (
                1 + this.walls_count / 2.0
        );
        System.out.println("**** " + self_distance +" **** "+opponent_distance  );
        return total_score;
    }
    String get_best_action(MiniMaxPlayer opponent){
        // System.out.println("get_best_action");
        double best_action_value = - (this.INFINITY);
        String best_action = "";
        Set<String> legal_move = new HashSet<String>();
        legal_move = this.get_legal_actions(opponent);
        for (String action : legal_move) {
            this.play(action, true);
            if (this.is_winner()){
                this.undo_last_action();
                return action;
            }
            double action_value = this.evaluate(opponent);
            if (action_value > best_action_value){
                best_action_value = action_value;
                best_action = action;
            }
            this.undo_last_action();
        }
        return best_action;
    }


    public int minimax(MiniMaxPlayer opponent,int depth, Boolean maximizingPlayer, int alpha, int beta) {
        // Terminating condition. i.e
        // leaf node is reached

        if (depth == 2)
            return 0;

        Set<String> legal_move = new HashSet<String>();
        legal_move = this.get_legal_actions(opponent);
        int val;
        if (maximizingPlayer){
            int best = this.MIN;

            // Recur for left and
            // right children
            for (String action : legal_move) {
                val = minimax(this, depth + 1,!maximizingPlayer,alpha, beta);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
        else {
            int best = this.MAX;

            // Recur for left and
            // right children
            for (String action : legal_move) {
                val = minimax(this,depth + 1,!maximizingPlayer, alpha, beta);
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
    }

}
