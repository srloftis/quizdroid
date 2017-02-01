package edu.washington.srloftis.quizdroid;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Answer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Answer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Answer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CORRECT = "correct";
    private static final String ARG_TOTAL = "total";
    private static final String ARG_ANSWER = "answer";

    Button next;
    TextView yourAnswer;
    TextView correctAnswer;
    TextView progress;
    private Fragment displayedFragment = null;

    // TODO: Rename and change types of parameters
    private int mCorrect;
    private int mTotal;
    private String mAnswer;

    private OnFragmentInteractionListener mListener;

    public Answer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param correct Parameter 1.
     * @param total Parameter 2.
     * @param answer Parameter 3.
     * @return A new instance of fragment Answer.
     */
    // TODO: Rename and change types and number of parameters
    public static Answer newInstance(int correct, int total, String answer) {
        Answer fragment = new Answer();
        Bundle args = new Bundle();
        args.putInt(ARG_CORRECT, correct);
        args.putInt(ARG_TOTAL, total);
        args.putString(ARG_ANSWER, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCorrect = getArguments().getInt(ARG_CORRECT);
            mTotal = getArguments().getInt(ARG_TOTAL);
            mAnswer = getArguments().getString(ARG_ANSWER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_answer, container, false);
        yourAnswer = (TextView) view.findViewById(R.id.yourAnswer);
        correctAnswer = (TextView) view.findViewById(R.id.correctAnswer);
        progress = (TextView) view.findViewById(R.id.progress);
        yourAnswer.setText("You answered: " + mAnswer);
        correctAnswer.setText("Correct answer: Nailed it!!");
        progress.setText("You have " + mCorrect + " out of " + mTotal + " correct");
        next = (Button) view.findViewById(R.id.btnNext);
        if  (mTotal == 2) {
            next.setText("Finish");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            next.setText("Next");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayedFragment = Question.newInstance(mCorrect, mTotal);
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, displayedFragment);
                    tx.commit();
                }
            });
        }

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
        //if (context instanceof OnFragmentInteractionListener) {
            //mListener = (OnFragmentInteractionListener) context;
        //} else {
           // throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
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
