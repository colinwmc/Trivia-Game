package TriviaGame2.Dao;

import TriviaGame2.Models.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPlayerDao implements PlayerDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Player getPlayer(String player_name) {
        Player player = null;
        String sql = "SELECT * " +
                "FROM leaderboard " +
                "WHERE player_name=?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, player_name);
        if (result.next()) {
            player = mapRowToPlayer(result);
        }
        return player;
    }

    @Override
    public Player createPlayer(Player player) {
        String sql = "INSERT INTO leaderboard (player_name, wins, correct_answers) " +
                "VALUES (?,?,?)";
        jdbcTemplate.update(sql,player.getName(), player.getWins(), player.getCorrectAnswers());
        return getPlayer(player.getName());
    }

    @Override
    public void updatePlayer(String player_name,Player player) {
        String sql = "UPDATE leaderboard " +
                "SET wins=?, correct_answers=? " +
                "WHERE player_name=?;";
        jdbcTemplate.update(sql, player.getWins(),player.getCorrectAnswers(),player_name);
    }

    @Override
    public List<Player> getTop10() {
        List<Player> top10 = new ArrayList<>();
        String sql = "SELECT player_name, wins, correct_answers FROM leaderboard ORDER BY wins DESC, correct_answers DESC LIMIT 10;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            top10.add(mapRowToPlayer(results));
        }
        return top10;
    }

    private Player mapRowToPlayer(SqlRowSet rowSet) {
        Player player = new Player();
        player.setName(rowSet.getString("player_name"));
        player.setWins(rowSet.getInt("wins"));
        player.setCorrectAnswers(rowSet.getInt("correct_answers"));
        return player;
    }
}
