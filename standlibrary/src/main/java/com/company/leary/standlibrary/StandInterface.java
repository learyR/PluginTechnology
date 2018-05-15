package com.company.leary.standlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * @author leary
 */
public interface StandInterface {
    /**
     * 注入上下这
     *
     * @param proxyActivity
     */
    void attach(Activity proxyActivity);

    void onCreate(Bundle SavedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onRestart();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    void onBackPressed();

    boolean onTouchEvent(MotionEvent event);
}
