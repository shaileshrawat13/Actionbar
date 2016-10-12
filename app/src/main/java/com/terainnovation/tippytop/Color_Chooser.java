package com.terainnovation.tippytop;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class Color_Chooser extends AppCompatActivity {

     private Button button1;
     EditText edit;
    ImageButton b;
    boolean volumeon=true;

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {


            Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Fonts/colors.ttf");
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_color__chooser);
            LayoutInflater inflator = LayoutInflater.from(this);
            //View v = inflator.inflate(R.layout.activity_color__chooser, null);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbar.setNavigationIcon(R.drawable.toplogo);
            toolbar.setBackground(getResources().getDrawable(R.drawable.shape1));
            toolbar.setTitle("");
            mTitle.setTypeface(myTypeface);

            setSupportActionBar(toolbar);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            if (savedInstanceState == null) {
            }

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);

            TextView myTextView = (TextView)findViewById(R.id.welcometext);
            myTextView.setTypeface(myTypeface);
            edit = (EditText)findViewById(R.id.username);
            edit.setTypeface(myTypeface);
            edit.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
            button1 = (Button) findViewById(R.id.greetId);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = edit.getText().toString();
                name=name.trim();
                if(name != null && !name.equals("")) {
                    Intent intent = new Intent(v.getContext(), Welcome.class);
                    intent.putExtra("welcomeText", "Welcome\n" + name);
                    UserInfo.name = name;
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_SHORT).show();
                }
            }
        });
        RelativeLayout lin = (RelativeLayout) findViewById(R.id.main);
        lin.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
        MobileAds.initialize(getApplicationContext());
        AdView mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        b = (ImageButton) findViewById(R.id.musicButton);
        if (MediaWrapper.count==1){
            b.setBackgroundResource(R.drawable.unmute);
            MediaWrapper.count++;
            MediaWrapper.musicflag=true;
        }
        if (MediaWrapper.musicflag)
        {

            b.setBackgroundResource(R.drawable.unmute);
        }
        else
        {
            b.setBackgroundResource(R.drawable.mute);
            MediaWrapper.musicflag=false;
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaWrapper.musicflag){
                    b.setBackgroundResource(R.drawable.mute);
                    MediaWrapper.musicflag=false;
                }
                else{
                    b.setBackgroundResource(R.drawable.unmute);
                    MediaWrapper.musicflag=true;
                }

            }
        });
    }
    @Override

        public void onBackPressed()    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (MediaWrapper.mp!=null && MediaWrapper.mp.isPlaying()){
            MediaWrapper.mp.pause();
            startActivity(intent);
        }
        else {
            startActivity(intent);
        }
        return;// create Intent and start MainActivity again
    }

    }


