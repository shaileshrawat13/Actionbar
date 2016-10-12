package com.terainnovation.tippytop;

import android.app.Activity;

import com.terainnovation.tippytop.MediaWrapper;

/**
 * Created by shailesh.rawat on 24-06-2016.
 */
public abstract class ActivityWrapper extends Activity
{

    @Override
    protected void onPause() {
        if(MediaWrapper.musicflag) {

            MediaWrapper.mp.pause();
        }
            super.onPause();
        }
    protected void onResume(){

        if(MediaWrapper.musicflag) {

            MediaWrapper.mp.start();
        }
        super.onResume();
    }
    }

