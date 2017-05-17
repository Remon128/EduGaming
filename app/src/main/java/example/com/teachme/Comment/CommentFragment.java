package example.com.teachme.Comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import example.com.teachme.BuildConfig;
import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.api.CommentAPIInterface;
import example.com.teachme.model.Course;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {
    CommentRecyclerViewAdapter adapter;
    private OnListFragmentInteractionListener mListener = null;

    Observable<List<Comment>> connection;
    List<Comment> commentList = null;
    Context context;


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public CommentFragment() {

        CommentAPIInterface commentAPIInterface = ApiUtils.getAPICommentObservable();

        connection = commentAPIInterface.getComments(DbUtils.gameId);

        commentList = new ArrayList<Comment>();

        mListener = (OnListFragmentInteractionListener) context;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);


        context = view.getContext();


        RecyclerView recyclerView = (RecyclerView) view;

        adapter = new CommentRecyclerViewAdapter(commentList, context);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        recyclerView.setAdapter(adapter);


        connection.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "No Connection", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        commentList.addAll(comments);
                        adapter.notifyDataSetChanged();
                    }
                });

        return view;
    }


    OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient();

        List<Interceptor> interceptors = new ArrayList<>();

        client.networkInterceptors().addAll(interceptors);

        return client;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Comment item);
    }

}
