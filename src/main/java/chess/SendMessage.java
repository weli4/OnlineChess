package chess;


import chess.entity.Case;
import chess.entity.Game;
import chess.entity.Message;
import chess.entity.Player;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendMessage extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        Game game=(Game)session.getAttribute("game");
        Player player=(Player) session.getAttribute("player");
        String messageText = request.getParameter("messageText"); //getting message text from request
        Message message=new Message(player.getName(), messageText);  //creating new message
        game.getChat().add(message); //adding message to game chat
        session.setAttribute("game", game);
    }
}
