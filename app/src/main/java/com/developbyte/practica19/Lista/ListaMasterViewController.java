package com.developbyte.practica19.Lista;

import android.os.Bundle;
import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.developbyte.practica19.Abstract.AbstractViewController;
import com.developbyte.practica19.Interfaces.IMasterViewController;
import com.developbyte.practica19.App.InjectionManager;
import com.developbyte.practica19.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;

public class ListaMasterViewController extends AppCompatActivity implements IMasterViewController {

    private HashMap<Integer, AbstractViewController> fragments;
    private ArrayList<Integer> tags;

    public static final int LISTA_CONTROLLER = 0;
    public static final int AGREGAR_CONTROLLER = 1;


    @IntDef({LISTA_CONTROLLER,AGREGAR_CONTROLLER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProfileControllers{}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        fragments = new HashMap<>();
        tags = new ArrayList<>();

        FragmentManager fr = getSupportFragmentManager();
        if (getSupportFragmentManager().getFragments() != null) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment != null) {
                    fr.beginTransaction().remove(fragment).commit();
                    getSupportFragmentManager().executePendingTransactions();
                }
            }
        }

        InjectionManager.getInstance().startLista(this);

    }
    @Override
    public void addFragment(AbstractViewController fr) {
        fragments.put(fr.tag, fr);
    }

    @Override
    public void presetFragment(@ProfileControllers int tag) {
        tags.add(0,tag);
        Fragment fragment = fragments.get(tag);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listaMasterView, fragment)
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public boolean presetFragment2(@ProfileControllers int tag) {
        tags.add(0, tag);
        Fragment fragment = fragments.get(tag);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listaMasterView, fragment)
                .addToBackStack(null)
                .commit();

        return getSupportFragmentManager().executePendingTransactions();
    }


    @Override
    public void presentMenu(int tag) {

    }

    @Override
    public void onBackPressed() {
        int index = tags.remove(0);
        fragments.get(index).onBackPressed();
    }

    @Override
    public void finishThis() {
        finish();
    }

}