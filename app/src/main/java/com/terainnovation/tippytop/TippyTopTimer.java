package com.terainnovation.tippytop;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by shailesh.rawat on 11-07-2016.
 */
public class TippyTopTimer extends CountDownTimer {

    public static long score = 0;
    MediaPlayer ding;
    TextView count;
    long finalScore;
    Intent mainIntent ;
    colors colors;
    private boolean run = true;

    public TippyTopTimer(long startTime, long interval){
        super(startTime, interval);
    }
    public TippyTopTimer(long startTime, long interval,MediaPlayer ding,TextView count ,long finalScore ,Intent mainIntent, colors colors  )
    {
        super(startTime, interval);
        this.ding=ding;
        this.count = count;
        this.finalScore = finalScore;
        this.mainIntent = mainIntent;
        this.colors = colors;
    }

    @Override
    public void onFinish()
    {


        if(run){
            ding.start();

            mainIntent.putExtra("result", "Time Up....\n! " + UserInfo.name);
            mainIntent.putExtra("finalscore", finalScore);
            colors.startActivity(mainIntent);
        }
    }

    @Override
    public void onTick(long m)
    {

        ding.start();
        long sec = m / 1000 + 1;
        count.setText("" + sec);
        score = sec;

    }
}

