package chess;


import chess.entity.Game;
import chess.entity.Player;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Update extends HttpServlet {
    //this servlet calling every 1sec to check if there is new turns from player, timer changed or game is over
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        Player player=(Player) session.getAttribute("player");
        Game game=(Game)session.getAttribute("game");
        session.setAttribute("game", game);
        session.setAttribute("player", player);
        request.getRequestDispatcher("game.jsp").forward(request, response);
        // if game has field update=true, pieces.js repaint the board. after that we must set update to false, because we haven't need to repaint the board every seconds. Thats why we use here .forward, not .redirect
        // after calling .forward, browser get the updated jsp and we can change update to false; With .redirect we cant do this, after calling .redirect, our java code continue working and we get jsp with update=false;
        if(game.getPlayers().size()==2 && game.getCurrentPlayer().equals(player) && game.getUpdate()==true){
            game.setUpdate(false);
        }
        if(game.getGameOver())
        {
            GameManager manager=(GameManager)getServletContext().getAttribute("gameManager");
            manager.getGameMap().remove(game.getId()); //remove game from game manager.
        }
    }
}
