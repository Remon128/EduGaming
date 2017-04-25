package example.com.teachme.Course;

import android.content.Context;
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
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private OnListFragmentInteractionListener mListener;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    Call<List<Course>> connection ;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {

    }

    public CourseFragment()
    {

    }

    public CourseFragment(String email) {

        CourseAPIInterface courseAPIInterface = ApiUtils.getAPICourse();
        User user = new User();
        user.setMail(email);

        connection = courseAPIInterface.getCourses(user);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        connection.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful()) {
                    courses = response.body();
                    //Toast.makeText(getContext(), "" + courses.get(0).getName(), Toast.LENGTH_SHORT).show();
                }
                Context context = view.getContext();

                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new LinearLayoutManager(context));

                recyclerView.setAdapter(new MyCourseRecyclerViewAdapter(courses));

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getContext(), "no connection", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getContext(),courses.get())
            }
        });
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
