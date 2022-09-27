package com.developbyte.practica19.Lista;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.practica19.Abstract.AbstractViewController;
import com.developbyte.practica19.Adapters.LibrosAdapter;
import com.developbyte.practica19.Model.LibroModel;
import com.developbyte.practica19.R;

import java.util.List;

public class ListaViewController extends AbstractViewController implements ILista.IListaRepresentationHandler {

    private ILista.IListaRepresentationDelegate representationDelegate;
    private AppCompatEditText txtFilter;
    private AppCompatButton btnAgregar;
    private RecyclerView lstLibros;
    private LibrosAdapter librosAdapter;

    public void setRepresentationDelegate(ILista.IListaRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_lista, container, false);

        txtFilter = view.findViewById(R.id.txtFilter);
        txtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                representationDelegate.filtrarLibros(editable.toString().toLowerCase());
            }
        });
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.showAgregar();
            }
        });

        lstLibros = view.findViewById(R.id.lstLibros);
        lstLibros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        librosAdapter = new LibrosAdapter(getContext(), representationDelegate);
        lstLibros.setAdapter(librosAdapter);

        return view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreData(Bundle savedInstanceState) {

    }


    @Override
    public void onBackPressed() {
        getActivity().finish();
    }

    @Override
    public boolean showLista() {
        return masterViewController.presetFragment2(tag);
    }

    @Override
    public void actualizarLista(List<LibroModel> modelList) {
        librosAdapter.setModelList(modelList);
    }
}