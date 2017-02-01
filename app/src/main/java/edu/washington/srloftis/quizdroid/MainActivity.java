package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.content.Intent;


public class MainActivity extends Activity {

    private String[] topics = new String[]{
            "Math", "Physics", "Marvel Super Heroes"
    };

    public final static String EXTRA_MESSAGE = "edu.washington.srloftis.quizdroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                topics);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Topic.class);
                if (position == 0)
                    intent.putExtra(EXTRA_MESSAGE, topics[0]);
                if (position == 1)
                    intent.putExtra(EXTRA_MESSAGE, topics[1]);
                if (position == 2)
                    intent.putExtra(EXTRA_MESSAGE, topics[2]);
                startActivity(intent);
            }
        });
    }
}
