package example.com.teachme.Question;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import example.com.teachme.Question.QuestionFragment.OnListFragmentInteractionListener;
import example.com.teachme.Question.dummy.DummyContent.DummyItem;
import example.com.teachme.R;

import java.util.List;


public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    private List<MCQ> questions = null ;
    private Context context =null;
    public QuestionRecyclerViewAdapter(List<MCQ> items,Context context) {
        questions = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = questions.get(position);
        holder.mQuestion.setText(questions.get(position).getDescription());
        //holder.mItem.getChoices()[1]
        holder.r1.setText("hi");
        holder.r2.setText("hi");
        holder.r3.setText("hi");
        holder.r4.setText("hi");

        //holder.mContentView.setText(questions.get(position).getDescription());

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mQuestion;
        public RadioGroup group ;
        public MCQ mItem;
        public RadioButton r1;
        public  RadioButton r2;
        public  RadioButton r3;
        public RadioButton r4 ;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mQuestion = (TextView) view.findViewById(R.id.question);
            group = (RadioGroup) view.findViewById(R.id.mcqGroup);
            r1 = (RadioButton) view.findViewById(R.id.radio1);
            r2 =(RadioButton) view.findViewById(R.id.radio2);
            r3 = (RadioButton) view.findViewById(R.id.radio3);
             r4 = (RadioButton) view.findViewById(R.id.radio4);

        }

    }
}
