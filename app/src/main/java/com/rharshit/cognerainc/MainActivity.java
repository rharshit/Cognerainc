package com.rharshit.cognerainc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private JobScheduler jobScheduler;
    private JobInfo job;

    private static final int JOB_ID = 1;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        jobScheduler = (JobScheduler)getApplicationContext()
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,
                BGService.class);

        job = new JobInfo.Builder(JOB_ID, componentName)
                .setPeriodic(500)
                .build();
    }

    public void start(View view) {
        JobInfo jobInfo = jobScheduler.getPendingJob(JOB_ID);
        if(jobInfo == null){
            Log.d(TAG, "start: restarting job");
            jobScheduler.schedule(job);
        } else {
            Log.d(TAG, "start: running");
            Toast.makeText(mContext, "service is already started", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View view) {
        JobInfo jobInfo = jobScheduler.getPendingJob(JOB_ID);
        if(jobInfo == null){
            Log.d(TAG, "stop: no jobs");
            Toast.makeText(mContext, "service isn't started yet", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "stop: stopping job");
            jobScheduler.cancel(JOB_ID);
        }
    }

    public void send(View view) {
    }
}
