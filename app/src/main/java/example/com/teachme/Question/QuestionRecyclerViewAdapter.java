package example.com.teachme.Question;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import example.com.teachme.Question.QuestionFragment.OnListFragmentInteractionListener;
import example.com.teachme.Question.dummy.DummyContent.DummyItem;
import example.com.teachme.R;

import java.util.ArrayList;
import java.util.List;


public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    static private List<MCQ> questions = null;
    private Context context = null;
    static private int[] userAnswers;


    public QuestionRecyclerViewAdapter(List<MCQ> items, Context context) {
        questions = items;
        this.context = context;
        userAnswers = new int[questions.size()+1];
    }



    public static int[] getUserAnswers() {
        return userAnswers;
    }

    public static List<MCQ> getQuestions() {
        return questions;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_question, parent, false);

        if(userAnswers.length!=questions.size()+1)
             userAnswers = new int[questions.size()+1];

        //  Toast.makeText(context.getApplicationContext(), userAnswers.length + "", Toast.LENGTH_SHORT).show();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = questions.get(position);
        holder.mQuestion.setText(questions.get(position).getDescription());
        String[] chs = holder.mItem.getChoices();

        holder.r1.setText(String.format("%s", chs[0]));
        holder.r2.setText(String.format("%s", chs[1]));
        holder.r3.setVisibility(View.GONE);
        holder.r4.setVisibility(View.GONE);

        if (chs.length == 4) {
            holder.r3.setVisibility(View.VISIBLE);
            holder.r4.setVisibility(View.VISIBLE);

            holder.r3.setText(String.format("%s", chs[2]));
            holder.r4.setText(String.format("%s", chs[3]));
        }
        holder.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                String pos = holder.mItem.getId();


                int newPosition = Integer.parseInt(pos);

                switch (checkedId) {
                    case R.id.radio1:
                        userAnswers[newPosition] = 1;
                        break;
                    case R.id.radio2:
                        userAnswers[newPosition] = 2;
                        break;
                    case R.id.radio3:
                        userAnswers[newPosition] = 3;
                        break;
                    case R.id.radio4:
                        userAnswers[newPosition] = 4;
                        break;
                }
                Toast.makeText(context.getApplicationContext(), newPosition + "", Toast.LENGTH_SHORT).show();

            }
        });
/*
        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = holder.group.getCheckedRadioButtonId();
                Toast.makeText(context.getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();

                switch (selectedId)
                {
                    case R.id.radio1:
                        userAnswers.add(position,1);
                        break;
                    case R.id.radio2:
                        userAnswers.add(position,2);
                        break;
                    case R.id.radio3:
                        userAnswers.add(position,3);
                        break;
                    case R.id.radio4:
                        userAnswers.add(position,4);
                        break;
                }
            }
        });
*/
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //            Intent i = new Intent()
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View mView;
        public TextView mQuestion;
        public RadioGroup group;
        public MCQ mItem;
        public RadioButton r1;
        public RadioButton r2;
        public RadioButton r3;
        public RadioButton r4;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mQuestion = (TextView) view.findViewById(R.id.question);
            group = (RadioGroup) view.findViewById(R.id.mcqGroup);
            r1 = (RadioButton) view.findViewById(R.id.radio1);
            r2 = (RadioButton) view.findViewById(R.id.radio2);
            r3 = (RadioButton) view.findViewById(R.id.radio3);
            r4 = (RadioButton) view.findViewById(R.id.radio4);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

        }
    }
}
