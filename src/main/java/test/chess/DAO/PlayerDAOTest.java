package test.chess.DAO; 

import chess.DAO.PlayerDAO;
import chess.entity.Player;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* PlayerDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>��� 5, 2014</pre> 
* @version 1.0 
*/ 
public class PlayerDAOTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findOne(String name) 
* 
*/ 
@Test
public void testFindOne() throws Exception {
    Player  player = new Player();
    player.setName("test");
    player.setRating(1.4);
    player.setWin(2);
    player.setLoose(3);
    player.setWithdraw(4);
    PlayerDAO dao=new PlayerDAO();
    dao.update(player);
} 


} 
