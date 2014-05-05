package chess.entity;

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
    private Boolean update;

    public Game(){
        id=UUID.randomUUID();
        players=new ArrayList<Player>();
        initializeBoard();
        update=false;
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
        firstPlayer=players.get(0);
        secondPlayer=players.get(1);
        currentPlayer = (firstPlayer.getColor().equals("white")) ? firstPlayer : secondPlayer;
        currentPlayer.getTimer().start();
    }
    public Boolean turn(Player player, Case from , Case to)
    {
        if(player.equals(currentPlayer) && isValidMove(from, to))
        {
            pieces[to.getY()][to.getX()]=pieces[from.getY()][from.getX()];
            pieces[from.getY()][from.getX()]=null;
            currentPlayer.getTimer().pause();
            currentPlayer = (currentPlayer.getColor().equals(secondPlayer.getColor())) ? firstPlayer : secondPlayer;
            update=true;
            currentPlayer.getTimer().go();
            return true;
        }
        else{
            return false;
        }
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
    public void joinPlayer(String name)
    {
        if(players.size()!=2)
        {
            Player newPlayer=new Player();
            newPlayer.setName(name);
            String newPlayerColor = (players.get(0).getColor().equals("white")) ? "black" : "white";
            newPlayer.setColor(newPlayerColor);
            players.add(newPlayer);
            startGame();
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

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    private boolean isValidMove(Case from, Case to){
        Piece p=pieces[from.getY()][from.getX()];
        Piece t=pieces[to.getY()][to.getX()];
        int d =  (p.getColor().equals("white")) ? -1 : 1;
        int pX = from.getX();
        int pY = from.getY();
        int tX = to.getX();
        int tY = to.getY();

        if(p==null || tX>7 || tX<0 || tY>7 || tX<0)
            return false;
        switch(PieceType.valueOf(p.getName().toUpperCase())){
            case PAWN:
                int startY = (p.getColor().equals("white")) ? 6 : 1;
                if(tY==startY+2*d && pX==tX && t==null && pieces[startY+1*d][pX]==null)
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
            case ROOK:
                if(tX==pX && tY!=pY || tX!=pX && tY==pY)
                {
                    if(t!=null && t.getColor().equals(p.getColor())) {
                        return false;
                    }
                    else if(tX==pX)
                    {
                        int dY = (tY>pY) ? 1 : -1;
                        for(int y=pY; y!=tY; y=y+dY)
                        {
                            if(pieces[y][tX]!=null){
                                return false;
                            }
                        }
                        return true;
                    }
                    else if(tY==pY)
                    {
                        int dX = (tX>pX) ? 1 : -1;
                        for(int x=pX; x!=tX; x=x+dX)
                        {
                            if(pieces[tY][x]!=null){
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
                if(t!=null &&  t.getColor().equals(p.getColor()))
                {
                    return false;
                }
                else
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
            case BISHOP: ;
                if(tX==pX || tY==pY)
                {
                    return false;
                }
                else if(t!=null && t.getColor().equals(p.getColor()))
                {
                    return false;
                }
                else
                {
                    int dX = (tX>pX) ? 1 : -1;
                    int dY = (tY>pY) ? 1 : -1;
                    for(int x=pX; x!=tX; x=x+dX)
                    {
                        for(int y=pY; y!=tY; y=y+dY)
                        {
                            if(pieces[y][x]!=null)
                            {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            case QUEEN: ;
                if(t!=null &&  t.getColor().equals(p.getColor())) {
                    return false;
                }
                else if(tX!=pX || tY!=pY){
                    if(tX==pX)
                    {
                        int dY = (tY>pY) ? 1 : -1;
                        for(int y=pY; y!=tY; y=y+dY)
                        {
                            if(pieces[y][tX]!=null){
                                return false;
                            }
                        }
                        return true;
                    }
                    else if(tY==pY)
                    {
                        int dX = (tX>pX) ? 1 : -1;
                        for(int x=pX; x!=tX; x=x+dX)
                        {
                            if(pieces[tY][x]!=null){
                                return false;
                            }
                        }
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(tX==pX && tY!=pY || tX!=pX && tY==pY)
                {
                    int dX = (tX>pX) ? 1 : -1;
                    int dY = (tY>pY) ? 1 : -1;
                    for(int x=pX; x!=tX; x=x+dX)
                    {
                        for(int y=pY; y!=tY; y=y+dY)
                        {
                            if(pieces[y][x]!=null)
                            {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                else{
                    return false;
                }
            case KING:
                if( (tY==pY+1 || tY==pY-1) && (tX==pX+1 || tX==pX-1) && t!=null && !t.getColor().equals(p.getColor())){
                    return true;
                }
                else{
                    return false;
                }
        }
        return false;
    }
}
