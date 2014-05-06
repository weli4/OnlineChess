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
        Player player=(Player) session.getAttribute("player");
        String id = request.getParameter("id");
        Game game = manager.joinGame(player, id);
        if(game!=null){
            session.setAttribute("game", game);
            session.setAttribute("player", player);
            response.sendRedirect("game.jsp");
        }
        else{
            response.sendRedirect("index.jsp");
        }

    }
}
