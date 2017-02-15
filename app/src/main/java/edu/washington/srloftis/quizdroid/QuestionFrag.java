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
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CORRECT = "correct";
    private static final String ARG_TOTAL = "total";
    private static final String ARG_TOPIC = "topic";

    TextView questionHolder;
    RadioGroup answers;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    Button submit;
    private Fragment displayedFragment = null;

    // TODO: Rename and change types of parameters
    private int mCorrect;
    private int mTotal;
    private int mTopic;

    private OnFragmentInteractionListener mListener;

    public QuestionFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param correct Parameter 1.
     * @param total Parameter 2.
     * @return A new instance of fragment QuestionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFrag newInstance(int correct, int total, int topic) {
        QuestionFrag fragment = new QuestionFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_CORRECT, correct);
        args.putInt(ARG_TOTAL, total);
        args.putInt(ARG_TOPIC, topic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCorrect = getArguments().getInt(ARG_CORRECT);
            mTotal = getArguments().getInt(ARG_TOTAL);
            mTopic = getArguments().getInt(ARG_TOPIC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        QuizApp app = (QuizApp)this.getActivity().getApplication();
        final ArrayList<Topic> topics = (ArrayList)app.getRepo().getTopics();
        Topic topic = topics.get(mTopic);
        final Question question = topic.questions.get(mTotal);

        submit = (Button) view.findViewById(R.id.btnSubmit);
        questionHolder = (TextView) view.findViewById(R.id.questionHolder);
        questionHolder.setText(question.question);
        answers = (RadioGroup) view.findViewById(R.id.radiogroup);
        answer1= (Button) view.findViewById(R.id.radio1);
        answer1.setText(question.answers.get(0));
        answer2= (Button) view.findViewById(R.id.radio2);
        answer2.setText(question.answers.get(1));
        answer3= (Button) view.findViewById(R.id.radio3);
        answer3.setText(question.answers.get(2));
        answer4= (Button) view.findViewById(R.id.radio4);
        answer4.setText(question.answers.get(3));


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = answers.getCheckedRadioButtonId();
                if (checked != -1) {
                    Button answer = (Button) answers.findViewById(checked);
                    String youAnswered = (String) answer.getText();
                    if (youAnswered == question.answers.get(question.correct))
                        mCorrect++;

                    displayedFragment = AnswerFrag.newInstance(mCorrect, mTotal, youAnswered, mTopic);

                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, displayedFragment);
                    tx.commit();
                }
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
      //  if (context instanceof OnFragmentInteractionListener) {
        //    mListener = (OnFragmentInteractionListener) context;
       // } else {
        //    throw new RuntimeException(context.toString()
      //              + " must implement OnFragmentInteractionListener");
        //}
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
