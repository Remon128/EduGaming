package example.com.teachme.Game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import example.com.teachme.R;
import example.com.teachme.api.GameAPIInterface;
import example.com.teachme.model.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GameFragment extends Fragment {

    private List<Game> gameList = null;
    private OnListFragmentInteractionListener mListener;
    private Call<List<Game>> connection;
    private Context context;
    private int courseId;
    GameAPIInterface gameAPIInterface = ApiUtils.getAPIGame();
    private GameFragment.OnListFragmentInteractionListener listener;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("courseId", courseId);
    }


    public void setmListener(OnListFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    @SuppressWarnings("unused")
    public static GameFragment newInstance(int courseId) {
        GameFragment fragment = new GameFragment();

        Bundle args = new Bundle();
        args.putInt("courseId", courseId);
        fragment.setArguments(args);
        return fragment;
    }


    public GameFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            courseId = getArguments().getInt("courseId");

        connection = gameAPIInterface.getGames(courseId);
        gameList = new ArrayList<>();


    }

    GameRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new GameRecyclerViewAdapter(gameList, context, mListener);
        recyclerView.setAdapter(adapter);

        connection.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        Toast.makeText(getContext(), getString(R.string.no_quizes_availables), Toast.LENGTH_SHORT).show();
                    } else
                        gameList.addAll(response.body());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Game item);

        void onGameSelectionInteraction(Intent intent);

        void onCommentSelectionInteraction(Intent intent);


    }

}
