package Utilities;

import Objects.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionStocker {
    public List<Question> geopraphyQuestionList=new ArrayList<>();
    public List<Question>historyQuestionList=new ArrayList<>();
    public List<Question> stockQuestion(String category){
        File questionFile=new File("QuestionFiles/"+category+".txt");
        try {
            Scanner fileReader=new Scanner(questionFile);
            while (fileReader.hasNextLine()){
                String line=fileReader.nextLine();
                String []questionArray=line.split("\\|");
                Question temp=new Question(questionArray[0],questionArray[1],questionArray[2],questionArray[3],questionArray[4]);
                if(category.equals("geography")) {
                    geopraphyQuestionList.add(temp);
                }
                if(category.equals("history")){
                    historyQuestionList.add(temp);
                }
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        if(category.equals("history")){
            return historyQuestionList;
        }
        else if(category.equals("geography")){
            return geopraphyQuestionList;
        }
        else{
            return null;
        }
    }
}
