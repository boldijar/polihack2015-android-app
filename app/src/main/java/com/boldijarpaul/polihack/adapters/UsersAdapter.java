package com.boldijarpaul.polihack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.mvp.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

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
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView);
    }

    private String getProfileUrl(String id) {
        return "http://graph.facebook.com/" + id + "/picture?type=square&width=100&height=100";
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User user = mUsers.get(position);
        holder.title.setText(user.name);
        Picasso.with(mContext).load(getProfileUrl(user.facebookId)).into(holder.circle);
        holder.secondLine.setText("Points: " + user.score);
        holder.thirdLine.setText("County: Cluj Napoca");
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
        @Bind(R.id.item_user_3rd_line)
        TextView thirdLine;


        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
