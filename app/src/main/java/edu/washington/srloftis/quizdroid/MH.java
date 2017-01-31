package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MH extends Activity {

    Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh);

        begin = (Button) findViewById(R.id.btnBegin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MH.this, MH1.class);
                startActivity(intent);
            }
        });
    }
}
