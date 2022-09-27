package com.developbyte.practica19.Model;

import java.util.HashMap;

public class Memento {

    private Object mMementoOwner;
    private HashMap<String, Object> mMementoData;

    public Memento() {
        mMementoData = new HashMap<>();
        mMementoOwner = new Object();
    }

    public Object getMementoOwner() {
        return mMementoOwner;
    }

    public void setMementoOwner(Object mementoOwner) {
        mMementoOwner = mementoOwner;
    }

    public HashMap<String, Object> getMementoData() {
        return mMementoData;
    }

    public void setMementoData(HashMap<String, Object> mementoData) {
        mMementoData = mementoData;
    }
}