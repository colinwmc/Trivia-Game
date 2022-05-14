package View;

import Objects.Player;
import Services.PlayerService;

public class AddPlayerMenu {
private final PlayerService playerService=new PlayerService();

    public void createPlayer(){
        String inputName=UserInput.displayAddPlayerMenu();
        Player player=new Player(inputName);
        Player playerAdded=playerService.addNewPlayer(player);
        if(playerAdded.getName()==null){
            System.out.println("Player not successfully added. Player name may already be taken. Please try again.");
        }
        else if(playerAdded.getName().equals(inputName)){
            System.out.println("Player successfully added.");
        }

    }
}
