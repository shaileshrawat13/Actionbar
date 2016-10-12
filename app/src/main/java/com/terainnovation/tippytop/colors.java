package com.terainnovation.tippytop;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by shailesh.rawat on 21-06-2016.
 */
public class colors extends ActivityWrapper implements View.OnClickListener {

    private String color;
    public static long score = 0;
    private boolean run = true;
    ProgressBar mprogressBar;
    CountDownTimer ctimer;
    Vibrator vi;
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(Bundle savedInstanceState) {

        MediaWrapper.mp.setVolume(0,0);

        final MediaPlayer ding = MediaPlayer.create(getApplicationContext(), R.raw.ding);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.colors);
        if (savedInstanceState == null) {
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        ImageView img = (ImageView)findViewById(R.id.Cloudbg);
        img.setBackgroundResource(R.drawable.progress);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();

        TextView timer = (TextView) findViewById(R.id.timer);
        final Animation animblink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        timer.startAnimation(animblink);
        final Animation animbounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        FrameLayout layout = (FrameLayout) findViewById(R.id.colors);
        mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(18000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        final TextView count = (TextView) findViewById(R.id.count);
        ctimer = new CountDownTimer(5000, 1000) {

            public void onTick(long m) {
                ding.start();
                long sec = m / 1000;
                count.setText("" + sec);
                score = sec;
            }

            public void onFinish() {
                if(run){
                    ding.start();
                    long finalScore = getIntent().getLongExtra("finalscore", 0);
                    Intent mainIntent = new Intent(getApplicationContext(), lastscreen.class);
                    mainIntent.putExtra("result", "Y");
                    mainIntent.putExtra("finalscore", finalScore);
                    colors.this.startActivity(mainIntent);
                }
            }
        };
        Button yellowButton = (Button) findViewById(R.id.yellow);
        yellowButton.startAnimation(animbounce);
        yellowButton.setTag("yellow");
        yellowButton.setOnClickListener(this);

        Button tealButton = (Button) findViewById(R.id.teal);
        tealButton.startAnimation(animbounce);
        tealButton.setTag("teal");
        tealButton.setOnClickListener(this);

        Button blueButton = (Button) findViewById(R.id.blue);
        blueButton.startAnimation(animbounce);
        blueButton.setTag("blue");
        blueButton.setOnClickListener(this);

        Button brownButton = (Button) findViewById(R.id.brown);
        brownButton.startAnimation(animbounce);
        brownButton.setTag("brown");
        brownButton.setOnClickListener(this);

        Button grayButton = (Button) findViewById(R.id.gray);
        grayButton.startAnimation(animbounce);
        grayButton.setTag("gray");
        grayButton.setOnClickListener(this);

        Button limeButton = (Button) findViewById(R.id.lime);
        limeButton.startAnimation(animbounce);
        limeButton.setTag("lime");
        limeButton.setOnClickListener(this);

        Button redButton = (Button) findViewById(R.id.red);
        redButton.startAnimation(animbounce);
        redButton.setTag("red");
        redButton.setOnClickListener(this);

        Button maroonButton = (Button) findViewById(R.id.maroon);
        maroonButton.startAnimation(animbounce);
        maroonButton.setTag("maroon");
        maroonButton.setOnClickListener(this);

        Button oliveButton = (Button) findViewById(R.id.olive);
        oliveButton.startAnimation(animbounce);
        oliveButton.setTag("olive");
        oliveButton.setOnClickListener(this);

        Button greenButton = (Button) findViewById(R.id.green);
        greenButton.startAnimation(animbounce);
        greenButton.setTag("green");
        greenButton.setOnClickListener(this);

        Button peachButton = (Button) findViewById(R.id.peach);
        peachButton.startAnimation(animbounce);
        peachButton.setTag("peach");
        peachButton.setOnClickListener(this);

        Button orangeButton = (Button) findViewById(R.id.orange);
        orangeButton.startAnimation(animbounce);
        orangeButton.setTag("orange");
        orangeButton.setOnClickListener(this);

        Button violetButton = (Button) findViewById(R.id.violet);
        violetButton.startAnimation(animbounce);
        violetButton.setTag("violet");
        violetButton.setOnClickListener(this);

        color = getIntent().getStringExtra("colorcode");

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View v) {
        Vibrator vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vi.vibrate(500);
        MediaPlayer snd_brst = MediaPlayer.create(getApplicationContext(), R.raw.bln_brst);
        snd_brst.setVolume(1.0f, 1.0f);
        snd_brst.start();
        run = false;
        Button b = (Button) findViewById(v.getId());
        //b.setBackground(R.drawable.baloon_burst_red);
        switch (v.getId()) {
            case R.id.red: {
                b.setBackgroundResource(R.drawable.red_burst);
                break;
            }
            case R.id.teal: {
                b.setBackgroundResource(R.drawable.teal_burst);
                break;
            }
            case R.id.maroon: {
                b.setBackgroundResource(R.drawable.maroon_burst);
                break;
            }
            case R.id.yellow: {
                b.setBackgroundResource(R.drawable.yellow_burst);
                break;
            }
            case R.id.orange: {
                b.setBackgroundResource(R.drawable.orange_burst);
                break;
            }
            case R.id.green: {
                b.setBackgroundResource(R.drawable.green_burst);
                break;
            }
            case R.id.peach: {
                b.setBackgroundResource(R.drawable.peach_burst);
                break;
            }
            case R.id.brown: {
                b.setBackgroundResource(R.drawable.brown_burst);
                break;
            }
            case R.id.lime: {
                b.setBackgroundResource(R.drawable.lime_burst);
                break;
            }
            case R.id.violet: {
                b.setBackgroundResource(R.drawable.violet_burst);
                break;
            }
            case R.id.gray: {
                b.setBackgroundResource(R.drawable.gray_burst);
                break;
            }
            case R.id.blue: {
                b.setBackgroundResource(R.drawable.blue_burst);
                break;
            }
            case R.id.olive: {
                b.setBackgroundResource(R.drawable.olive_burst);
                break;
            }


            //.... etc
        }
        String btnName = v.getTag().toString();
        long finalScore = getIntent().getLongExtra("finalscore", 0);
        Intent mainIntent = new Intent(getApplicationContext(), lastscreen.class);
        if (btnName.equalsIgnoreCase(color)) {
            stopTimer();
            mainIntent.putExtra("result", "X" );
            mainIntent.putExtra("finalscore", finalScore);
            mainIntent.putExtra("score", score);
            colors.this.startActivity(mainIntent);
        } else {
            stopTimer();
            mainIntent.putExtra("finalscore", finalScore);
            colors.this.startActivity(mainIntent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ctimer.start();
            }
        }, 500);

    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(colors.this, Welcome.class));
        stopTimer();
        return;
        // create Intent and start MainActivity again
    }

    private void stopTimer(){
        if(ctimer != null){
            ctimer.cancel();
        }
    }

}





