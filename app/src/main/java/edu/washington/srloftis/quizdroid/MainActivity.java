package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    /*private String[] topics = new String[]{
            "Math", "Physics", "Marvel Super Heroes"
    };*/

    public final static String EXTRA_MESSAGE = "edu.washington.srloftis.quizdroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);

        QuizApp app = (QuizApp)this.getApplication();
        final ArrayList<Topic> topics = (ArrayList)app.getRepo().getTopics();

        ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                topics);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                intent.putExtra(EXTRA_MESSAGE, position);
                startActivity(intent);
            }
        });
    }
}
