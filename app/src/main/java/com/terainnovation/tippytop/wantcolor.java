package com.terainnovation.tippytop;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

/**
 * Created by shailesh.rawat on 21-06-2016.
 */

public class wantcolor extends ActivityWrapper implements View.OnClickListener {
    Animation animblink;
    public static String[][] colorArrays = {{"Blue", "#0000FF"}, {"Brown", "#9E664C"}, {"Green", "#4B7A22"}, {"Violet", "#9400D3"}, {"Gray", "#696969"}, {"Olive", "#137244"}, {"Peach", "#FFDAB9"}, {"Red", "#FF0000"}, {"Teal", "#008080"}, {"Yellow", "#FFFF00"}, {"Lime", "#33FF57"}, {"Maroon", "#800000"}, {"Orange", "#FFA500"}};
    TextView colorwant;
    TextView TextView;
    TextView TextView1;
    TextView TextView2;
    TextView TextView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.wantcolor);
        View v = null;
        if (savedInstanceState == null) {
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        TextView = (TextView) findViewById(R.id.iwant);
        TextView1 = (TextView) findViewById(R.id.wantwant);
        TextView2 = (TextView) findViewById(R.id.dotwant);
        TextView3 = (TextView) findViewById(R.id.dot1want);
        colorwant = (TextView) findViewById(R.id.colorwant);
        animblink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        colorwant.startAnimation(animblink);
        String[] colorArr = getColor().split("_");
        colorwant.setText(colorArr[0]);
        colorwant.setTextColor(Color.parseColor(colorArr[1]));
        FrameLayout layout = (FrameLayout) findViewById(R.id.wantcolor);//In your xml file, main xml  layout android:id="@+id/layout"
        TextView wantView = (TextView) findViewById(R.id.colorwant);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Fonts/colors.ttf");
        TextView.setTypeface(myTypeface);
        TextView1.setTypeface(myTypeface);
        layout.setOnClickListener(this);
        TextView.setOnClickListener(this);
        wantView.setOnClickListener(this);

        TextView.setText("I\n");
        TextView1.setText("Want");
        TextView2.setText(".\n");
        TextView3.setText(".\n");
        MobileAds.initialize(getApplicationContext());
        AdView mAdView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void onClick(View v) {
        finish();
        long finalScore = getIntent().getLongExtra("finalscore", 0);
        final Intent mainIntent = new Intent(getApplicationContext(), colors.class);
        mainIntent.putExtra("colorcode", colorwant.getText().toString());
        mainIntent.putExtra("finalscore", finalScore);
        wantcolor.this.startActivity(mainIntent);

    }

    private String getColor() {
        int num = new Random().nextInt(colorArrays.length);
        return colorArrays[num][0] + "_" + colorArrays[num][1];
    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(wantcolor.this, Welcome.class));
        // create Intent and start MainActivity again
    }


}


