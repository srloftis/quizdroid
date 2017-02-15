package edu.washington.srloftis.quizdroid;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopicOverview.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopicOverview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicOverview extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TOPIC = "topic";

    Button begin;
    TextView title;
    TextView descr;
    private Fragment displayedFragment = null;

    // TODO: Rename and change types of parameters
    private int mTopic;


    private OnFragmentInteractionListener mListener;

    public TopicOverview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param topic Parameter 1.
     * @return A new instance of fragment TopicOverview.
     */
    // TODO: Rename and change types and number of parameters
    public static TopicOverview newInstance(int topic) {
        TopicOverview fragment = new TopicOverview();
        Bundle args = new Bundle();
        args.putInt(ARG_TOPIC, topic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTopic = getArguments().getInt(ARG_TOPIC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_overview, container, false);
        title = (TextView) view.findViewById(R.id.topicTitle);
        descr = (TextView) view.findViewById(R.id.topicDescr);

        QuizApp app = (QuizApp)this.getActivity().getApplication();
        final ArrayList<Topic> topics = (ArrayList)app.getRepo().getTopics();

        title.setText(topics.get(mTopic).title);
        descr.setText(topics.get(mTopic).longDescr);
        begin = (Button) view.findViewById(R.id.btnBegin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayedFragment = QuestionFrag.newInstance(0, 0, mTopic);

                FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.fragment_placeholder, displayedFragment);
                tx.commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       // if (context instanceof OnFragmentInteractionListener) {
       //     mListener = (OnFragmentInteractionListener) context;
      //  } else {
       //     throw new RuntimeException(context.toString()
       //             + " must implement OnFragmentInteractionListener");
       // }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
