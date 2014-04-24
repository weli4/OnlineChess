package chess.entity;

import chess.entity.Case;
import chess.entity.Piece;
import chess.entity.PieceType;
import chess.entity.Player;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game{
    private UUID id;
    private List<Player> players;
    private Player firstPlayer;
    private Player secondPlayer;
    private Piece[][] pieces = new Piece[8][8];
    private Player currentPlayer;
    private Boolean turnResult;
    public Game(){
        id=UUID.randomUUID();
        players=new ArrayList<Player>();
        initializeBoard();
    }
    private void initializeBoard()
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Boolean getTurnResult() {
        return turnResult;
    }

    public void setTurnResult(Boolean turnResult) {
        this.turnResult = turnResult;
    }

    private boolean isValidMove(Case from, Case to){
        Piece p=pieces[from.getX()][from.getY()];
        Piece t=pieces[to.getX()][to.getY()];
        int d =  (p.getColor().equals("white")) ? 1 : -1;
        int pX = from.getX();
        int pY = from.getY();
        int tX = to.getX();
        int tY = to.getY();
        if(p==null || tX>7 || tX<0 || tY>7 || tX<0)
            return false;
        switch(PieceType.valueOf(p.getName())){
            case PAWN:
                int startY = (p.getColor().equals("white")) ? 1 : 6;
                if(tY==startY+2*d && pX==tX && t==null && pieces[pX][startY+1*d]==null)
                {
                    return true;
                }
                else if(tY==pY+1*d && tX==pX && t==null){
                    return true;
                }
                else if(tY==pY+1*d && (tX==pX+1 || tX==pX-1) && t!=null)
                {
                    return true;
                }
                else{
                    return false;
                }
            case ROOK: //Ладья
                if(tX==pX && tY!=pY || tX!=pX && tY==pY)
                {
                    if(t.getColor().equals(p.getColor())) {
                        return false;
                    }
                    else if(tX==pX)
                    {
                        int dY = (tY>pY) ? 1 : -1;
                        for(int y=pY; y<pY; y=y+dY)
                        {
                            if(pieces[tX][y]!=null){
                                return false;
                            }
                        }
                        return true;
                    }
                    else if(tY==pY)
                    {
                        int dX = (tX>pX) ? 1 : -1;
                        for(int x=pX; x<tX; x=x+dX)
                        {
                            if(pieces[x][tY]!=null){
                                return false;
                            }
                        }
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            case KNIGHT:
                if(!t.getColor().equals(p.getColor()))
                {
                    if((tY==pY-2 || tY==pY+2) && (tX==pX+1 || tX==pX-1))
                    {
                        return true;
                    }
                    else if((tY==pY-1 || tY==pY+1) && (tX==pX+2 || tX==pX-2)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            case BISHOP: ;
                if(tX==pX || tY==pY)
                {
                    return false;
                }
                else if(t.getColor().equals(p.getColor()))
                {
                    return false;
                }
                else
                {
                    int dX = (tX>pX) ? 1 : -1;
                    int dY = (tY>pY) ? 1 : -1;
                    for(int x=pX; x<tX; x=x+dX)
                    {
                        for(int y=pY; y<tY; y=y+dY)
                        {
                            if(pieces[x][y]!=null)
                            {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            case QUEEN: ;
                if(tX!=pX || tY!=pY){
                    return true;
                }
                else if(tX==pX && tY!=pY || tX!=pX && tY==pY)
                {
                    return true;
                }
                else{
                    return false;
                }
            case KING:
                if((tY==pY+1 || tY==pY-1) && (tX==pX+1 || tX==pX-1) && !t.getColor().equals(p.getColor())){
                    return true;
                }
                else{
                    return false;
                }
        }
        return false;
    }

}
