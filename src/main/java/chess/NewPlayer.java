package chess;

import chess.DAO.PlayerDAO;
import chess.entity.Game;
import chess.entity.Message;
import chess.entity.Player;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewPlayer extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        String name  = request.getParameter("name");
        if(name!=null){
            Player player = PlayerDAO.insert(name);
            session.setAttribute("player", player);
            response.sendRedirect("index.jsp");
        }
        else{
            response.sendRedirect("newPlayer.jsp");
        }
    }
}
