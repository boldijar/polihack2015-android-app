package com.boldijarpaul.polihack;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.boldijarpaul.polihack.mvp.model.Story;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_HEADER = 1;
    private List<Story> mStories;
    private Context mContext;


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

            Story story = mStories.get(position - 1);
            StoryViewHolder storyViewHolder = (StoryViewHolder) holder;
            storyViewHolder.title.setText(story.name);
            GradientDrawable shapeDrawable = (GradientDrawable) storyViewHolder.circle.getBackground();
            shapeDrawable.setColor(story.getColor());
            storyViewHolder.secondLine.setText(mContext.getString(R.string.msg_quest_count) + "15");

        }
    }


    @Override
    public int getItemCount() {
        return mStories.size() + 1;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_story_circle)
        View circle;
        @Bind(R.id.item_story_second_line)
        TextView secondLine;
        @Bind(R.id.item_story_title)
        TextView title;

        public StoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
