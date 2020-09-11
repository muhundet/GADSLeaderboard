package com.example.gadsleaderboard.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.gadsleaderboard.DataManager;
import com.example.gadsleaderboard.models.LearningLeader;

import java.util.ArrayList;

public class PageViewModel extends ViewModel {
    ArrayList<LearningLeader> mLearningLeadersArrayList;

    MutableLiveData<ArrayList<LearningLeader>> learningLeadersLiveData = new MutableLiveData<>();

    public PageViewModel() {
//        DataManager.getInstance().populateLearners();
//        mLearningLeadersArrayList = DataManager.getInstance().getLearners();
    }

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Error loading learners: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

//    public LiveData<ArrayList<LearningLeader>> getLearningLeaders(){
//        learningLeadersLiveData.setValue(mLearningLeadersArrayList);
//        return learningLeadersLiveData;
//    }


}