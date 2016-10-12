package com.terainnovation.tippytop;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by shailesh.rawat on 22-06-2016.
 */
public class lastscreen extends ActivityWrapper {

    private Button button1,button2;
    private InterstitialAd mInterstitialAd;
    protected void onCreate(Bundle savedInstanceState) {
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        MediaWrapper.mp.setVolume(1,1);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lastscreen);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Fonts/colors.ttf");
        //TextView v = (TextView) findViewById(R.id.finalWords);
        ImageView img = (ImageView) findViewById(R.id.resultimg);
        ImageView smiley = (ImageView) findViewById(R.id.resultsmiley);
        TextView run = (TextView) findViewById(R.id.score);
        TextView finalrun = (TextView) findViewById(R.id.finalscore);
        //v.setTypeface(myTypeface);
        run.setTypeface(myTypeface);
        finalrun.setTypeface(myTypeface);
        long score = getIntent().getLongExtra("score", 0);
        run.setText("Your Score = " +score);
        String result = getIntent().getStringExtra("result");
        final Animation animblink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        final Animation blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        smiley.startAnimation(animblink);
        if(result == null || result.equals("Y")){
            //result = "YOU LOOSE";
            smiley.setBackgroundResource(R.drawable.failimg);
            img.startAnimation(blink);
            img.setBackgroundResource(R.drawable.youloose);
        }
        else
        {
            smiley.setBackgroundResource(R.drawable.winimg);
            img.startAnimation(blink);
            img.setBackgroundResource(R.drawable.youwin);
        }


        long finalscore= getIntent().getLongExtra("finalscore", 0);
        finalscore= finalscore + score;
        finalrun.setText("Final Score = " +finalscore);
        if (savedInstanceState == null) {
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        button1 = (Button) findViewById(R.id.restartId);
        final long finalScore = finalscore;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Welcome.class);
                intent.putExtra("finalscore", finalScore);
                startActivity(intent);
            }
        });
        button2 = (Button) findViewById(R.id.exitId);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                showInterstitial();
                finish();

            }
        });


    }
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(lastscreen.this,Welcome.class));
        // create Intent and start MainActivity again
    }
    private void loadInterstitial() {

        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }


    private void goToNextLevel() {
        this.finish();
        Intent intent = new Intent(getApplicationContext(), Color_Chooser.class);
        startActivity(intent);

    }
    private void showInterstitial() {

        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            if(mInterstitialAd.isLoaded()){
                mInterstitialAd.show();

            }

        } else {

            goToNextLevel();
        }
    }
    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd =new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
                        public void onAdClosed() {

               goToNextLevel();
               }
           });
        return interstitialAd;
    }

    public void onAdFailedToLoad(int error) {
        Intent intent = new Intent(getApplicationContext(), Color_Chooser.class);
        startActivity(intent);
    }
}



