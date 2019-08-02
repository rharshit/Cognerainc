package com.rharshit.cognerainc;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.rharshit.cognerainc.Callbacks.processCallback;
import static com.rharshit.cognerainc.Callbacks.setCallback;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 1;
    private static final String TAG = "MainActivity";
    private Context mContext;
    private JobScheduler jobScheduler;
    private JobInfo job;
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        etInput = findViewById(R.id.et_input);

        jobScheduler = (JobScheduler) getApplicationContext()
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,
                BGService.class);

        setCallback = new Callbacks.setCallback() {
            @Override
            public void set(String s) {
                etInput.setText(s);
            }
        };

        job = new JobInfo.Builder(JOB_ID, componentName)
                .setMinimumLatency(500)
                .setOverrideDeadline(0)
                .build();
    }

    public void start(View view) {
        JobInfo jobInfo = jobScheduler.getPendingJob(JOB_ID);
        if (jobInfo == null) {
            Log.d(TAG, "start: restarting job");
            jobScheduler.schedule(job);
        } else {
            Log.d(TAG, "start: running");
            Toast.makeText(mContext, "service is already started", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View view) {
        JobInfo jobInfo = jobScheduler.getPendingJob(JOB_ID);
        if (jobInfo == null) {
            Log.d(TAG, "stop: no jobs");
            Toast.makeText(mContext, "service isn't started yet", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "stop: stopping job");
            jobScheduler.cancel(JOB_ID);
        }
    }

    public void send(View view) {
        if (processCallback == null) {
            Toast.makeText(mContext, "Service not running", Toast.LENGTH_SHORT).show();
        } else {
            processCallback.process(etInput.getText().toString());
        }
    }
}
