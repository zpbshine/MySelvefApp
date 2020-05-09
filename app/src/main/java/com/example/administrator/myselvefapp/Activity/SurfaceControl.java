package com.example.administrator.myselvefapp.Activity;

/**
 * Created by win on 2020/4/9.
 */

import android.graphics.Bitmap;
import android.view.View;

public class SurfaceControl {

    public static Bitmap screenshot(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        return bmp;
    }
}


