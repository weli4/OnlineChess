package chess;

import chess.entity.Case;
import chess.entity.Piece;
import chess.entity.PieceType;
import chess.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Player firstPlayer;
    private Player secondPlayer;
    private Piece[][] pieces = new Piece[8][8];
    private Player currentPlayer;
    private Boolean turnResult;
    public Game(){
        players=new ArrayList<Player>();
        initiliazeBoard();
    }
    private void initiliazeBoard()
    {
        pieces[0][0] = new Piece("rook", "black");
        pieces[0][1] = new Piece("knight", "black");
        pieces[0][2] = new Piece("bishop", "black");
        pieces[0][3] = new Piece("queen", "black");
        pieces[0][4] = new Piece("king", "black");
        pieces[0][5] = new Piece("bishop", "black");
        pieces[0][6] = new Piece("knight", "black");
        pieces[0][7] = new Piece("rook", "black");

        pieces[1][0] = new Piece("pawn", "black");
        pieces[1][1] = new Piece("pawn", "black");
        pieces[1][2] = new Piece("pawn", "black");
        pieces[1][3] = new Piece("pawn", "black");
        pieces[1][4] = new Piece("pawn", "black");
        pieces[1][5] = new Piece("pawn", "black");
        pieces[1][6] = new Piece("pawn", "black");
        pieces[1][7] = new Piece("pawn", "black");

        pieces[6][0] = new Piece("pawn", "white");
        pieces[6][1] = new Piece("pawn", "white");
        pieces[6][2] = new Piece("pawn", "white");
        pieces[6][3] = new Piece("pawn", "white");
        pieces[6][4] = new Piece("pawn", "white");
        pieces[6][5] = new Piece("pawn", "white");
        pieces[6][6] = new Piece("pawn", "white");
        pieces[6][7] = new Piece("pawn", "white");

        pieces[7][0] = new Piece("rook", "white");
        pieces[7][1] = new Piece("knight", "white");
        pieces[7][2] = new Piece("bishop", "white");
        pieces[7][3] = new Piece("queen", "white");
        pieces[7][4] = new Piece("king", "white");
        pieces[7][5] = new Piece("bishop", "white");
        pieces[7][6] = new Piece("knight", "white");
        pieces[7][7] = new Piece("rook", "white");
    }
    public void startGame()
    {
        firstPlayer=players.get(1);
        secondPlayer=players.get(2);
        currentPlayer = (firstPlayer.getColor().equals("white")) ? firstPlayer : secondPlayer;
    }
    public boolean turn(Case from , Case to)
    {
        if(currentPlayer.getColor().equals(pieces[from.getX()][from.getY()].getColor()))
        {
            pieces[to.getX()][to.getY()]=pieces[from.getX()][from.getY()];
            pieces[from.getX()][from.getY()]=null;
            return true;
        }
        else
            return false;
    }
    public void joinPlayer(Player player)
    {
        if(players.size()!=2)
        {
            players.add(player);
        }
        else{
            startGame();
        }
    }
    private boolean isValidMove(Case from, Case to){
        Piece p=pieces[from.getX()][from.getY()];
        switch(PieceType.valueOf(p.getName())){
            case PAWN:
                int startY = (p.getColor().equals("white")) ? 1 : 6;
                if(true){

                }
                else{

                };

            case ROOK: ;

            case KNIGHT: ;

            case BISHOP: ;

            case QUEEN: ;

            case KING: ;
        }
        return true;
    }
}
