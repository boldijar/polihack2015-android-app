package com.boldijarpaul.polihack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.mvp.model.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> mUsers;
    private Context mContext;
    private UserAdapterListener mListener;

    public void setListener(UserAdapterListener mListener) {
        this.mListener = mListener;
    }


    public UsersAdapter(List<User> mUsers, Context mContext) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final User user = mUsers.get(position);
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        userViewHolder.title.setText(user.name);
        userViewHolder.secondLine.setText("Points: " + user.score);
        userViewHolder.circle.setImageResource(((int) (Math.random() * 100) % 2) == 0 ? R.drawable.ic_close_white_24dp :
                R.drawable.ic_check_white_24dp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(user);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public interface UserAdapterListener {
        void onItemClick(User user);
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_user_avatar)
        CircleImageView circle;
        @Bind(R.id.item_user_second_line)
        TextView secondLine;
        @Bind(R.id.item_user_title)
        TextView title;


        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
