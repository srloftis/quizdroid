package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class Math extends Activity {

    Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        begin = (Button) findViewById(R.id.btnBegin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Math.this, Math1.class);
                startActivity(intent);
            }
        });
    }
}
