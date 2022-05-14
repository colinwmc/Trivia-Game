package TriviaGame2.Dao;



import TriviaGame2.Models.Player;

import java.util.List;

public interface PlayerDao {
    public Player getPlayer(String player_name);

    public Player createPlayer(Player player);

    public void updatePlayer(String player_name,Player player);

    public List<Player>getTop10();
}
