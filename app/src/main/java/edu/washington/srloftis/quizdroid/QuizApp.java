package edu.washington.srloftis.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by Sarah on 2/13/2017.
 */

public class QuizApp extends Application {
    public static final String tag = "QuizApp";

    public QuizApp(){ }

    private static QuizApp instance = new QuizApp();
    public static QuizApp getInstance(){
        return instance;
    }

    public TopicRepository getRepo(){
        return TopicRepository.getInstance();
    }

    @Override
    public void onCreate(){
        Log.d(tag, "Loaded and running!");
    }

}
