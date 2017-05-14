package example.com.teachme.Comment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.teachme.Comment.CommentFragment.OnListFragmentInteractionListener;
import example.com.teachme.R;

import java.util.List;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private List<Comment> mValues;
    private OnListFragmentInteractionListener mListener;

    public CommentRecyclerViewAdapter(List<Comment> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.cardView.setTag(position);
        holder.mItem = mValues.get(position);
        String name ;
        if(holder.mItem.getStudent()==null)
        {
            name = holder.mItem.getTeacher().getName();
        }else
        {
            name = holder.mItem.getStudent().getName();
        }
        holder.username.setText(name);
        holder.comment.setText(mValues.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public CardView cardView;
        public TextView comment;
        public Comment mItem;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            username = (TextView) view.findViewById(R.id.username);
            comment = (TextView) view.findViewById(R.id.comment);
        }
    }
}

