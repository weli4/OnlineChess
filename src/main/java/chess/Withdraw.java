package chess;

import chess.entity.Game;
import chess.entity.Message;
import chess.entity.Player;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Withdraw extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        Game game=(Game)session.getAttribute("game");
        Player player=(Player) session.getAttribute("player");
        if(!game.getGameOver() && game.getPlayers().size() == 2) {
            game.withdraw();
            GameManager manager=(GameManager)getServletContext().getAttribute("gameManager");
            manager.getGameMap().remove(game.getId());
        }
        response.sendRedirect("index.jsp");
    }
}
