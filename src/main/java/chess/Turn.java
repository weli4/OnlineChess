package chess;


import chess.entity.Case;
import chess.entity.Game;
import chess.entity.Player;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Turn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        Game game=(Game)session.getAttribute("game");
        Player player=(Player) session.getAttribute("player");
        String from = request.getParameter("from");
        Case caseFrom=new Case(from.charAt(0),from.charAt(1));
        String to = request.getParameter("to");
        Case caseTo=new Case(to.charAt(0),to.charAt(1));
        if(game.turn(player,caseFrom,caseTo)){
            session.setAttribute("player", player);
            session.setAttribute("game", game);
            response.sendRedirect("game.jsp");
        }
        else {
            response.sendRedirect("game.jsp");
        }
    }
}
