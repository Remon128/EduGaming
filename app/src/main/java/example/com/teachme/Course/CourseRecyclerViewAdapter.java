package example.com.teachme.Course;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import example.com.teachme.Course.CourseFragment.OnListFragmentInteractionListener;
import example.com.teachme.Game.GameActivity;
import example.com.teachme.R;
import example.com.teachme.User.MainActivity;
import example.com.teachme.model.Course;

import java.util.List;



public class MyCourseRecyclerViewAdapter extends RecyclerView.Adapter<MyCourseRecyclerViewAdapter.ViewHolder> {

    private List<Course> courses = null;
    private Context context = null;

    public MyCourseRecyclerViewAdapter(List<Course> items,Context context) {
        this.courses = items;
        this.context = context ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = courses.get(position);
        holder.mIdView.setText(courses.get(position).getName());
        holder.mContentView.setText(courses.get(position).getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GameActivity.class);
                i.putExtra("courseid",courses.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        CardView cardView ;
        public TextView mIdView;
        public TextView mContentView;
        public Course mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            cardView = (CardView)view.findViewById(R.id.card_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
