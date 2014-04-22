package chess;

import chess.entity.Game;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private Map<String,Game> gameMap;
    public GameManager()
    {
        gameMap=new HashMap<String, Game>();
    }
    public Game createGame(){
        Game game=new Game();
        gameMap.put(game.getId().toString(), game);
        return game;
    }
    public Map<String, Game> getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map<String, Game> gameMap) {
        this.gameMap = gameMap;
    }

}
