package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class Physics4 extends Activity{

    TextView yourAnswer;
    TextView correctAnswer;
    TextView progress;
    Button finish;
    int correct;
    int total;
    public final static String EXTRA_MESSAGE = "edu.washington.srloftis.quizdroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics4);

        yourAnswer = (TextView) findViewById(R.id.yourAnswer);
        correctAnswer = (TextView) findViewById(R.id.correctAnswer);
        progress = (TextView) findViewById(R.id.progress);
        finish = (Button) findViewById(R.id.btnFinish);

        Intent intent = getIntent();
        ArrayList<String> message = intent.getStringArrayListExtra(Physics3.EXTRA_MESSAGE);
        correct = Integer.parseInt(message.get(1));
        total = Integer.parseInt(message.get(2));
        yourAnswer.setText("You answered: " + message.get(0));
        correctAnswer.setText("Correct answer: Nailed it!!");
        if (message.get(0).equals("Nailed it!!"))
            correct += 1;
        progress.setText("You have " + correct + " out of " + total + " correct");

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Physics4.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
