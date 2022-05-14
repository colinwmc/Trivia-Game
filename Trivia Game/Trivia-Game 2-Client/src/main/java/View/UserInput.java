package View;

import java.util.Scanner;

public class UserInput {
    private static Scanner userInput = new Scanner(System.in);
    public static String displayMainMenu(){

        System.out.println("************* Trivia Showdown *************");
        System.out.println("1. Start Game!");
        System.out.println("2. Add new player");
        System.out.println("3. View Leaderboard");
        System.out.println("4. Exit");
        System.out.print("\nInput Choice:");
        String mainMenuChoice=userInput.nextLine();

        switch (mainMenuChoice){
            case "1":
                return "start";
            case "2":
                return "addPlayer";
            case "3":
                return "leaderboard";
            case "4":
                return "exit";
            default:
                return "invalid";
        }
    }
    public static int displayGameMenu(){

        System.out.println("Choose game length:");
        System.out.println("6 questions");
        System.out.println("10 questions");
        System.out.println("20 questions");
        System.out.print("Your choice:");
        String gameLengthChoice=userInput.nextLine();

        switch(gameLengthChoice){
            case "6":
                return 6;
            case "10":
                return 10;
            case "20":
                return 20;
            default:
                return 0;
        }
    }
    public static String displayQuestionMenu(){
        System.out.println("1. Geography");
        System.out.println("2. History");
        System.out.println("Your choice:");
        String categoryChoice=userInput.nextLine();

        switch (categoryChoice){
            case "1":
                return "Geography";
            case "2":
                return "History";
            default:
                return "Invalid";
        }
    }

    public static int recieveAnswer(){
        System.out.print("Your answer:");
        String answer=userInput.nextLine();
        switch(answer){
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            default:
                return 0;
        }
    }
    public static String displayAddPlayerMenu(){
        System.out.println("Input your unique player name:");
        String inputName=userInput.nextLine();
        return inputName;
    }
    public static String getPlayerName(String playerNumber){
        System.out.println("Who is Player "+playerNumber+"?");
        System.out.print("Input Player name:");
        String playerName=userInput.nextLine();
        return playerName;
    }
}
