package com.company.leary.taopiaopiao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.company.leary.standlibrary.StandInterface;

public class BaseActivity extends Activity implements StandInterface {
    /**
     * MyFrameWork的上下文
     */
    protected Activity that;

    @Override
    public void attach(Activity proxyActivity) {
        that = proxyActivity;
    }

    @Override
    public void setContentView(View view) {
        that.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public Resources getResources() {
        return that.getResources();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }

    @Override
    public void finish() {
        that.finish();
    }

    @Override
    public void onCreate(Bundle SavedInstanceState) {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void startActivity(Intent intent) {
        Intent intent1 = new Intent();
        intent1.putExtra("className", intent.getComponent().getClassName());
        that.startActivity(intent1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
