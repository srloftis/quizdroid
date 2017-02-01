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
import android.widget.RadioGroup;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Question.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CORRECT = "correct";
    private static final String ARG_TOTAL = "total";

    Button submit;
    RadioGroup answers;
    private Fragment displayedFragment = null;

    // TODO: Rename and change types of parameters
    private int mCorrect;
    private int mTotal;

    private OnFragmentInteractionListener mListener;

    public Question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param correct Parameter 1.
     * @param total Parameter 2.
     * @return A new instance of fragment Question.
     */
    // TODO: Rename and change types and number of parameters
    public static Question newInstance(int correct, int total) {
        Question fragment = new Question();
        Bundle args = new Bundle();
        args.putInt(ARG_CORRECT, correct);
        args.putInt(ARG_TOTAL, total);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCorrect = getArguments().getInt(ARG_CORRECT);
            mTotal = getArguments().getInt(ARG_TOTAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        submit = (Button) view.findViewById(R.id.btnSubmit);
        answers = (RadioGroup) view.findViewById(R.id.radiogroup);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = answers.getCheckedRadioButtonId();
                if (answer != -1) {
                    mTotal++;
                    Button radioButton = (Button) answers.findViewById(answer);
                    String youAnswered = (String) radioButton.getText();
                    if (youAnswered.equals("Nailed it!!"))
                        mCorrect++;

                    displayedFragment = Answer.newInstance(mCorrect, mTotal, youAnswered);

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
