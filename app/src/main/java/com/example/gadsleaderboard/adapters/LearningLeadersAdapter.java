package com.example.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.models.LearningLeader;

import java.util.ArrayList;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.LearningLeaderViewHolder> {
    ArrayList<LearningLeader> learningLeaders;

    public LearningLeadersAdapter(ArrayList<LearningLeader> learningLeaders) {
        this.learningLeaders = learningLeaders;
    }

    @NonNull
    @Override
    public LearningLeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_1, parent, false);

        return new LearningLeaderViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(LearningLeaderViewHolder holder, int position) {
        LearningLeader learningLeader = learningLeaders.get(position);
        holder.bind(learningLeader);

    }

    @Override
    public int getItemCount() {
        return learningLeaders.size();
    }


    public class LearningLeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvHours;
        TextView tvCountry;
        ImageView imgLearnersBadge;

        public LearningLeaderViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvHours = itemView.findViewById(R.id.tv_hours);
            tvCountry = itemView.findViewById(R.id.tv_country);
            imgLearnersBadge = itemView.findViewById(R.id.image_view);

        }

        public void bind(LearningLeader learningLeader) {
            tvName.setText(learningLeader.getName());
            tvHours.setText(learningLeader.getHours() + " learning hours,");
            tvCountry.setText(learningLeader.getCountry());
        }

    }
}
