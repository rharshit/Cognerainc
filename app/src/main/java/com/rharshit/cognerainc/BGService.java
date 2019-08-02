package com.rharshit.cognerainc;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class BGService extends JobService {


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "onStartJob: Started");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob: Stopped");
        return false;
    }
}
