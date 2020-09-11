package com.example.gadsleaderboard.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gadsleaderboard.DataManager;
import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.Utils.IQLeadersAPIUtil;
import com.example.gadsleaderboard.Utils.LeadingLearnersAPIUtil;
import com.example.gadsleaderboard.adapters.LearningLeadersAdapter;
import com.example.gadsleaderboard.adapters.SkillIQLeadersAdapter;
import com.example.gadsleaderboard.models.LearningLeader;
import com.example.gadsleaderboard.models.SkillIQLeader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQLeadersFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView mRecyclerView;
    ProgressBar mPgBarIq;
    TextView tvError ;
    URL urlIq;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }



    public static SkillIQLeadersFragment newInstance(int index) {
        SkillIQLeadersFragment fragment = new SkillIQLeadersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int index = 1;
//        if (getArguments() != null) {
//            index = getArguments().getInt(ARG_SECTION_NUMBER);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        tvError = root.findViewById(R.id.tvError);
        mRecyclerView = root.findViewById(R.id.rv_skilliq_leaders);
        mPgBarIq = root.findViewById(R.id.pgBarIQ);

//        DataManager.getInstance().populateIQLeaders();
//        mSkillIQLeaders = DataManager.getInstance().getIQlearders();
//        mSkillIQLeadersAdapter = new SkillIQLeadersAdapter(mSkillIQLeaders);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setAdapter(mSkillIQLeadersAdapter);

        try {
            urlIq = IQLeadersAPIUtil.buildUrl();
            new SkillIQLeadersFragment.BooksQueryTask().execute(urlIq);
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
                result = IQLeadersAPIUtil.getJson(searchURL);
            }
            catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            mPgBarIq.setVisibility(View.INVISIBLE);
            if (result == null) {
                mRecyclerView.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }
            else {
                mRecyclerView.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);
            }
            ArrayList<SkillIQLeader> iqlearners = IQLeadersAPIUtil.getIQLeanersFromJson(result);
            SkillIQLeadersAdapter adapter = new SkillIQLeadersAdapter(iqlearners);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPgBarIq.setVisibility(View.VISIBLE);
        }
    }

}