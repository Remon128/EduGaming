package example.com.teachme.Course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.User.UserFactory;
import example.com.teachme.api.CourseAPIInterface;
import example.com.teachme.model.Course;
import example.com.teachme.model.Student;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    UserFactory userFactory = null;
    private int courseType;

    Call<List<Course>> connection = null;
    CourseAPIInterface courseAPIInterface = null;
    User user;
    CourseRecyclerViewAdapter adapter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (courseType != 0)
            outState.putInt("courseType", courseType);
    }

    //courseType = 1 for teacher
    //courseType = 2 for STUDENT
    //courseType = 3 for All


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CourseFragment newInstance(String email, int courseType) {
        CourseFragment fragment = new CourseFragment();

        Bundle args = new Bundle();
        args.putString("email", email);
        args.putInt("courseType", courseType);
        fragment.setArguments(args);
        return fragment;
    }


    public CourseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        courseAPIInterface = ApiUtils.getAPICourse();
        courses = new ArrayList<>();
        fcourses = new ArrayList<>();


        if (getArguments() != null) {

            Bundle bundle = getArguments();
            this.courseType = bundle.getInt("courseType", courseType);

        }


        if (savedInstanceState != null)
            this.courseType = savedInstanceState.getInt("courseType");


        if (courseType == 1) {
            userFactory = UserFactory.getFactory("Teacher");
            user = userFactory.createUser();
            user.setMail(DbUtils.mail);
            connection = courseAPIInterface.getCourses(user);
        } else if (courseType == 2) {
            userFactory = UserFactory.getFactory("Student");
            user = userFactory.createUser();
            user.setMail(DbUtils.mail);
            connection = courseAPIInterface.getCoursesStudent(user);
        } else if (courseType == 3) {
            connection = courseAPIInterface.getAllCourses();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CourseRecyclerViewAdapter(courses, fcourses, courseType, mail, getContext());
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
                Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
        });

        courseAPIInterface = ApiUtils.getAPICourse();

        if (courseType != 1) {

            connection = courseAPIInterface.getCoursesStudent((Student) user);

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
                    Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Course item);
    }
}
