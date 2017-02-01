package edu.washington.srloftis.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentTransaction;

public class Topic extends Activity {

    private Fragment displayedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        displayedFragment = TopicOverview.newInstance(message);

        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, displayedFragment);
        tx.commit();
    }
}
