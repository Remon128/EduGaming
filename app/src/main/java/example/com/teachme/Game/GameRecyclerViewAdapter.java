package example.com.teachme.Game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.teachme.Connection.DbUtils;
import example.com.teachme.Game.GameFragment.OnListFragmentInteractionListener;
import example.com.teachme.Question.QuestionActivity;
import example.com.teachme.R;
import example.com.teachme.model.Game;

import java.util.List;

public class GameRecyclerViewAdapter extends RecyclerView.Adapter<GameRecyclerViewAdapter.ViewHolder> {

    private List<Game> mValues;
    private Context context ;

    public GameRecyclerViewAdapter(List<Game> items, Context context) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getDescription());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, QuestionActivity.class);
                i.putExtra("gameId",holder.mItem.getId());
                DbUtils.gameId = holder.mItem.getId();
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Game mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
