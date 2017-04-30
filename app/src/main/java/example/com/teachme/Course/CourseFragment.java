package example.com.teachme.Course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.R;
import example.com.teachme.api.CourseAPIInterface;
import example.com.teachme.model.Course;
import example.com.teachme.model.Teacher;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CourseFragment extends Fragment {

    private String mail;
    private List<Course> courses = null;
    private List<Course> fcourses = null;

    int courseType;

    Call<List<Course>> connection = null;
    CourseAPIInterface courseAPIInterface = null;
    User user;
    CourseRecyclerViewAdapter adapter;

    public CourseFragment() {
        courseAPIInterface = ApiUtils.getAPICourse();
    }

    //courseType = 1 for teacher
    //courseType = 2 for STUDENT
    //courseType = 3 for All

    public CourseFragment(String email, int courseType) {

        courseAPIInterface = ApiUtils.getAPICourse();
        user = new User();
        user.setMail(email);
        Teacher teacher = new Teacher();
        teacher.setMail(email);
        mail = email;
        courses = new ArrayList<>();
        fcourses = new ArrayList<>();
        this.courseType = courseType;

        if (courseType == 1)
            connection = courseAPIInterface.getCourses(teacher);
        else if (courseType == 2)
            connection = courseAPIInterface.getCoursesStudent(user);
        else if (courseType == 3)
            connection = courseAPIInterface.getAllCourses();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CourseRecyclerViewAdapter(courses, fcourses, courseType , mail , getContext());
        recyclerView.setAdapter(adapter);

        connection.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        courses.addAll(response.body());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getContext(), "no connection", Toast.LENGTH_SHORT).show();
            }
        });

        courseAPIInterface = ApiUtils.getAPICourse();

        if (courseType != 1) {

            connection = courseAPIInterface.getCoursesStudent(user);

            connection.enqueue(new Callback<List<Course>>() {
                @Override
                public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                    if (response.isSuccessful())
                        if (response.body() != null) {
                            fcourses.addAll(response.body());
                        }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Course>> call, Throwable t) {
                    Toast.makeText(getContext(), "No Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }


/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
  */

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Course item);
    }
}
