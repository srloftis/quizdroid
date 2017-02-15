package edu.washington.srloftis.quizdroid;
import java.util.*;

/**
 * Created by Sarah on 2/13/2017.
 */

public class TopicRepository {
    private List<Topic> topics;
    private TopicRepository(){
        topics = new ArrayList<Topic>();
        topics.add(new Topic("Math"));
        topics.add(new Topic("Physics"));
        topics.add(new Topic("Marvel Super Heroes"));
    }

    private static TopicRepository instance = new TopicRepository();
    public static TopicRepository getInstance(){
        return instance;
    }
    public List<Topic> getTopics(){
        return topics;
    }

}
