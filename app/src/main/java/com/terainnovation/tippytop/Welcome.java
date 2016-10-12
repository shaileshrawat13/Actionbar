package com.terainnovation.tippytop;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.terainnovation.tippytop.MediaWrapper;
import com.terainnovation.tippytop.R;
import com.terainnovation.tippytop.UserInfo;


/**
 * Created by shailesh.rawat on 21-06-2016.
 */

public class Welcome extends ActivityWrapper {

    TextView nameView;
    Animation animblink;
    private Button button2;
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(Welcome.this,Color_Chooser.class));
        if (MediaWrapper.musicflag)
        {
            MediaWrapper.mp.seekTo(0);
            MediaWrapper.mp.pause();
        }
        return;// create Intent and start MainActivity again
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome);
        if (savedInstanceState == null) {
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        if(MediaWrapper.mp == null)
        {

            MediaWrapper.mp = MediaPlayer.create(getApplicationContext(), R.raw.flourish);
            MediaWrapper.mp.setVolume(0.5f,0.5f);
            MediaWrapper.mp.setLooping(true);
            if (MediaWrapper.musicflag) {
                MediaWrapper.mp.start();
            }
        }
        else if(MediaWrapper.mp != null && MediaWrapper.mp.isPlaying()==false && MediaWrapper.musicflag)
        {

            MediaWrapper.mp.seekTo(MediaWrapper.mp.getCurrentPosition());
            MediaWrapper.mp.start();
        }
        nameView = (TextView) findViewById(R.id.nameview);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Fonts/colors.ttf");
        nameView.setTypeface(myTypeface);
        Intent intent = getIntent();
        nameView.setText("Welcome..!! \n"+ UserInfo.name+"\nGood to see you ");
        animblink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        nameView.startAnimation(animblink);
        button2 = (Button) findViewById(R.id.welcomeid);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                long finalScore = getIntent().getLongExtra("finalscore", 0);
                Intent nextIntent = new Intent(v.getContext(), wantcolor.class);
                nextIntent.putExtra("finalscore", finalScore);
                startActivity(nextIntent);
            }
        });
        MobileAds.initialize(getApplicationContext());
        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    }





