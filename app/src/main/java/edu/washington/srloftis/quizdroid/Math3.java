package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;

public class Math3 extends Activity {

    Button submit;
    RadioGroup answers;
    int correct;
    int total;
    public final static String EXTRA_MESSAGE = "edu.washington.srloftis.quizdroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math3);

        submit = (Button) findViewById(R.id.btnSubmit);
        answers = (RadioGroup) findViewById(R.id.radiogroup);

        Intent intent = getIntent();
        ArrayList<String> message = intent.getStringArrayListExtra(Math2.EXTRA_MESSAGE);
        correct = Integer.parseInt(message.get(0));
        total = Integer.parseInt(message.get(1));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = answers.getCheckedRadioButtonId();
                if (answer != -1) {
                    List<String> message = new ArrayList<String>();
                    Button radioButton = (Button) answers.findViewById(answer);

                    String youAnswered = (String) radioButton.getText();

                    message.add(youAnswered);
                    message.add(correct + "");
                    message.add(total + "");

                    Intent intent = new Intent(Math3.this, Math4.class);
                    intent.putStringArrayListExtra(EXTRA_MESSAGE, (ArrayList) message);
                    startActivity(intent);
                }
            }
        });

    }
}