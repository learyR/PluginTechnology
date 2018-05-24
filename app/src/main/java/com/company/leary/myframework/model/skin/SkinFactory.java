package com.company.leary.myframework.model.skin;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.company.leary.myframework.model.entity.SkinItem;
import com.company.leary.myframework.model.entity.SkinView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SkinFactory implements LayoutInflater.Factory2 {

    private static final String[] prefixList = {"android.widget.", "android.view.", "android.webkit."};
    private List<SkinView> cacheList = new ArrayList<>();
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.e("leary-" + SkinFactory.class.getName(), name);
        //收集需要换肤的控件
        View view = null;
        if (name.contains(".")) {// 自定义控件
            view = createView(context, attrs, name);
        } else {//系统控件
            for (String prefix : prefixList) {
                view = createView(context, attrs, prefix + name);
                if (view != null) {
                    break;
                }
            }
        }
        if (view != null) {
            parseSkinView(context, attrs,view);
        }
        return view;
    }

    private void parseSkinView(Context context, AttributeSet attrs, View view) {
        List<SkinItem> list = new ArrayList<>();
        for (int i=0;i<attrs.getAttributeCount();i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            if ("textColor".equals(attrName) || "background".equals(attrName)) {
                //可换肤的控件
                int id = Integer.parseInt(attrValue.substring(1));
                String entry_name = context.getResources().getResourceEntryName(id);
                String typeName = context.getResources().getResourceTypeName(id);
                SkinItem skinItem = new SkinItem(attrName, entry_name, typeName, id);
                list.add(skinItem);
            }
        }
        if (!list.isEmpty()) {
            SkinView skinView = new SkinView(view, list);
            cacheList.add(skinView);
            //xml加载过程中换肤
            skinView.apply();
        }
    }

    //
    private View createView(Context context, AttributeSet attrs, String name) {
        try {
            Class viewClazz = context.getClassLoader().loadClass(name);
            Constructor<? extends View> constructor = viewClazz.getConstructor(
                    new Class[]{Context.class, AttributeSet.class});
            return constructor.newInstance(context, attrs);
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

        return null;
    }

    public void apply() {
        for (SkinView skinView : cacheList) {
            skinView.apply();
        }
    }

    public void clear() {
        for (SkinView skinView : cacheList) {
            cacheList.remove(skinView);
        }
        if (cacheList != null) {
            cacheList.clear();
            cacheList = null;
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }
}
