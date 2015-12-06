package com.boldijarpaul.polihack.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.mvp.model.Story;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_HEADER = 1;
    private List<Story> mStories;
    private Context mContext;
    private StoryAdapterListener mListener;

    public void setListener(StoryAdapterListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public StoryAdapter(List<Story> mStories, Context mContext) {
        this.mStories = mStories;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_story, parent, false);
            return new HeaderViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
            return new StoryViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {

            final Story story = mStories.get(position - 1);
            StoryViewHolder storyViewHolder = (StoryViewHolder) holder;
            storyViewHolder.title.setText(story.name);
            GradientDrawable shapeDrawable = (GradientDrawable) storyViewHolder.circle.getBackground();
            shapeDrawable.setColor(story.color);
            storyViewHolder.secondLine.setText(mContext.getString(R.string.msg_quest_count) + "15");
            storyViewHolder.circle.setImageResource(((int) (Math.random() * 100) % 2) == 0 ? R.drawable.ic_close_white_24dp :
                    R.drawable.ic_check_white_24dp);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(story);
                    }
                }
            });

        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onHeaderClick();
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mStories.size() + 1;
    }

    public interface StoryAdapterListener {
        void onHeaderClick();

        void onItemClick(Story story);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_story_circle)
        ImageView circle;
        @Bind(R.id.item_story_second_line)
        TextView secondLine;
        @Bind(R.id.item_story_title)
        TextView title;
        @Bind(R.id.item_story_root)
        View root;

        public StoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
