package com.company.leary.myframework.model.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SkinManager {
    private static final SkinManager ourInstance = new SkinManager();

    public static SkinManager getInstance() {
        return ourInstance;
    }

    private SkinManager() {
    }

    //apk中的resources
    private Resources skinResources;
    private Context context;
    //皮肤apk的包名
    private String skinPackage;

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public Resources getSkinResources() {
        return skinResources;
    }

    //获取resId
    public int getColor(int resId) {
        if (skinResources == null) {
            return ContextCompat.getColor(context, resId);
        }
        String resName = context.getResources().getResourceEntryName(resId);
        int skinId = skinResources.getIdentifier(resName, "color", skinPackage);
        if (skinId == 0) {
            return ContextCompat.getColor(context, resId);
        }
        if (resId == skinId) {
            Log.e("leary", resName + " id一样 " + skinPackage);
        }
        return skinResources.getColor(skinId);
    }

    public Drawable getDrawable(int resId) {
        if (skinResources != null) {
            String resName = context.getResources().getResourceEntryName(resId);
            int skinId = skinResources.getIdentifier(resName, "drawable", skinPackage);
            if (skinId == 0) {
                return ContextCompat.getDrawable(context, resId);
            }
            return skinResources.getDrawable(skinId);
        }
        return ContextCompat.getDrawable(context, resId);
    }

    //加载apk
    public void loadApk(String path) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, path);
            skinResources = new Resources(assetManager, context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration());
            PackageManager packageManager = context.getPackageManager();
            //拿到皮肤的包名
            skinPackage = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES).packageName;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
