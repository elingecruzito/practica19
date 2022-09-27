package com.developbyte.practica19.Interfaces;

import com.developbyte.practica19.Abstract.AbstractViewController;

public interface IMasterViewController {
    void addFragment(AbstractViewController fr);
    void presetFragment(int tag);
    boolean presetFragment2(int tag);
    void presentMenu(int tag);
    void finishThis();
}