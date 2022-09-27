package com.developbyte.practica19.Abstract;

import android.app.Activity;

import com.developbyte.practica19.Interfaces.IMementoHandler;

public abstract class AbstractBusinessController {

    protected IMementoHandler mementoHandler;
    protected Activity activity;

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}