package View;

import Objects.Player;
import Objects.Question;
import Services.PlayerService;
import Utilities.Colors;
import Utilities.QuestionStocker;

import java.util.ArrayList;
import java.util.List;

public class GameMenu {


    public void run() {
        PlayerService playerService=new PlayerService();
        QuestionStocker questionStocker = new QuestionStocker();
        List<Question> geographyQuestions = questionStocker.stockQuestion("geography");
        List<Question> historyQuestions = questionStocker.stockQuestion("history");
        String playerOneName=UserInput.getPlayerName("One");
        Player playerOne=playerService.getPlayer(playerOneName);
        String playerTwoName=UserInput.getPlayerName("Two");
        Player playerTwo=playerService.getPlayer(playerTwoName);
        boolean numberAccepted = false;
        int totalNumberOfQuestions = 0;
        while (!numberAccepted) {
            totalNumberOfQuestions = UserInput.displayGameMenu();
            if (totalNumberOfQuestions == 0) {
                System.out.println(Colors.Red + "I'm sorry, I didn't understand that. Please enter a response from the list above." + "\n" + Colors.Reset);
            } else {
                numberAccepted = true;
            }
        }
        int currentQuestionNumber = 1;
        int playerOneScore = 0;
        int playerTwoScore = 0;
        String playerWhoseTurnItIs;
        String playerTyringToSteal;
        List<Question> questionList = new ArrayList<>();
        while (currentQuestionNumber <= totalNumberOfQuestions) {
            if (currentQuestionNumber % 2 != 0) {
                playerWhoseTurnItIs = playerOneName;
                playerTyringToSteal = playerTwoName;

            } else {
                playerWhoseTurnItIs = playerTwoName;
                playerTyringToSteal = playerOneName;
            }

            System.out.println(playerWhoseTurnItIs + "! Choose a category:");
            boolean categoryAccepted = false;
            String currentCategory="";
            while (!categoryAccepted) {
                String choice = UserInput.displayQuestionMenu();
                if (choice.equals("Geography")) {
                    questionList = geographyQuestions;
                    currentCategory=choice;
                } else if (choice.equals("History")) {
                    questionList = historyQuestions;
                    currentCategory=choice;
                }if(choice.equals("Invalid")) {
                    System.out.println(Colors.Red + "Sorry, I didn't understand that. Please choose a category from the list." + Colors.Reset);
                }
                else if (questionList.size() > 0) {
                    categoryAccepted = true;
                } else {
                    System.out.println(Colors.Red + "Sorry, there are no more questions available in this category." + Colors.Reset);
                }

            }
            int rando = getRandomNumber(0, questionList.size() - 1);
            String answerStatus = askQuestion(questionList, rando);
            //make all this a method
            if (answerStatus.equals("Correct")) {
                System.out.println("Correct!");
                if (playerWhoseTurnItIs.equals(playerOneName)) {
                    playerOneScore++;
                } else {
                    playerTwoScore++;
                }
                System.out.println("Current Score:");
                System.out.println(playerOneName+": " + playerOneScore);
                System.out.println(playerTwoName+": " + playerTwoScore);
                questionList.remove(rando);
            } else {
                System.out.println("Incorrect!");
                System.out.println(playerTyringToSteal + "! Here's your chance to steal!");
                answerStatus = askQuestion(questionList, rando);
                if (answerStatus.equals("Correct")) {
                    System.out.println("Correct!");
                    if (playerTyringToSteal.equals(playerOneName)) {
                        playerOneScore++;
                    } else {
                        playerTwoScore++;
                    }

                } else {
                    System.out.println("Incorrect! No points awarded this round.");
                }
                System.out.println("Current Score:");
                System.out.println(playerOneName+": " + playerOneScore);
                System.out.println(playerTwoName+": " + playerTwoScore);
                if(currentCategory.equals("Geography")){
                    geographyQuestions.remove(rando);
                }
                else if(currentCategory.equals("History")){
                    historyQuestions.remove(rando);
                }
            }
            currentQuestionNumber++;
            questionList=null;
        }

        if(playerOneScore>playerTwoScore){
            System.out.println(playerOneName+"Wins!");
            playerOne.setWins(playerOne.getWins()+1);
        }
        else if(playerTwoScore>playerOneScore){
            System.out.println(playerTwoName+"Wins!");
            playerTwo.setWins(playerTwo.getWins()+1);
        }
        else{
            System.out.println("It's A Tie!");
        }
        System.out.println("Final Scores:");
        System.out.println(playerOneName+": "+playerOneScore);
        playerOne.setCorrectAnswers(playerOne.getCorrectAnswers()+playerOneScore);
        System.out.println(playerTwoName+": "+playerTwoScore);
        playerTwo.setCorrectAnswers(playerTwo.getCorrectAnswers()+playerTwoScore);
        playerService.updatePlayer(playerOne);
        playerService.updatePlayer(playerTwo);

    }

    public static String askQuestion(List<Question> questionList,int rando){

        Question questionBeingAsked=questionList.get(rando);
        System.out.println(questionBeingAsked.toString());
        String[]answerChoices=new String[]{questionBeingAsked.getQuestion(),questionBeingAsked.getOption1(),questionBeingAsked.getOption2(),questionBeingAsked.getOption3(),questionBeingAsked.getOption4()};

        boolean answerIsNotValid=true;
        int playersAnswer=0;
        while(answerIsNotValid){
            int answer=UserInput.recieveAnswer();
            playersAnswer=answer;
            if(answer==0){
                System.out.println(Colors.Red+"Your answer must be 1,2,3, 0r 4"+Colors.Reset);
            }
            else{
                answerIsNotValid=false;
            }
        }
        if(answerChoices[playersAnswer].contains("*")){
            return"Correct";
        }
        else{
            return"Incorrect";
        }
    }

    public static int getRandomNumber(int min,int max){
        return(int)((Math.random()*(max-min))+min);
    }
}
