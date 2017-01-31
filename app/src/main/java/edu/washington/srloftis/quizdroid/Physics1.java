package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;

public class Physics1 extends Activity {

    Button submit;
    RadioGroup answers;
    int correct;
    int total;
    public final static String EXTRA_MESSAGE = "edu.washington.srloftis.quizdroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics1);

        submit = (Button) findViewById(R.id.btnSubmit);
        answers = (RadioGroup) findViewById(R.id.radiogroup);

        correct = 0;
        total = 1;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = answers.getCheckedRadioButtonId();
                if (answer != -1) {
                    List<String> message = new ArrayList<String>();
                    Button radioButton = (Button) answers.findViewById(answer);

                    String youAnswered = (String) radioButton.getText();
                    if (youAnswered.equals("Nailed it!!"))
                        correct += 1;

                    message.add(youAnswered);
                    message.add(correct + "");
                    message.add(total + "");

                    Intent intent = new Intent(Physics1.this, Physics2.class);
                    intent.putStringArrayListExtra(EXTRA_MESSAGE, (ArrayList) message);
                    startActivity(intent);
                }
            }
        });

    }
}
