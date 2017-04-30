package example.com.teachme.Question;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.teachme.R;


public class CreateQuestionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    EditText desc1;
    EditText q1;
    EditText q2;
    EditText q3;
    EditText q4;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;

    boolean questionType = false;
    //true if mcq
    // false if tf
    EditText desc2;
    RadioButton t;
    RadioButton f;

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    Spinner spinner;
    Context context;
    LinearLayout linearLayoutmcq = null, linearLayouttf;

    public CreateQuestionFragment(Context context, OnFragmentInteractionListener newListener) {
        this.context = context;
        this.mListener = newListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_question, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner);

        List<String> list = new ArrayList<>();
        list.add("No Question Type Selected");
        list.add("MCQ");
        list.add("True & False");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        linearLayouttf = (LinearLayout) view.findViewById(R.id.tf);
        linearLayoutmcq = (LinearLayout) view.findViewById(R.id.mcq);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = spinner.getSelectedItem().toString();

                Toast.makeText(context, item, Toast.LENGTH_SHORT).show();

                if (item.equals("MCQ")) {
                    questionType = true;
                    linearLayoutmcq.setVisibility(View.VISIBLE);
                    linearLayouttf.setVisibility(View.GONE);

                } else if (item.equals("True & False")) {
                    questionType = false;
                    linearLayouttf.setVisibility(View.VISIBLE);
                    linearLayoutmcq.setVisibility(View.GONE);
                } else {
                    linearLayouttf.setVisibility(View.GONE);
                    linearLayoutmcq.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                linearLayoutmcq.setVisibility(View.GONE);
                linearLayouttf.setVisibility(View.GONE);

            }
        });


        desc1 = (EditText) view.findViewById(R.id.question);
        q1 = (EditText) view.findViewById(R.id.answer1);
        q2 = (EditText) view.findViewById(R.id.answer2);
        q3 = (EditText) view.findViewById(R.id.answer3);
        q4 = (EditText) view.findViewById(R.id.answer4);

        r1 = (RadioButton) view.findViewById(R.id.radio1);
        r2 = (RadioButton) view.findViewById(R.id.radio2);
        r3 = (RadioButton) view.findViewById(R.id.radio3);
        r4 = (RadioButton) view.findViewById(R.id.radio4);

        t1 = (TextView) view.findViewById(R.id.t1);
        t2 = (TextView) view.findViewById(R.id.t2);
        t3 = (TextView) view.findViewById(R.id.t3);
        t4 = (TextView) view.findViewById(R.id.t4);


        desc2 = (EditText) view.findViewById(R.id.tfQuestion);
        t = (RadioButton) view.findViewById(R.id.flag1);
        f = (RadioButton) view.findViewById(R.id.flag2);

        Button createmcq = (Button) view.findViewById(R.id.createmcq);
        Button createtf = (Button) view.findViewById(R.id.createtf);

        Button showmcq = (Button) view.findViewById(R.id.showmcq);
        Button showtf = (Button) view.findViewById(R.id.showtf);

        showmcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuestion();
            }
        });

        showtf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuestion();
            }
        });
        createmcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestion();
            }
        });

        createtf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestion();
            }
        });


        return view;
    }

    public void createQuestion() {
        if (questionType) {
            if (desc1.getText().length() > 0) {
                if (q1.getText().length() > 0 && q2.getText().length() > 0 && q3.getText().length() > 0 && q4.getText().length() > 0) {

                    if (r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked()) {
                        MCQ tf = new MCQ();
                        tf.setDescription(desc1.getText().toString());
                        List<String> list = new ArrayList<>();

                        list.add(q1.getText().toString());
                        list.add(q2.getText().toString());
                        list.add(q3.getText().toString());
                        list.add(q4.getText().toString());

                        tf.setChoices(list.toArray(new String[0]));

                        if (r1.isChecked())
                            tf.setAnswer(1);
                        else if (r2.isChecked())
                            tf.setAnswer(2);
                        else if (r3.isChecked())
                            tf.setAnswer(3);
                        else if (r4.isChecked())
                            tf.setAnswer(4);


                        mListener.onFragmentInteraction(tf);


                        startActivity(new Intent(context,QuestionActivity.class));

                    } else
                        Toast.makeText(context, "Please check an answer", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "Please don't leave any question empty", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(context, "Please don't leave question description empty", Toast.LENGTH_SHORT).show();

        } else {
            if (desc2.getText().length() > 0) {

                if (t.isChecked() || f.isChecked()) {
                    MCQ tf = new MCQ();
                    tf.setDescription(desc2.getText().toString());
                    List<String> list = new ArrayList<>();

                    list.add("True");
                    list.add("False");
                    tf.setChoices(list.toArray(new String[0]));

                    if (t.isChecked())
                        tf.setAnswer(1);
                    else
                        tf.setAnswer(2);

                    mListener.onFragmentInteraction(tf);

                    startActivity(new Intent(context,QuestionActivity.class));

                }

            } else
                Toast.makeText(context, "Please don't leave any question empty", Toast.LENGTH_SHORT).show();

        }


    }

    public void showQuestion() {

        if (questionType) {

            if (desc1.getText().length() > 0) {
                if (q1.getText().length() > 0 && q2.getText().length() > 0 && q3.getText().length() > 0 && q4.getText().length() > 0) {
                    t1.setText(q1.getText());
                    t2.setText(q2.getText());
                    t3.setText(q3.getText());
                    t4.setText(q4.getText());

                    q1.setVisibility(View.GONE);
                    q2.setVisibility(View.GONE);
                    q3.setVisibility(View.GONE);
                    q4.setVisibility(View.GONE);
                } else
                    Toast.makeText(context, "Please don't leave any question empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (desc2.getText().length() > 0) {
                if (t.isChecked() || f.isChecked()) {
                    //
                }

            } else
                Toast.makeText(context, "Please don't leave any question empty", Toast.LENGTH_SHORT).show();

        }
    }


            /*

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

*/


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(MCQ question);
    }
}
