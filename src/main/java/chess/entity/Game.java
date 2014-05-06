package chess.entity;

import chess.DAO.PlayerDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game{
    private final UUID id;
    private List<Player> players;
    private Player whitePlayer;
    private Player blackPlayer;
    private Piece[][] pieces = new Piece[8][8];
    private Player currentPlayer;
    private Boolean update;
    private Boolean gameOver;
    private List<Message> chat;

    public Game(){
        id=UUID.randomUUID();
        players=new ArrayList<Player>();
        chat=new ArrayList<Message>();
        initializeBoard();
        update=false;
        gameOver=false;
    }
    public void startGame()
    {
        currentPlayer = whitePlayer;
        currentPlayer.getTimer().start();
    }
    public Boolean turn(Player player, Case from , Case to)
    {
        if(player.equals(currentPlayer) && isValidMove(from, to) && !gameOver)
        {
            pieces[to.getY()][to.getX()]=pieces[from.getY()][from.getX()];
            pieces[from.getY()][from.getX()]=null;
            currentPlayer.getTimer().pause();
            currentPlayer = (currentPlayer.getColor().equals(whitePlayer.getColor())) ? blackPlayer : whitePlayer;
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
            if(player.getColor().equals("white"))
            {
                whitePlayer=player;
            }
            else{
                blackPlayer=player;
            }
            players.add(player);
            if(players.size()==2)
            {
                startGame();
            }
        }
    }
    public void checkTimers(){
        if(whitePlayer.getTimer().getTotal()==0)
        {
            gameOver(blackPlayer, whitePlayer);
        }
        else if(blackPlayer.getTimer().getTotal()==0)
        {
            gameOver(whitePlayer, blackPlayer);
        }
    }
    public UUID getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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

    public List<Message> getChat() {
        return chat;
    }

    public void setChat(List<Message> chat) {
        this.chat = chat;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean isGameOver()
    {
        Boolean whiteKnight;
        Boolean blackKnight;
        for(int i=0; i<=7;i++)
        {
            for(int j=0; j<=7;j++)
            {
                if(pieces[i][j]!=null && pieces[i][j].getName().equals("knight"))
                {
                    if(pieces[i][j].getColor().equals("white"))
                    {
                        whiteKnight=true;
                    }
                    else{
                        blackKnight=true;
                    }
                }
            }
        }
        return false;
    }
    private void gameOver(Player winner, Player looser)
    {
        Integer winnerWinCount = winner.getWin()+1;
        Double winnerRating = winner.getRating()+1;
        Integer looserRating = looser.getLoose()+1;
        winner.setWin(winnerWinCount);
        winner.setRating(winnerRating);
        looser.setLoose(looserRating);
        PlayerDAO.update(winner);
        PlayerDAO.update(looser);
    }
    private void withdraw()
    {

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
