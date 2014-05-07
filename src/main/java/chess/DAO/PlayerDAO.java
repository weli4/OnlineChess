package chess.DAO;
/*
CREATE TABLE public.player (
  id BIGSERIAL,
  name VARCHAR,
  rating DOUBLE PRECISION DEFAULT 0.0,
  win INTEGER DEFAULT 0,
  loose INTEGER DEFAULT 0,
  withdraw INTEGER DEFAULT 0
)
WITH (oids = false);
*/

import chess.entity.Player;
import java.sql.*;

public class PlayerDAO {
    private static ConnectionManager manager;
    {
        manager=new ConnectionManager();

    }
    public Player insert(String name){
        Player player = findOne(name);
        if(player!=null)
        {
            return player;
        }
        else{
            Connection c = null;
            try{
                c = manager.getConnection();
                PreparedStatement st = c.prepareStatement("insert into player(name,rating,win,loose,withdraw) values(?,0.0,0,0,0)");
                st.setString(1, name);
                st.executeUpdate();
                player = new Player();
                player.setName(name);
                return player;
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                try {
                    if(c != null)
                        c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public  boolean update(Player player)
    {
        Connection c = null;
        try{
            c = manager.getConnection();
            PreparedStatement st = c.prepareStatement("update player set rating = ?, win= ?, loose = ?, withdraw = ? where name = ?");
            st.setDouble(1, player.getRating());
            st.setInt(2, player.getWin());
            st.setInt(3, player.getLoose());
            st.setInt(4, player.getWithdraw());
            st.setString(5, player.getName());
            int result = st.executeUpdate();
            if(result!=0)
            {
                return true;
            }
            else{
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally{
            try {
                if(c != null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public  Player findOne(String name){
        Connection c = null;
        try{
            c = manager.getConnection();
            PreparedStatement st = c.prepareStatement("select * from player where name = ?");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            Player player = null;
            while(rs.next()){
                String pName = rs.getString("name");
                Double pRating = rs.getDouble("rating");
                Integer pWin = rs.getInt("win");
                Integer pLoose = rs.getInt("loose");
                Integer pWithdraw = rs.getInt("withdraw");
                player = new Player();
                player.setName(pName);
                player.setRating(pRating);
                player.setWin(pWin);
                player.setLoose(pLoose);
                player.setWithdraw(pWithdraw);
            }
            c.close();
            return player;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            try {
                if(c != null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
