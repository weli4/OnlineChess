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
        if(game.getPlayers().size()==2 && game.getCurrentPlayer().equals(player) && game.getUpdate()==true){

            game.setUpdate(false);
        }

    }
}
