package example.com.teachme.Comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.api.CommentAPIInterface;
import example.com.teachme.model.Course;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CommentFragment extends Fragment {
    CommentRecyclerViewAdapter adapter;
    private OnListFragmentInteractionListener mListener = null;
    Call<List<Comment>> connection;
    List<Comment> commentList = null;
    Context context;

    public CommentFragment(Context context) {

        this.context = context;
        CommentAPIInterface commentAPIInterface = ApiUtils.getAPIComment();

        connection = commentAPIInterface.getComments(DbUtils.gameId);

        commentList = new ArrayList<>();

        mListener = (OnListFragmentInteractionListener)context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);

        Context context = view.getContext();

        RecyclerView recyclerView = (RecyclerView) view;

        adapter = new CommentRecyclerViewAdapter(commentList, context);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        recyclerView.setAdapter(adapter);


        connection.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (response.isSuccessful()) {
                    //if (response.body() != null)
                      //  commentList.addAll(response.body());

                    Toast.makeText(getContext(), "" + commentList.size(), Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(getContext(), "No Connection", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Comment item);
    }

}
