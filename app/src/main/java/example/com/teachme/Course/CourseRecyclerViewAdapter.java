package example.com.teachme.Course;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.Game.GameActivity;
import example.com.teachme.R;
import example.com.teachme.api.CourseAPIInterface;
import example.com.teachme.model.Course;
import example.com.teachme.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {

    private List<Course> courses = null;
    private List<Course> favoriteList = null;
    private Context context = null;
    boolean Isfavorite = false;
    int userType = 0;
    String email = null;

    public CourseRecyclerViewAdapter(List<Course> items, List<Course> favoriteList, int userType, String email, Context context) {
        this.courses = items;
        this.context = context;
        this.favoriteList = favoriteList;
        this.email = email;
        this.userType = userType;
    }

    public boolean isChecked(int courseId) {

        if (favoriteList != null) {
            for (Course c : favoriteList) {
                if (c.getId() == courseId) {
                    return true;
                }
            }
        }
        return false;
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


        if (userType == 1) {
            holder.favorite.setVisibility(View.INVISIBLE);
        }

        if (isChecked(holder.mItem.getId())) {
            holder.favorite.setImageResource(R.drawable.on);
        } else {
            holder.favorite.setImageResource(R.drawable.off);
        }


        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.favorite.setImageResource(R.drawable.on);

                CourseAPIInterface courseAPIInterface = ApiUtils.getAPICourse();

                Student student = new Student();

                student.setMail(email);
                student.setName(DbUtils.name);

                // student.setName(user.getName());
                //student.setPassword(user.getPassword());

                Course course = new Course();

                course.setId(courses.get(position).getId());
                course.putStudent(student);

                Call<Course> conn = courseAPIInterface.enrollCourse(course);


                conn.enqueue(new Callback<Course>() {

                    @Override
                    public void onResponse(Call<Course> call, Response<Course> response) {
                        if (response.isSuccessful()) {
                            Course course = response.body();

                        }
                    }

                    @Override
                    public void onFailure(Call<Course> call, Throwable t) {

                    }
                });
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GameActivity.class);
                DbUtils.courseId = courses.get(position).getId();
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
        CardView cardView;
        public TextView mIdView;
        public TextView mContentView;
        public Course mItem;
        public ImageButton favorite;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            cardView = (CardView) view.findViewById(R.id.card_view);
            favorite = (ImageButton) view.findViewById(R.id.favorite);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
