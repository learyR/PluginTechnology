package com.company.leary.myframework.model.entity;

import android.view.View;

import com.company.leary.myframework.model.skin.SkinManager;

import java.util.List;

public class SkinView {
    private View view;
    private List<SkinItem> list;

    public SkinView(View view, List<SkinItem> list) {
        this.view = view;
        this.list = list;
    }

    public void apply() {
        //应用所有的换肤
        for (SkinItem skinItem : list) {
            if ("background".equals(skinItem.getAttrName())) {
                if ("color".equals(skinItem.getAttrType())) {
                    view.setBackgroundColor(SkinManager.getInstance().getColor(skinItem.getAttrId()));
                } else if ("drawable".equals(skinItem.getAttrType())) {
                    view.setBackgroundDrawable(SkinManager.getInstance().getDrawable(skinItem.getAttrId()));
                }
            }
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinItem> getList() {
        return list;
    }

    public void setList(List<SkinItem> list) {
        this.list = list;
    }
}
