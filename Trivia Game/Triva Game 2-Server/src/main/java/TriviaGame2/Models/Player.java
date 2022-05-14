package TriviaGame2.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    @JsonProperty("player_Name")
    private String name;
    @JsonProperty("wins")
    private int wins;
    @JsonProperty("correct_Answers")
    private int correctAnswers;

    public Player() {
    }

    public Player(String name){
        this.name=name;
        this.wins=0;
        this.correctAnswers=0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
