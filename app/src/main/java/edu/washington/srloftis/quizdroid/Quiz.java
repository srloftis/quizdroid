package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentTransaction;

public class Quiz extends Activity {

    private Fragment displayedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        Intent intent = getIntent();
        int topic = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);

        displayedFragment = TopicOverview.newInstance(topic);

        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, displayedFragment);
        tx.commit();
    }
}
