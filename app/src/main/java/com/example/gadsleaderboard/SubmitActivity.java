package com.example.gadsleaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadsleaderboard.Utils.GADSAPIUtil;
import com.example.gadsleaderboard.models.SubmitPost;
import com.example.gadsleaderboard.remote.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    public static final String TAG = "SUBMIT ACTIVITY";
    Button btnSubmit;
    EditText etFirstName;
    EditText etLastName;
    EditText etEmailAddress;
    EditText etGithubLink;

    APIService mAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmailAddress = findViewById(R.id.et_email_address);
        etGithubLink = findViewById(R.id.et_github_link);
        btnSubmit = findViewById(R.id.btn_Submit);

        mAPIService = GADSAPIUtil.getAPIService();

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    String firstName  = etFirstName.getText().toString().trim();
                                    String lastName = etLastName.getText().toString().trim();
                                    String emailAddress = etEmailAddress.getText().toString().trim();
                                    String githubLink = etGithubLink.getText().toString().trim();

                                        if (etFirstName.getText().toString().trim().length() > 0 &&
                                                etLastName.getText().toString().trim().length() > 0 &&
                                                etEmailAddress.getText().toString().trim().length() > 0 &&
                                                etGithubLink.getText().toString().trim().length() > 0 ) {
                                            sendPost(firstName, lastName, emailAddress, githubLink);
                                        } else {
                                            Toast.makeText(getApplicationContext(),"Required Fields Missing!",Toast.LENGTH_SHORT).show();

                                        }
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:

                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder ab = new AlertDialog.Builder(SubmitActivity.this);
                    ab.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener).show();
                }
            });
    }

    private void sendPost(String firstName, String lastName, String emailAddress, String githubLink) {
        mAPIService.savePost(firstName, lastName, emailAddress, githubLink).enqueue(new Callback<SubmitPost>() {
            @Override
            public void onResponse(Call<SubmitPost> call, Response<SubmitPost> response) {
                if(response.isSuccessful()) {
                    ImageView view = new ImageView(SubmitActivity.this);
                    view.setImageResource(R.drawable.ic_submitted);
                    AlertDialog.Builder ab = new AlertDialog.Builder(SubmitActivity.this);
                    ab.setView(view);
                    ab.setMessage("Submission Successful").show();
                    Log.i(TAG, "post submitted to API Successfully." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<SubmitPost> call, Throwable t) {
                ImageView view = new ImageView(SubmitActivity.this);
                view.setImageResource(R.drawable.ic_notsubmitted);
                AlertDialog.Builder ab = new AlertDialog.Builder(SubmitActivity.this);
                ab.setView(view);
                ab.setMessage("Submission not successful").show();
                Log.e(TAG, "Failure sending to API." );
            }
        });
    }

}