package edu.washington.srloftis.quizdroid;
import java.util.*;

/**
 * Created by Sarah on 2/13/2017.
 */

public class Question {
    public String question;
    public List<String> answers;
    public int correct;

    public Question(){
        question = "Question?";
        answers = new ArrayList<String>();
        answers.add("Answer 1");
        answers.add("Answer 2");
        answers.add("Answer 3");
        answers.add("Answer 4");
        correct = 3;
    }
}
