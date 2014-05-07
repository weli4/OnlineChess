package chess;

import chess.entity.Game;
import chess.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private static Map<String,Game> gameMap;
    public GameManager()
    {
        if(gameMap==null)
        gameMap=new HashMap<String, Game>();
    }
    public Game createGame()     //creating the game and adding it to hashmap
    {
        Game game=new Game();
        gameMap.put(game.getId().toString(), game);
        return game;
    }
    public Game joinGame(Player player, String id)      //join player to game with id;
    {
        Game game = gameMap.get(id);
        if(game!=null){
            game.joinPlayer(player);
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
