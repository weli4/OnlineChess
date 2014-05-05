package chess;


import chess.entity.Game;

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
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        Game game = manager.joinGame(name, id);
        if(game!=null){
            session.setAttribute("game", game);
            session.setAttribute("player", game.getSecondPlayer());
            response.sendRedirect("game.jsp");
        }
        else{
            response.sendRedirect("index.jsp");
        }

    }
}
