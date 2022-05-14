package View;

import Objects.Player;
import Services.PlayerService;
import Utilities.Colors;

public class MainMenu {


    public static void main(String[] args) {
        AddPlayerMenu addPlayerMenu = new AddPlayerMenu();
        GameMenu gameMenu=new GameMenu();
        PlayerService playerService=new PlayerService();
        boolean keepRunning = true;
        while (keepRunning) {
            String choice = UserInput.displayMainMenu();
            if (choice.equals("invalid")) {
                System.out.println(Colors.Red + "I'm sorry I didn't understand that. Please enter a valid response from list below." + Colors.Reset);
            } else if (choice.equals("start")) {
                gameMenu.run();
            }else if(choice.equals("addPlayer")){
                addPlayerMenu.createPlayer();

            }else if(choice.equals("leaderboard")){
                Player[] top10=playerService.getTop10();
                System.out.println("***************** Leaderboard *****************");
                System.out.println("Player           Wins           Correct Answers");
                for(Player player:top10){
                    System.out.println(player);
                }
            }else if(choice.equals("exit")){
                keepRunning=false;
            }


        }
    }
}
