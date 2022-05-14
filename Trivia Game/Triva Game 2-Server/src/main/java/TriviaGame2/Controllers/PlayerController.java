package TriviaGame2.Controllers;

import TriviaGame2.Dao.PlayerDao;
import TriviaGame2.Models.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/leaderboard")
public class PlayerController {

    private PlayerDao playerDao;

    public PlayerController(PlayerDao dao){
        this.playerDao=dao;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Player createPlayer(@RequestBody Player player){
       return playerDao.createPlayer(player);
    }

    @RequestMapping(value = "/{player_name}",method = RequestMethod.GET)
    public Player getPlayer(@PathVariable String player_name){
        return playerDao.getPlayer(player_name);
    }

    @RequestMapping(value = "/{player_name}",method = RequestMethod.PUT)
    public void updatePlayer(@RequestBody Player player,@PathVariable String player_name){
        playerDao.updatePlayer(player_name,player);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Player> getTop10(){
        return playerDao.getTop10();
    }
}
