package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MH2 extends Activity {

    TextView yourAnswer;
    TextView correctAnswer;
    TextView progress;
    Button next;
    int correct;
    int total;

    public final static String EXTRA_MESSAGE = "edu.washington.srloftis.quizdroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh2);

        yourAnswer = (TextView) findViewById(R.id.yourAnswer);
        correctAnswer = (TextView) findViewById(R.id.correctAnswer);
        progress = (TextView) findViewById(R.id.progress);
        next = (Button) findViewById(R.id.btnNext);

        Intent intent = getIntent();
        ArrayList<String> message = intent.getStringArrayListExtra(MH1.EXTRA_MESSAGE);
        yourAnswer.setText("You answered: " + message.get(0));
        correctAnswer.setText("Correct answer: Nailed it!!");

        correct = Integer.parseInt(message.get(1));
        total = Integer.parseInt(message.get(2));

        progress.setText("You have " + correct + " out of " + total + " correct");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> message = new ArrayList<String>();
                message.add(correct + "");
                message.add((total + 1) + ""); //questions so far
                Intent intent = new Intent(MH2.this, MH3.class);
                intent.putStringArrayListExtra(EXTRA_MESSAGE, (ArrayList) message);
                startActivity(intent);
            }
        });

    }
}
