package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardUI = boardUI;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        int rows = 5;
        for(int i = 0; i < pieces[i].length; i++){
            if(pieces[col][i] == Piece.EMPTY){
                rows--;
            }
        }
        if(rows == 5){
            rows = -1;
        }
        return rows;
    }

    private int findNextAvailableSpot1(int rows1) {
        int columns1 = 6;
        for(int i = 0; i < pieces.length; i++){
            if(pieces[i][rows1] == Piece.EMPTY){
                columns1--;
            }
        }
        if(columns1 == 6){
            columns1 = -1;
        }
        return columns1;

    }

    @Override
    public boolean isLegalMove(int col) {
        int row = findNextAvailableSpot(col);

        if (row == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existLegalMove() {
        boolean isExists = false;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    isExists = true;
                    break;
                }
            }
        }
        return isExists;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int row = findNextAvailableSpot(col);

        pieces[col][row] = move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {

        Piece winnerPieces = Piece.EMPTY;

        int col1 = 0;
        int col2 = 0;
        int row1 = 0;
        int row2 = 0;

        for(int i = 0; i < pieces.length; i++){
            if(findNextAvailableSpot(i) == 4 || findNextAvailableSpot(i) == -1){
                if(pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3]){
                    winnerPieces = pieces[i][0];
                    col1 = i;
                    col2 = i;
                    row1 = 0;
                    row2 = 3;

                } else if ( pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3] && pieces[i][3] == pieces[i][4]) {
                    winnerPieces = pieces[i][1];
                    col1 = i;
                    col2 = i;
                    row1 = 1;
                    row2 = 4;

                }
            }
        }

        for(int j = 0; j < pieces[j].length; j++){
            if(findNextAvailableSpot1(j) == 4 || findNextAvailableSpot1(j) == 5 || findNextAvailableSpot1(j) == -1){
                if(pieces[0][j] == pieces[1][j] && pieces[1][j] == pieces[2][j] && pieces[2][j] == pieces[3][j]){
                    winnerPieces = pieces[0][j];
                    col1 = 0;
                    col2 = 3;
                    row1 = j;
                    row2 = j;
                } else if (pieces[1][j] == pieces[2][j] && pieces[2][j] == pieces[3][j] && pieces[3][j] == pieces[4][j]) {
                    winnerPieces = pieces[1][j];
                    col1 = 1;
                    col2 = 4;
                    row1 = j;
                    row2 = j;
                } else if (pieces[2][j] == pieces[3][j] && pieces[3][j] == pieces[4][j] && pieces[4][j] == pieces[5][j]) {
                    winnerPieces = pieces[2][j];
                    col1 = 2;
                    col2 = 5;
                    row1 = j;
                    row2 = j;
                }
            }
        }

        Winner winner;

        if(winnerPieces == Piece.EMPTY){
            winner = new Winner(winnerPieces);
        }else {
            winner = new Winner(winnerPieces , col1 , row1 , col2 , row2);
        }

        return winner;
    }
}
