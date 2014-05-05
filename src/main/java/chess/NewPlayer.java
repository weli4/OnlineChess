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

public class NewPlayer extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        Game game=(Game)session.getAttribute("game");
        Player player=(Player) session.getAttribute("player");
        String messageText = request.getParameter("messageText");
        Message message=new Message(player.getName(), messageText);
        game.getChat().add(message);
        session.setAttribute("game", game);
    }
}
