package example.com.teachme.Question;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.R;
import example.com.teachme.Question.dummy.DummyContent;
import example.com.teachme.Question.dummy.DummyContent.DummyItem;
import example.com.teachme.api.QuestionAPIInterface;
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
public class QuestionFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    QuestionRecyclerViewAdapter adapter;
    List<MCQ> mcqList = null;
    Call<List<MCQ>> connection;

    public QuestionFragment(String gameId) {
        mcqList = new ArrayList<>();
        QuestionAPIInterface questionAPIInterface = ApiUtils.getAPIQuestion();
        connection = questionAPIInterface.getQuestions(gameId);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new QuestionRecyclerViewAdapter(mcqList, context);
        recyclerView.setAdapter(adapter);


        connection.enqueue(new Callback<List<MCQ>>() {
            @Override
            public void onResponse(Call<List<MCQ>> call, Response<List<MCQ>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null)
                        mcqList.addAll(response.body());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MCQ>> call, Throwable t) {

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
        void onListFragmentInteraction(Question item);
    }
}
