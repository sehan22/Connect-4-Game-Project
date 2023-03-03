package lk.ijse.dep.service;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AiPlayer extends Player {
    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {

        //Random Numbers generate call

        /*do{
            col=(int)((Math.random() * (6-0))+0);
        }while (!board.isLegalMove(col));

        board.updateMove(col,Piece.GREEN);
        board.getBoardUI().update(col,false);

        Winner winner = board.findWinner();

        if(board.findWinner().getWinningPiece()==Piece.GREEN){
            board.getBoardUI().notifyWinner(winner);
        } else if (!board.existLegalMoves()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }*/

        col = bestMove();
        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);
        Winner winner = board.findWinner();

        if (winner.getWinningPiece() == Piece.GREEN) {
            board.getBoardUI().notifyWinner(winner);
        } else if (board.existLegalMove() == false) {
            board.getBoardUI().notifyWinner(winner);
        }

    }

    private int miniMax(int depth, boolean isMaximizingPlayer){

        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {
            return -1;
        } else if (board.existLegalMove() && depth != 2) {
            int i;
            int row;
            int heuristicVal;
            if (!isMaximizingPlayer) {
                for (i = 0; i < 6; ++i) {
                    if (board.isLegalMove(i)) {
                        row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.BLUE);
                        heuristicVal = this.miniMax(depth + 1, true);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return heuristicVal;
                        }
                    }
                }
            } else {
                for (i = 0; i < 6; ++i) {
                    if (board.isLegalMove(i)) {
                        row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.GREEN);
                        heuristicVal = miniMax(depth + 1, false);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return heuristicVal;
                        }
                    }
                }
            }
            return 0;
        } else {
            return 0;
        }

    }


    private int bestMove() {
        boolean isUserWinning = false;
        int tiedColumn = 0;

        int i;
        for (i = 0; i < 6; ++i) {
            if (board.isLegalMove(i)) {
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i, Piece.GREEN);
                int heuristicVal = miniMax(0, false);
                board.updateMove(i, row, Piece.EMPTY);
                if (heuristicVal == 1) {
                    return i;
                }

                if (heuristicVal == -1) {
                    isUserWinning = true;
                } else {
                    tiedColumn = i;
                }
            }
        }

        if (isUserWinning && board.isLegalMove(tiedColumn)) {
            return tiedColumn;

        } else {
            do {
                i = (int) ((Math.random() * (6 - 0)) + 0);
            } while (!board.isLegalMove(i));

            return i;
        }
    }

}
