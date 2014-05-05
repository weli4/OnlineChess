package chess;

import chess.entity.Game;
import chess.entity.Player;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewGame extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect("index.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name  = request.getParameter("name");
        String color = request.getParameter("color");
        if (name == null || name.isEmpty() || color == null || (!color.equals("white") && !color.equals("black"))) {
            response.sendRedirect("index.html");
            return;
        }
        ServletContext context = getServletContext();
        GameManager manager = (GameManager) context.getAttribute("gameManager");
        if (manager == null) {
            manager = new GameManager();
            context.setAttribute("gameManager", manager);
        }
        HttpSession session = request.getSession(true);
        Game game = manager.createGame();
        Player player = new Player();
        player.setColor(color);
        player.setName(name);
        game.joinPlayer(player);
        session.setAttribute("player", player);
        session.setAttribute("game", game);
        session.setAttribute("gameManager", manager);
        response.sendRedirect("game.jsp");
    }
}
