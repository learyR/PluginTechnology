package com.company.leary.myframework.model.entity;

public class SkinItem {
    // background or textColor
    private String attrName;
    private String attrValue;
    // drwable or color
    private String attrType;
    private int refId;

    public SkinItem(String attrName, String attrValue, String attrType, int attrId) {
        this.attrName = attrName;
        this.attrValue = attrValue;
        this.attrType = attrType;
        this.refId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public int getAttrId() {
        return refId;
    }

    public void setAttrId(int attrId) {
        this.refId = attrId;
    }
}
