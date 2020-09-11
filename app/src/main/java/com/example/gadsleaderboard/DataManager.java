package com.example.gadsleaderboard;

import com.example.gadsleaderboard.models.LearningLeader;
import com.example.gadsleaderboard.models.SkillIQLeader;

import java.util.ArrayList;

public class DataManager {
    private static DataManager ourInstance = null;
    ArrayList<LearningLeader> learners;
    ArrayList<SkillIQLeader> IQlearders;


    public static DataManager getInstance(){
        if(ourInstance == null){
            ourInstance = new DataManager();
        }
        return ourInstance;
    }

    public ArrayList<LearningLeader> getLearners(){
        return learners;
    }

    public ArrayList<SkillIQLeader> getIQlearders() {
        return IQlearders;
    }

    public void  populateLearners() {
        learners =  new ArrayList<>();
        LearningLeader l1 = new LearningLeader( "Tristone Muhunde","200 learning hours", "Zimbabwe", "image");
        LearningLeader l2 = new LearningLeader( "Tristone Muhunde","200 learning hours", "Zimbabwe", "image");
        LearningLeader l3 = new LearningLeader( "Tristone Muhunde","200 learning hours", "Zimbabwe", "image");

        learners.add(l1);
        learners.add(l2);
        learners.add(l3);


    }

    public void  populateIQLeaders() {
        IQlearders =  new ArrayList<>();
        SkillIQLeader i1 = new SkillIQLeader( "Tristone Muhunde","200 learning hours", "Zimbabwe", "image");
        SkillIQLeader i2 = new SkillIQLeader( "Tristone Muhunde","200 learning hours", "Zimbabwe", "image");
        SkillIQLeader i3 = new SkillIQLeader( "Tristone Muhunde","200 learning hours", "Zimbabwe", "image");

        IQlearders.add(i1);
        IQlearders.add(i2);
        IQlearders.add(i3);


    }
}
