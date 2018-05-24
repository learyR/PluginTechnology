package com.company.leary.myframework.base;

import android.app.Activity;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.leary.myframework.model.skin.SkinFactory;
import com.company.leary.myframework.model.skin.SkinManager;

public class BaseActivity extends Activity {
    public SkinFactory skinFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SkinManager.getInstance().init(this);
        //监听xml生成的过程
        skinFactory = new SkinFactory();
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), skinFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        skinFactory.clear();
    }
}
