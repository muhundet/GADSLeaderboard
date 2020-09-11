package com.example.gadsleaderboard.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.Utils.GADSAPIUtil;
import com.example.gadsleaderboard.Utils.LeadingLearnersAPIUtil;
import com.example.gadsleaderboard.adapters.LearningLeadersAdapter;
import com.example.gadsleaderboard.models.LearningLeader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class LearningLeadersFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    RecyclerView mRecyclerView;
    LearningLeadersAdapter mLearningLeadersAdapter;
    ProgressBar mPgBarLearners;
    TextView tvSectionLabel ;
    URL urlLeaners;


    public static LearningLeadersFragment newInstance(int index) {
        LearningLeadersFragment fragment = new LearningLeadersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        tvSectionLabel = root.findViewById(R.id.section_label);
        mRecyclerView = root.findViewById(R.id.rv_learning_leaders);
        mPgBarLearners = root.findViewById(R.id.pgBarLearners);
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvSectionLabel.setText(s);
            }
        });

//        pageViewModel.getLearningLeaders().observe(getViewLifecycleOwner(), new Observer<ArrayList<LearningLeader>>() {
//            @Override
//            public void onChanged(ArrayList<LearningLeader> learningLeaders) {
//                mLearningLeadersAdapter = new LearningLeadersAdapter(learningLeaders);
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                mRecyclerView.setAdapter(mLearningLeadersAdapter);
//            }
//        });

        try {
            urlLeaners = LeadingLearnersAPIUtil.buildUrl();
            new BooksQueryTask().execute(urlLeaners);
        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return root;
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = LeadingLearnersAPIUtil.getJson(searchURL);
            }
            catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            mPgBarLearners.setVisibility(View.INVISIBLE);
            if (result == null) {
                mRecyclerView.setVisibility(View.INVISIBLE);
                tvSectionLabel.setVisibility(View.VISIBLE);
            }
            else {
                mRecyclerView.setVisibility(View.VISIBLE);
                tvSectionLabel.setVisibility(View.INVISIBLE);
            }
            ArrayList<LearningLeader> learners = LeadingLearnersAPIUtil.getLeanersFromJson(result);
            LearningLeadersAdapter adapter = new LearningLeadersAdapter(learners);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPgBarLearners.setVisibility(View.VISIBLE);
        }
    }
}