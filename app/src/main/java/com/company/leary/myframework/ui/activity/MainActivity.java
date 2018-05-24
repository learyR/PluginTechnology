package com.company.leary.myframework.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.company.leary.myframework.R;
import com.company.leary.myframework.base.BaseActivity;
import com.company.leary.myframework.model.plugin.PluginManager;
import com.company.leary.myframework.model.skin.SkinManager;

import java.io.File;

/**
 * @author leary
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission(PERMISSIONS);
        PluginManager.getInstance().setContext(this);
        File skinFile = new File(Environment.getExternalStorageDirectory(), "skina.apk");
        SkinManager.getInstance().loadApk(skinFile.getPath());
    }
    public void load(View view) {
        File pluginFile = new File(Environment.getExternalStorageDirectory(), "plugin.apk");
        PluginManager.getInstance().loadApk(pluginFile.getPath());
    }

    public void open(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }
    //开始换肤
    public void skin(View view) {
        skinFactory.apply();
    }


    //=========权限============
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    /**
     * app需要开通的运行时权限
     */
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    private static final String[] PERMISSIONS_STR = new String[]{
            "读写手机存储",
            "获取手机信息"
    };
    private void requestPermission(String[] permissions) {
        boolean isMinSdkM = Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
        if (isMinSdkM || permissions.length == 0) {
            //低于6。0或无权限，则直接通过
            return;
        }

        //否则请求权限
        ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (isTip) {//表明用户没有彻底禁止弹出权限请求
                        requestPermission(new String[]{permissions[i]});
                    } else {
                        //表明用户已经彻底禁止弹出权限请求
                    }
                    return;
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
