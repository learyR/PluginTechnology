package com.company.leary.myframework;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.leary.standlibrary.StandInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ProxyActivity extends AppCompatActivity {
    private String className;
    private StandInterface standInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");

        try {
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object o = constructor.newInstance(new Object[]{});
            //实现表准
            standInterface = (StandInterface) o;
            standInterface.attach(this);
            //传参
            Bundle bundle = new Bundle();
            standInterface.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }



    @Override
    protected void onStart() {
        super.onStart();
        standInterface.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        standInterface.onPause();
    }

    @Override
    public void startActivity(Intent intent) {
        String className1 = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className1);
        super.startActivity(intent1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        standInterface.onDestroy();
    }
}
