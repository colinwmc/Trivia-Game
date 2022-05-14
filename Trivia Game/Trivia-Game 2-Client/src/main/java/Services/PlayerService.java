package Services;

import Objects.Player;
import Utilities.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class PlayerService {
    private static final String API_BASE_URL = "http://localhost:8080/leaderboard";
    private final RestTemplate restTemplate = new RestTemplate();

    public Player addNewPlayer(Player player) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> entity = new HttpEntity<>(player, headers);

        try {
            Player playerCreated = restTemplate.postForObject(API_BASE_URL, entity, Player.class);
            return playerCreated;
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getMessage());
            Player newPlayer = new Player();
            return newPlayer;
        }

    }

    public Player getPlayer(String player_name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> entity = new HttpEntity<>(headers);
        Player player = new Player();
        try {
            player = restTemplate.getForObject(API_BASE_URL + "/" + player_name, Player.class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getMessage());
        }
        return player;
    }

    public void updatePlayer(Player player){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> entity = new HttpEntity<>(player,headers);
        try{
            restTemplate.put(API_BASE_URL+"/"+player.getName(),entity);
        }catch (RestClientResponseException e) {
            BasicLogger.log(e.getMessage());
        }
    }
    public Player[] getTop10(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> entity = new HttpEntity<>(headers);
        Player[]top10=null;

        try{
            top10=restTemplate.exchange(API_BASE_URL,HttpMethod.GET,entity,Player[].class).getBody();
        } catch (RestClientResponseException e){
            BasicLogger.log(e.getMessage());
        }
        return top10;
    }
}
