package chess;

import chess.entity.Game;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private static Map<String,Game> gameMap;
    public GameManager()
    {
        if(gameMap==null)
        gameMap=new HashMap<String, Game>();
    }
    public Game createGame(){
        Game game=new Game();
        gameMap.put(game.getId().toString(), game);
        return game;
    }
    public Game joinGame(String name, String id)
    {
        Game game = gameMap.get(id);
        if(game!=null){
            game.joinPlayer(name);
            return game;
        }
        else{
            return null;
        }
    }
    public Map<String, Game> getGameMap() {
        return gameMap;
    }
    public void setGameMap(Map<String, Game> gameMap) {
        this.gameMap = gameMap;
    }

}
