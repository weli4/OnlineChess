package chess;


import chess.entity.Game;
import chess.entity.Player;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JoinGame extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session == null) {
            return;
        }

        GameManager manager=(GameManager)getServletContext().getAttribute("gameManager");
        Player player=(Player) session.getAttribute("player"); // getting the player entity from session
        String id = request.getParameter("id"); //id from the web-form
        Game game = manager.getGameMap().get(id);  //getting the game from game manager, if null-redirect to index.jsp
        if(game!=null){
            if(game.getPlayers().get(0).getColor().equals("white")) //changing the player's color
            {
                player.setColor("black");
            }
            else{
                player.setColor("white");
            }
            game.joinPlayer(player);  //adding the player to game;
            session.setAttribute("game", game);
            session.setAttribute("player", player);
            response.sendRedirect("game.jsp");
        }
        else{
            response.sendRedirect("index.jsp");
        }

    }
}
