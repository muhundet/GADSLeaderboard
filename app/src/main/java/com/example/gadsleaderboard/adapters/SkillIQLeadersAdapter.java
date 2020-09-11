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
import com.example.gadsleaderboard.models.SkillIQLeader;

import java.util.ArrayList;

public class SkillIQLeadersAdapter extends RecyclerView.Adapter<SkillIQLeadersAdapter.SkillIQLeaderViewHolder> {
    ArrayList<SkillIQLeader> skillIQLeaders;

    public SkillIQLeadersAdapter(ArrayList<SkillIQLeader> skillIQLeaders) {
        this.skillIQLeaders = skillIQLeaders;
    }

    @NonNull
    @Override
    public SkillIQLeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_2, parent, false);

        return new SkillIQLeaderViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(SkillIQLeaderViewHolder holder, int position) {
        SkillIQLeader skillIQLeader = skillIQLeaders.get(position);
        holder.bind(skillIQLeader);

    }

    @Override
    public int getItemCount() {
        return skillIQLeaders.size();
    }


    public class SkillIQLeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvHours;
        TextView tvCountry;
        ImageView imgLearnersBadge;

        public SkillIQLeaderViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_iq);
            tvHours = itemView.findViewById(R.id.tv_score);
            tvCountry = itemView.findViewById(R.id.tv_country_iq);
            imgLearnersBadge = itemView.findViewById(R.id.image_view);

        }

        public void bind(SkillIQLeader skillIQLeader) {
            tvName.setText(skillIQLeader.getName());
            tvHours.setText(skillIQLeader.getScore() + " skill IQ score,");
            tvCountry.setText(skillIQLeader.getCountry());
        }

    }
}
