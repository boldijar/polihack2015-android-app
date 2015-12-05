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
import com.boldijarpaul.polihack.mvp.model.Quest;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Quest> mQuests;
    private Context mContext;
    private QuestAdapterListener mListener;

    public void setListener(QuestAdapterListener mListener) {
        this.mListener = mListener;
    }


    public QuestAdapter(List<Quest> mQuests, Context mContext) {
        this.mQuests = mQuests;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quest, parent, false);
        return new QuestViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Quest quest = mQuests.get(position);
        QuestViewHolder questViewHolder = (QuestViewHolder) holder;
        questViewHolder.title.setText(quest.name);
        GradientDrawable shapeDrawable = (GradientDrawable) questViewHolder.circle.getBackground();
        shapeDrawable.setColor(quest.color);
        questViewHolder.secondLine.setText("Points: " + quest.points);
        questViewHolder.circle.setImageResource(((int) (Math.random() * 100) % 2) == 0 ? R.drawable.ic_close_white_24dp :
                R.drawable.ic_check_white_24dp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(quest);
                }
            }
        });
        questViewHolder.rate1.setVisibility(View.GONE);
        questViewHolder.rate2.setVisibility(View.GONE);
        questViewHolder.rate3.setVisibility(View.GONE);
        questViewHolder.rate4.setVisibility(View.GONE);
        if (quest.rating >= 1) {
            questViewHolder.rate1.setVisibility(View.VISIBLE);
        }
        if (quest.rating >= 2) {
            questViewHolder.rate2.setVisibility(View.VISIBLE);
        }
        if (quest.rating >= 3) {
            questViewHolder.rate3.setVisibility(View.VISIBLE);
        }
        if (quest.rating >= 4) {
            questViewHolder.rate4.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return mQuests.size();
    }

    public interface QuestAdapterListener {
        void onItemClick(Quest quest);
    }


    public static class QuestViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_quest_circle)
        ImageView circle;
        @Bind(R.id.item_quest_second_line)
        TextView secondLine;
        @Bind(R.id.item_quest_title)
        TextView title;
        @Bind(R.id.item_quest_rate1)
        View rate1;
        @Bind(R.id.item_quest_rate2)
        View rate2;
        @Bind(R.id.item_quest_rate3)
        View rate3;
        @Bind(R.id.item_quest_rate4)
        View rate4;

        public QuestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
